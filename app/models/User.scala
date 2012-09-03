package models

import anorm._
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class User(var id: Pk[Long],
				name: Option[String],
				email: String,
				twitter: Option[String],
				facebook: Option[String],
				phone: Option[String],
				website: Option[String]) {
	
	/** Inserts the user into the DB
	  */
	def create = {
		Logger.debug("Creating User " + this)
		
		DB.withConnection { implicit connection =>
			val id = SQL("""insert into s1user (name, email, twitter, facebook, phone, website) 
							values ({name}, {email}, {twitter}, {facebook}, {phone}, {website})""").on(
						'name -> name,
						'email -> email,
						'twitter -> twitter,
						'facebook -> facebook,
						'phone -> phone,
						'website -> website).executeInsert()
						
			id.map {
				case id => {
					this.id = Id(id)
					this
				}
			}
		}
	}
	
		/** Deletes the speaker from the DB
	  */
	def delete = {
		DB.withConnection { implicit connection =>
			SQL("delete from s1user where id = {id}")
						.on('id -> id)
						.executeUpdate()
		}
	}
}

object User {
	
	def apply(name: Option[String],
				email: String,
				twitter: Option[String],
				facebook: Option[String],
				phone: Option[String],
				website: Option[String]):User  = {
		User(Id(0), name, email, twitter, facebook, phone, website)
	}
	
	def apply(email: String):User  = {
		User(Id(0), None, email, None, None, None, None)
	}
	
	private val user = {
		get[Pk[Long]]("id") ~
		get[Option[String]]("name") ~
		get[String]("email") ~
		get[Option[String]]("twitter") ~
		get[Option[String]]("facebook") ~ 
		get[Option[String]]("phone") ~ 
		get[Option[String]]("website") map {
			case id ~ name ~ email ~ twitter ~ facebook ~ phone ~ website => 
				User(id, name, email, twitter, facebook, phone, website)
		}
	}
		
	/** Fetches all Users
	  */
	def findAll = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user").as(user *)
		}
	}
	
	def findById(id: Long) = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user where id = {id}").on('id -> id).as(user.singleOpt)
		}
	}
	
	def findByEmail(email: String) = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user where email = {email}").on('email -> email).as(user.singleOpt)
		}
	}
	
	def countAll = {
		DB.withConnection { implicit connection =>
			SQL("select count(*) from s1user").as(scalar[Long].single)
		}
	}
	
}