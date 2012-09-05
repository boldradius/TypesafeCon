package controllers

import play.api._
import play.api.mvc._
import models._
import json._

object DiscussionMessages extends APIController {

	/** Returns all the general discussion messages
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
	
	/** Returns all the discussion messages specific to an event
	  */
	def event(id: Long) = Action {
		try {
			Success(EventMessage.findAll(id), "messages")(JSONEventMessageWriter)
		} catch {
			case t: Throwable => {
				t.printStackTrace()
				Logger.error("Error retrieving messages for event " + id, t)
				ServerError(t.getMessage)
			}
		}
	}
	
	/** Returns all the private messages between two users
	  */
	def privateMessages(id1: Long, id2: Long) = Action {
		try {
			Success(PrivateMessage.findAll(id1, id2), "messages")(JSONPrivateMessageWriter)
		} catch {
			case t: Throwable => {
				t.printStackTrace()
				Logger.error("Error retrieving private messages between users " + id1 + " and " + id2, t)
				ServerError(t.getMessage)
			}
		}
	}


}