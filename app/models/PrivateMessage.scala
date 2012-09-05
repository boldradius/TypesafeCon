package models

import org.joda.time.DateTime
import anorm.Pk
import play.api.db.DB
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import java.util.Date
import play.api.Logger

class PrivateMessage(
		id:Pk[Long],
		senderId: Long,
		content: String,
		toUserId: Long,
		senderName: String = "",
		sentTime: DateTime = new DateTime,
		index: Long = 0) extends Message(id, senderId, content, senderName, sentTime, index, None, Some(toUserId)){

	def nextIndex = """select coalesce(max(index),0) + 1 from message 
						where (touserid  = %d and senderid = %d) 
						OR (touserid  = %d and senderid = %d)""".format(senderId, toUserId, toUserId, senderId)
}

object PrivateMessage {
	
	/** Parses a result into a PrivateMessage
	  */
	private val privateMessage = {
		get[Pk[Long]]("id") ~
		get[Long]("senderid") ~
		get[String]("content") ~
		get[String]("name") ~
		get[Date]("senttime") ~
		get[Long]("index") ~
		get[Long]("touserid") map {
			case id ~ senderId ~ content ~ senderName ~ sentTime ~ index ~ toUserId =>
				new PrivateMessage(id, senderId, content, toUserId, senderName, new DateTime(sentTime), index)
		}
	}
		
	/** Fetches all Private Messages between users id1 and id1
	 */
	def findAll(id1: Long, id2: Long) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.name from message m
				inner join s1user u on u.id = m.senderid
				where (touserid  = {id1} and senderid = {id2}) 
				OR (touserid  = {id2} and senderid = {id1})
				order by index
				""").on('id1 -> id1, 'id2 -> id2).as(privateMessage *)
		}
	}
	
		/** Counts all GeneralMessages
	 */
	def countAll(id1: Long, id2: Long)  = {
		DB.withConnection { implicit connection =>
			SQL("""
				select count(*) from message 
				where (touserid  = {id1} and senderid = {id2}) 
				OR (touserid  = {id2} and senderid = {id1})
				""").on('id1 -> id1, 'id2 -> id2).as(scalar[Long].single)
		}
	}
	
}