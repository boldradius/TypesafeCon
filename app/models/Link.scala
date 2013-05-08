package models

import anorm._
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger
import java.util.Date

case class Link(source: User, 
		target: User,
		note: Option[String]) {

	/** Inserts the user into the DB */
	def create = {
		Logger.debug("Creating User " + this)
		
		DB.withConnection { implicit connection =>
			val id = SQL("""insert into link (sourceid, targetid, note) 
							values ({sourceid}, {targetid}, {note})""").on(
						'sourceid -> source.id,
						'targetid -> target.id,
						'note -> note).executeInsert()
						
			id.map(_ => this)
		}
	}
	
	def delete = {
		DB.withConnection { implicit connection =>
			SQL("delete from link where sourceid = {sourceid} and targetid = {targetid}").on(
				'sourceid -> source.id,
				'targetid -> target.id)
				.executeUpdate()
		}
	}
}

object Links {
	private val link = {
		get[Long]("sourceid") ~
		get[Long]("targetid") ~
		get[Option[String]]("note") map {
			case sourceid ~ targetid ~ note => Link(User.findById(sourceid).get, User.findById(targetid).get, note)
		}
	}
	
	def find(sourceid: Long, targetid: Long) = {
		DB.withConnection { implicit connection =>
			SQL("select * from link where sourceid = {sourceid} and targetid = {targetid}").on(
				'sourceid -> sourceid,
				'targetid -> targetid)
				.as(link.singleOpt)
		}
	}
	
	def find(sourceid: Long) = {
		DB.withConnection { implicit connection =>
			SQL("select * from link where sourceid = {sourceid}").on(
				'sourceid -> sourceid)
				.as(link.*)
		}
	}
	
		
}