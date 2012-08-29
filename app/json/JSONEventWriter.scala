package json

import models.S1Event

import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes

/** Writes an event to JSON
  */
object JsonEventWriter extends Writes[S1Event] {
	override def writes(event: S1Event): JsValue = {
		toJson(
			Map("id" ->          toJson(event.id.get),
				"code" ->          toJson(event.code),
				"title" ->       toJson(event.title),
				"description" -> toJson(event.description),
				"start" ->       toJson(event.start.toString()),
				"end" ->         toJson(event.end.toString()),
				"location" ->    toJson(event.location),
				"speakerIds" ->   toJson(event.speakerIds))
			)
	}
}