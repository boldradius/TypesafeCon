package controllers

import play.api._
import play.api.mvc._
import models.S1Event
import json.JsonEventWriter

object Events extends APIController {

	/** Returns the full list of events in JSON format
	  */
	def list = Action {
		try {
			Success(S1Event.findAll, "events")(JsonEventWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}

}