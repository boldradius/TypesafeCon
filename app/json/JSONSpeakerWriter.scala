package json

import models.Speaker
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes

/** Writes an speaker to JSON
  */
object JsonSpeakerWriter extends Writes[Speaker] {
	override def writes(speaker: Speaker): JsValue = {
		toJson(
			Map("id" ->      toJson(speaker.id.get),
				"name" ->    toJson(speaker.name),
				"title" ->   toJson(speaker.title),
				"about" ->   toJson(speaker.about),
				"email" ->   toJson(speaker.email),
				"twitter" -> toJson(speaker.twitter),
				"url" ->     toJson(speaker.url))
			)
	}
}