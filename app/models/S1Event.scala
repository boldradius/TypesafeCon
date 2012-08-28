package models

import anorm._
import org.joda.time.DateTime

case class S1Event(var id: Pk[Long],
				title: String,
				description: String,
				start: DateTime,
				end: DateTime,
				location: String,
				speakerId: Long) {
}

object S1Event {}