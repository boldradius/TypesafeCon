package models

import anorm._
import org.joda.time.DateTime

case class Speaker(var id: Pk[Long],
				name: String,
				title: String,
				about: String,
				email: String,
				twitter: String,
				url: String) {

}

object Speaker {}