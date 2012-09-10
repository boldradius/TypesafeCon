package models

import org.joda.time.DateTime

import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

abstract class Message(
	var id: Pk[Long],
	val senderId: Long,
	val content: String,
	val senderName: Option[String],
	val sentTime: DateTime,
	val index: Long,
	val eventId: Option[Long] = None,
	val toUserId: Option[Long] = None) {

	def create = {
		Logger.debug("Creating Message " + this)

		DB.withConnection { implicit connection =>
			val id = SQL("""insert into message (senderId, content, eventid, touserid, index) 
							values ({senderId}, {content}, {eventId}, {toUserId}, (""" + nextIndex + "))").on(
				'senderId -> senderId,
				'content -> content,
				'eventId -> eventId,
				'toUserId -> toUserId).executeInsert()

			id.map {
				case id => {
					this.id = Id(id)
					this
				}
			}
		}
	}

	/**
	 * Deletes the message from the DB
	 */
	def delete = {
		DB.withConnection { implicit connection =>
			SQL("delete from message where id = {id}")
				.on('id -> id)
				.executeUpdate()
		}
	}

	/**
	 * Returns the SQL required to calculate the next index on the message context
	 */
	def nextIndex: String

}