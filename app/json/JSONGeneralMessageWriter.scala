package json

import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes
import models.GeneralMessage

/** Writes a general message to JSON */
object JSONGeneralMessageWriter extends Writes[GeneralMessage] {
	override def writes(message: GeneralMessage): JsValue = {
		toJson(
			Map("id" ->          toJson(message.id.get),
				"senderId" ->    toJson(message.senderId),
				"content" ->     toJson(message.content),
				"senderName" ->  toJson(message.senderName),
				"sentTime" ->    toJson(message.sentTime.toString()),
				"index" ->       toJson(message.index))
			)
	}
}