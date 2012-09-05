package models
import org.joda.time.DateTime
import anorm.Pk
import play.api.db.DB
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import java.util.Date
import play.api.Logger

class EventMessage(
		id:Pk[Long],
		senderId: Long,
		content: String,
		senderName: String = "",
		sentTime: DateTime = new DateTime,
		eventId: Long,
		index: Long = 0) extends Message(id, senderId, content, senderName, sentTime, index, Some(eventId)){

	def nextIndex = "select coalesce(max(index),0) + 1 from message where eventid = " + eventId
}

object EventMessage {
	
	/** Parses a result into an EventMessage
	  */
	private val eventMessage = {
		get[Pk[Long]]("id") ~
		get[Long]("senderid") ~
		get[String]("content") ~
		get[String]("name") ~
		get[Date]("senttime") ~
		get[Long]("index") ~
		get[Long]("eventid") map {
			case id ~ senderId ~ content ~ senderName ~ sentTime ~ index ~ eventId =>
				new EventMessage(id, senderId, content, senderName, new DateTime(sentTime), eventId, index)
		}
	}
		
	/** Fetches all Event Messages
	 */
	def findAll(eventId: Long) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.name from message m
				inner join s1user u on u.id = m.senderid
				where eventid = {eventId}
				order by index
				""").on('eventId -> eventId).as(eventMessage *)
		}
	}
}