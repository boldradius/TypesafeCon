package json

import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes
import models.EventMessage

/** Writes an message to JSON
  */
object JSONEventMessageWriter extends Writes[EventMessage] {
	override def writes(message: EventMessage): JsValue = {
		toJson(
			Map("id" ->          toJson(message.id.get),
				"senderId" ->    toJson(message.senderId),
				"eventId" ->     toJson(message.eventId.get),
				"content" ->     toJson(message.content),
				"senderName" ->  toJson(message.senderName),
				"sentTime" ->    toJson(message.sentTime.toString()),
				"index" ->       toJson(message.index))
			)
	}
}