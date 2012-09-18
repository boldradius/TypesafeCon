package json

import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes
import models.PrivateMessage

/** Writes a private message to JSON */
object JSONPrivateMessageWriter extends Writes[PrivateMessage] {
	override def writes(message: PrivateMessage): JsValue = {
		toJson(
			Map("id" ->          toJson(message.id.get),
				"senderId" ->    toJson(message.senderId),
				"toUserId" ->     toJson(message.toUserId.get),
				"content" ->     toJson(message.content),
				"senderName" ->  toJson(message.senderName),
				"sentTime" ->    toJson(message.sentTime.toString()),
				"index" ->       toJson(message.index))
			)
	}
}