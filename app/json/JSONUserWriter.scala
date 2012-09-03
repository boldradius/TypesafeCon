package json

import models.User
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes

/** Writes an user to JSON
  */
object JsonUserWriter extends Writes[User] {
	override def writes(user: User): JsValue = {
		toJson(
			Map("name" ->       user.name.getOrElse(""),
				"email" ->      user.email,
				"twitter" ->    user.twitter.getOrElse(""),
				"facebook" ->   user.facebook.getOrElse(""),
				"phone" ->      user.phone.getOrElse(""),
				"website" ->    user.website.getOrElse(""))
			)
	}
}