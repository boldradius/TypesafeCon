package controllers

import play.api._
import play.api.mvc._
import models.Speaker
import json.JsonSpeakerWriter

object Speakers extends APIController {

	/**
	 * Returns the full list of speakers in JSON format
	 */
	def list = Action {
		try {
			Success(Speaker.findAll, "speakers")(JsonSpeakerWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}

	def get(id: Long) = Action {
		implicit request =>
			{
				Speaker.findById(id) match {
					case Some(speaker) => Ok(views.html.speaker(speaker))
					case _ => BadRequest("Speaker not found")
				}
			}
	}

}