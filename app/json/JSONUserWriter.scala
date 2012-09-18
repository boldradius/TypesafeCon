package json

import models.User
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes

/** Writes an user to JSON */
object JsonUserWriter extends Writes[User] {
	override def writes(user: User): JsValue = {
		toJson(
			Map("id" ->           toJson(user.id.get),
				"firstName" ->    toJson(user.firstName.getOrElse("")),
				"lastName" ->     toJson(user.lastName.getOrElse("")),
				"email" ->        toJson(user.email),
				"twitter" ->      toJson(user.twitter.getOrElse("")),
				"facebook" ->     toJson(user.facebook.getOrElse("")),
				"phone" ->        toJson(user.phone.getOrElse("")),
				"website" ->      toJson(user.website.getOrElse("")),
				"latitude" ->     toJson(user.latitude.map(_.toString).getOrElse("")),
				"longitude" ->    toJson(user.longitude.map(_.toString).getOrElse("")),
				"locationTime" -> toJson(user.locationTime.map(_.toString()).getOrElse("")))
			)
	}
}