package models

import java.util.Date
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class S1Event(var id: Pk[Long],
	title: String,
	description: String,
	start: DateTime,
	end: DateTime,
	location: String,
	speakerId: Long) {

	/** Inserts the event into the DB
	  */
	def create = {
		Logger.debug("Creating Event " + this)

		DB.withConnection { implicit connection =>
			val id = SQL("""insert into event (title, description, starttime, endtime, location, speakerid) 
							values ({title}, {description}, {start}, {end}, {location}, {speakerid})""").on(
				'title -> title,
				'description -> description,
				'start -> new Date(start.getMillis()),
				'end -> new Date(end.getMillis()),
				'location -> location,
				'speakerid -> speakerId).executeInsert()

			id.map {
				case id => {
					this.id = Id(id)
					this
				}
			}
		}
	}

}

object S1Event {

	/** Parses a row into an event
	  */
	private val event = {
		get[Pk[Long]]("id") ~
			get[String]("title") ~
			get[String]("description") ~
			get[Date]("starttime") ~
			get[Date]("endtime") ~
			get[String]("location") ~
			get[Long]("speakerid") map {
				case id ~ title ~ description ~ start ~ end ~ location ~ speakerId =>
					S1Event(id, title, description, new DateTime(start), new DateTime(end), location, speakerId)
			}
	}

	/** Fetches all Events
	  */
	def findAll = {
		DB.withConnection { implicit connection =>
			SQL("select * from event").as(event *)
		}
	}
}