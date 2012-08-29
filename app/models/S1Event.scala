package models

import java.util.Date
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class S1Event(var id: Pk[Long],
	code: String,
	title: String,
	description: String,
	start: DateTime,
	end: DateTime,
	location: String,
	var speakerIds: Seq[Long]) {

	/** Inserts the event into the DB
	  */
	def create = {
		Logger.debug("Creating Event " + this)

		DB.withConnection { implicit connection =>
			val id = SQL("""insert into event (code, title, description, starttime, endtime, location) 
							values ({code}, {title}, {description}, {start}, {end}, {location})""").on(
				'code -> code,
				'title -> title,
				'description -> description,
				'start -> new Date(start.getMillis()),
				'end -> new Date(end.getMillis()),
				'location -> location).executeInsert()

			id.map {
				case id => {
					this.id = Id(id)
					
					// Add speakers to event
					speakerIds.foreach( sid =>
						SQL("""insert into speakerevent (speakerid, eventid) values ({speakerid}, {eventid})""").on(
						'speakerid -> sid,
						'eventid -> id).executeInsert()
					)
					
					this
				}
			}
		}
	}
	
	
	/** Deletes the event from the DB
	  */
	def delete = {
		DB.withConnection { implicit connection =>
			SQL("delete from event where id = {id}")
						.on('id -> id)
						.executeUpdate()
		}
	}

	def addSpeaker(speakerId: Long) = {
		speakerIds = speakerIds :+ speakerId
		this
	}
}

object S1Event {

	/** Parses a row into an (Event,Speaker) tuple, representing the engagement of a speaker to an event
	  */
	private val eventWithSpeakers = {
		get[Pk[Long]]("eventid") ~
		get[String]("code") ~
		get[String]("etitle") ~
		get[String]("description") ~
		get[Date]("starttime") ~
		get[Date]("endtime") ~
		get[String]("location") ~
		get[Pk[Long]]("speakerid") ~
		get[String]("name") ~
		get[String]("stitle") ~
		get[String]("about") ~
		get[Option[String]]("email") ~ 
		get[Option[String]]("twitter") ~ 
		get[Option[String]]("url") map {
			case eventid ~ code ~ etitle ~ description ~ start ~ end ~ location ~ speakerid ~ name ~ stitle ~ about ~ email ~ twitter ~ url =>
				(S1Event(eventid, code, etitle, description, new DateTime(start), new DateTime(end), location, Nil), 
				Speaker(speakerid, name, stitle, about, email, twitter, url))
		}
	}

	/** Fetches all Events
	  */
	def findAll = {
		DB.withConnection { implicit connection =>
			val eventSpeakers = SQL("""
					select e.title etitle, s.title stitle, * 
					from event e
					inner join speakerevent se on se.eventid = e.id
					inner join speaker s on s.id = se.speakerid
					""").as(eventWithSpeakers *)
			eventSpeakers.foldLeft(List[S1Event]())((events, engagement) => combine(events, engagement._1, engagement._2))
		}
	}
	
	/** Combines the list of events with the event-speaker engagement, such that after the call:
	 *    a) events will contain event (if it did not contain it, it is added)
	 *    b) event will have speaker in it's list of speaker ids
	 */
	private def combine(events: List[S1Event], event: S1Event, speaker: Speaker) = {
		events.find(_.id == event.id) match {
			case Some(e) => 
				e.addSpeaker(speaker.id.get) 
				events
			case None => 
				events :+ event.addSpeaker(speaker.id.get)
		}
	}
		
	def countAll = {
		DB.withConnection { implicit connection =>
			SQL("select count(*) from event").as(scalar[Long].single)
		}
	}
}