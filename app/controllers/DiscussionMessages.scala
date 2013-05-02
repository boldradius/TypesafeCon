package controllers

import play.api._
import play.api.mvc._
import models._
import json._
import play.api.data.Form
import play.api.data.Forms._
import com.tindr.pusher.Pusher

object DiscussionMessages extends APIController {

	// Form mappings for message fields
	private val userMapping = longNumber.verifying("User does not exist", userid => User.findById(userid).isDefined)
	private val contentMapping = text(minLength = 1, maxLength = 255)

	/** Form for processing general messages */
	private val generalMessageForm: Form[GeneralMessage] = Form(
		mapping(
			"senderId" -> userMapping,
			"content" -> contentMapping) 
			{ GeneralMessage.apply } { message => None })

	/** Form for processing event messages */
	private def eventMessageForm(eventId: Long): Form[EventMessage] = Form(
		mapping(
			"senderId" -> userMapping,
			"content" -> contentMapping) 
			{ (senderId, content) => EventMessage(senderId, eventId, content) } 
			{ message => None }
			.verifying("Event does not exist", message => S1Event.findById(message.eventId.get).isDefined))

	/** Form for processing private messages */
	private def privateMessageForm(fromId: Long, toId: Long): Form[PrivateMessage] = Form(
		mapping("content" -> contentMapping) 
			{ content => PrivateMessage(fromId, toId, content) } 
			{ message => None }
			.verifying("Sender does not exist", message => User.findById(fromId).isDefined)
			.verifying("User does not exist", message => User.findById(toId).isDefined))

	/** Returns all the general discussion messages */
	def general(fromIndex: Option[Long]) = Action {
		try {
			Success(GeneralMessage.findAll(fromIndex), "messages")(JSONGeneralMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving discussion messages", t)
				ServerError(t.getMessage)
			}
		}
	}

	/** Creates a General Message */
	def createGeneral = create(generalMessageForm){
		// Send the new message to the general Pusher channel
		message => Pusher().trigger("general", "newMessage", message.senderId + ":" + message.content)
	}
	
	/** Creates an Event Message */
	def createEvent(eventId: Long) = create(eventMessageForm(eventId)){
		// Send the new message to the event Pusher channel
		message => Pusher().trigger("event-" + message.eventId.get, "newMessage", message.senderId + ":" + message.content)
	}
	
	/** Creates a Private Message */
	def createPrivate(fromId: Long, toId: Long) = create(privateMessageForm(fromId, toId)){
		// Send the new message to the private Pusher channel
		message => Pusher().trigger(privateChannel(fromId, toId), "newMessage", message.senderId + ":" + message.content)
	}
	
	/** Creates a Message based on a concrete form implementation */
	private def create[A <: Message](form: Form[A])(onSuccess: A => Unit) = SecuredAction {
		implicit request =>
			{
				try {
					form.bindFromRequest.fold(

						// Error on the user parameters, send proper json error
						formWithErrors => ParamError(formWithErrors.errors(0)),

						// Parameters are fine, create user
						message => {
							message.create match {
								case Some(newMessage) => {
									onSuccess(message)
									Success(newMessage.id.get)
								}
								case _ => Error("Message could not be created")
							}
						})
				} catch {
					case t: Throwable =>
						Logger.error("Error creating message", t)
						ServerError(t.getMessage)
				}
			}
	}

	/** Returns all the discussion messages specific to an event */
	def event(id: Long, fromIndex: Option[Long]) = Action {
		try {
			Success(EventMessage.findAll(id, fromIndex), "messages")(JSONEventMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving messages for event " + id, t)
				ServerError(t.getMessage)
			}
		}
	}

	/** Returns all the private messages between two users */
	def privateMessages(id1: Long, id2: Long, fromIndex: Option[Long]) = SecuredAction {
		try {
			Success(PrivateMessage.findAll(id1, id2, fromIndex), "messages")(JSONPrivateMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving private messages between users " + id1 + " and " + id2, t)
				ServerError(t.getMessage)
			}
		}
	}
	
	private def privateChannel(id1: Long, id2: Long) = "private_" + (if(id1<id2) id1 + "_" + id2 else id2 + "_" + id1)
}