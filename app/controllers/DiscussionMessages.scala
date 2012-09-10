package controllers

import play.api._
import play.api.mvc._
import models._
import json._
import play.api.data.Form
import play.api.data.Forms._

object DiscussionMessages extends APIController {

	private val userMapping = longNumber.verifying("User does not exist", userid => User.findById(userid).isDefined)
	private val eventMapping = longNumber.verifying("Event does not exist", eventid => S1Event.findById(eventid).isDefined)
	private val contentMapping = text(minLength = 1, maxLength = 255)

	private val generalMessageForm: Form[GeneralMessage] = Form(
		mapping(
			"senderId" -> userMapping,
			"content" -> contentMapping) 
			{ GeneralMessage.apply } { message => None })

	private val eventMessageForm: Form[EventMessage] = Form(
		mapping(
			"senderId" -> userMapping,
			"eventId" -> eventMapping,
			"content" -> contentMapping) 
			{ EventMessage.apply } { message => None })

	private val privateMessageForm: Form[PrivateMessage] = Form(
		mapping(
			"senderId" -> userMapping,
			"toUserId" -> userMapping,
			"content" -> contentMapping) 
			{ PrivateMessage.apply } { message => None })

	/**
	 * Returns all the general discussion messages
	 */
	def general = Action {
		try {
			Success(GeneralMessage.findAll, "messages")(JSONGeneralMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving discussion messages", t)
				ServerError(t.getMessage)
			}
		}
	}

	def createGeneral = Action {
		implicit request =>
			{
				try {
					generalMessageForm.bindFromRequest.fold(

						// Error on the user parameters, send proper json error
						formWithErrors => ParamError(formWithErrors.errors(0)),

						// Parameters are fine, create user
						message => {
							message.create match {
								case Some(newMessage) => Success(newMessage.id.get)
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

	/**
	 * Returns all the discussion messages specific to an event
	 */
	def event(id: Long) = Action {
		try {
			Success(EventMessage.findAll(id), "messages")(JSONEventMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving messages for event " + id, t)
				ServerError(t.getMessage)
			}
		}
	}

	/**
	 * Returns all the private messages between two users
	 */
	def privateMessages(id1: Long, id2: Long) = Action {
		try {
			Success(PrivateMessage.findAll(id1, id2), "messages")(JSONPrivateMessageWriter)
		} catch {
			case t: Throwable => {
				Logger.error("Error retrieving private messages between users " + id1 + " and " + id2, t)
				ServerError(t.getMessage)
			}
		}
	}

}