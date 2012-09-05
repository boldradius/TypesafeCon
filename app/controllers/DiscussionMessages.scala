package controllers

import play.api._
import play.api.mvc._
import models.S1Event
import models.Speaker
import models.GeneralMessage
import json._
import models.EventMessage

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


}