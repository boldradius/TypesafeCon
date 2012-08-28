package controllers

import play.api._
import play.api.mvc._
import models.Speaker
import json.JsonSpeakerWriter

object Speakers extends APIController {

	/** Returns the full list of speakers in JSON format
	  */
	def list = Action {
		try {
			Success(Speaker.findAll, "speakers")(JsonSpeakerWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}

}