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

	def nextIndex = "select coalesce(max(index),0) + 1 from message m where eventid = " + eventId
}

object GeneralMessage {
	
	/** Parses a result into a GeneralMessage
	  */
	private val generalMessage = {
		get[Pk[Long]]("id") ~
		get[Long]("senderid") ~
		get[String]("content") ~
		get[String]("name") ~
		get[Date]("senttime") ~
		get[Long]("index")  map {
			case id ~ senderId ~ content ~ senderName ~ sentTime ~ index  =>
				new GeneralMessage(id, senderId, content, senderName, new DateTime(sentTime), index)
		}
	}
		
	/** Fetches all General Messages
	 */
	def findAll = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.name from message m
				inner join s1user u on u.id = m.senderid
				where eventid is null and touserid is null
				order by index
				""").as(generalMessage *)
		}
	}
	
	/** Counts all GeneralMessages
	 */
	def countAll = {
		DB.withConnection { implicit connection =>
			SQL("select count(*) from message where eventid is null and touserid is null").as(scalar[Long].single)
		}
	}
}