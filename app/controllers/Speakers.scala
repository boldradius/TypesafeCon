package controllers

import play.api._
import play.api.mvc._
import models.Speaker
import json.JsonSpeakerWriter
import models.S1Event

object Speakers extends APIController {

	/** Returns the full list of speakers in JSON format */
	def list = Action {
		try {
			Success(Speaker.findAll, "speakers")(JsonSpeakerWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}

	/** Returns the details of a specific speaker */
	def get(id: Long) = Action {
		implicit request =>
			{
				Speaker.findById(id) match {
					case Some(speaker) => {
						val events = S1Event.findBySpeakerId(speaker.id.get)
						Ok(views.html.speaker(speaker, events))
					}
					case _ => BadRequest("Speaker not found")
				}
			}
	}

}