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

class PrivateMessage(
		id:Pk[Long],
		senderId: Long,
		content: String,
		toUserId: Long,
		senderName: Option[String] = None,
		sentTime: DateTime = new DateTime,
		index: Long = 0) extends Message(id, senderId, content, senderName, sentTime, index, None, Some(toUserId)){

	/** Calculates the next index in the private messages between users A and B */
	def nextIndex = """select coalesce(max(index),0) + 1 from message 
						where (touserid  = %d and senderid = %d) 
						OR (touserid  = %d and senderid = %d)""".format(senderId, toUserId, toUserId, senderId)
}

object PrivateMessage {
	
	def apply(senderId:Long, toUserId: Long, content: String) = new PrivateMessage(Id(0), senderId, content, toUserId)
	
	/** Parses a result into a PrivateMessage */
	private val privateMessage = {
		get[Pk[Long]]("id") ~
		get[Long]("senderid") ~
		get[String]("content") ~
		get[Option[String]]("firstName") ~
		get[Option[String]]("lastName") ~
		get[Date]("senttime") ~
		get[Long]("index") ~
		get[Long]("touserid") map {
			case id ~ senderId ~ content ~ firstName ~ lastName ~ sentTime ~ index ~ toUserId =>
				new PrivateMessage(id, senderId, content, toUserId, displayName(firstName, lastName), new DateTime(sentTime), index)
		}
	}
		
	/** Fetches all Private Messages between users id1 and id2 */
	def findAll(id1: Long, id2: Long, fromIndex: Option[Long]) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.firstname, u.lastname from message m
				inner join s1user u on u.id = m.senderid
				where 
					index > {index}
					AND ((touserid  = {id1} and senderid = {id2}) 
					OR (touserid  = {id2} and senderid = {id1}))
				order by index
				""").on('id1 -> id1, 'id2 -> id2, 'index -> fromIndex.getOrElse(0)).as(privateMessage *)
		}
	}
	
	/** Fetches a Private Message by id */
	def findById(id: Long) = {
		DB.withConnection { implicit connection =>
			SQL("""
				select m.*, u.firstname, u.lastname from message m
				inner join s1user u on u.id = m.senderid
				where m.id = {id}
				""").on('id -> id).as(privateMessage singleOpt)
		}
	}
	
	/** Counts all Private Messages between two users */
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