package models

import org.joda.time.DateTime
import anorm.Pk
import play.api.db.DB
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import java.util.Date
import play.api.Logger
import Message._

class EventMessage(
		id:Pk[Long],
		senderId: Long,
		content: String,
		eventId: Long,
		senderName: Option[String] = None,
		sentTime: DateTime = new DateTime,
		index: Long = 0) extends Message(id, senderId, content, senderName, sentTime, index, Some(eventId)){

	def nextIndex = "select coalesce(max(index),0) + 1 from message where eventid = " + eventId
}

object EventMessage {
	
	def apply(senderId: Long, eventId: Long, content: String) = new EventMessage(Id(0), senderId, content, eventId)
	
	/** Parses a result into an EventMessage
	  */
	private val eventMessage = {
		get[Pk[Long]]("id") ~
		get[Long]("senderid") ~
		get[String]("content") ~
		get[Option[String]]("firstname") ~
		get[Option[String]]("lastname") ~
		get[Date]("senttime") ~
		get[Long]("index") ~
		get[Long]("eventid") map {
			case id ~ senderId ~ content ~ firstName ~ lastName ~ sentTime ~ index ~ eventId =>
				new EventMessage(id, senderId, content, eventId, displayName(firstName, lastName), new DateTime(sentTime), index)
		}
	}
		
	/** Fetches all Event Messages
	 */
	def findAll(eventId: Long, fromIndex: Option[Long]) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.firstname, u.lastname from message m
				inner join s1user u on u.id = m.senderid
				where eventid = {eventId}
					and index > {index}
				order by index
				""").on('eventId -> eventId, 'index -> fromIndex.getOrElse(0)).as(eventMessage *)
		}
	}
	
	/** Fetches an Event Message by id */
	def findById(id: Long) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.firstname, u.lastname from message m
				inner join s1user u on u.id = m.senderid
				where m.id = {id}
				""").on('id -> id).as(eventMessage singleOpt)
		}
	}
}