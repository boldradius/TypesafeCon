package controllers

import play.api._
import play.api.mvc._
import models.S1Event
import models.Speaker
import models.GeneralMessage
import json._

object DiscussionMessages extends APIController {

	/** Returns all the general discussion messages
	  */
	def general = Action {
		try {
			Success(GeneralMessage.findAll, "messages")(JSONGeneralMessageWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}
	
	/** Returns all the discussion messages specific to an event
	  */
	def event(id: Long) = Action {
		try {
			Success(GeneralMessage.findAll, "messages")(JSONGeneralMessageWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}


}