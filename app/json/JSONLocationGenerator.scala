package json

import models.S1Event
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes
import models.User
import controllers.Location

/** Builds a JSON object with the details of the location */
object JSONLocationGenerator {
	def buildJson(location: Location, user: User): JsValue = {
		toJson(
			Map("userId" ->          toJson(user.id.get),
				"latitude" ->        toJson(location.latitude.toString),
				"longitude" ->       toJson(location.longitude.toString))
			)
	}
}