package controllers

import play.api._
import play.api.mvc._
import models.S1Event
import json.JsonEventWriter
import models.Speaker

object Events extends APIController {

	/** Returns the full list of events in JSON format */
	def list = Action {
		try {
			Success(S1Event.findAll, "events")(JsonEventWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}
	
	/** Returns an event along with the related speakers */
	def get(id: Long, hideActions: Option[Boolean]) = Action {
		implicit request =>
			{
				S1Event.findById(id) match {
					case Some(event) => {
						val speakers = Speaker.findByEventId(event.id.get)
						Ok(views.html.event(event, speakers, hideActions.getOrElse(false)))
					}
					case _ => BadRequest("Event not found")
				}
			}
	}

}