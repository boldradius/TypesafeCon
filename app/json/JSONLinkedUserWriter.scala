package json

import models._
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes

object JsonLinkedUserWriter extends Writes[LinkedUser] {
	override def writes(linkedUser: LinkedUser): JsValue = {
		toJson(
			Map("id" ->           toJson(linkedUser.user.id.get),
				"firstName" ->    toJson(linkedUser.user.firstName.getOrElse("")),
				"lastName" ->     toJson(linkedUser.user.lastName.getOrElse("")),
				"email" ->        toJson(linkedUser.user.email),
				"twitter" ->      toJson(linkedUser.user.twitter.getOrElse("")),
				"facebook" ->     toJson(linkedUser.user.facebook.getOrElse("")),
				"phone" ->        toJson(linkedUser.user.phone.getOrElse("")),
				"website" ->      toJson(linkedUser.user.website.getOrElse("")),
				"latitude" ->     toJson(linkedUser.user.latitude.map(_.toString).getOrElse("")),
				"longitude" ->    toJson(linkedUser.user.longitude.map(_.toString).getOrElse("")),
				"locationTime" -> toJson(linkedUser.user.locationTime.map(_.toString()).getOrElse("")),
				"note" ->         toJson(linkedUser.link.note.getOrElse(""))
			)
		)
	}
}