package models

import anorm._
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger
import java.util.Date

case class LinkedUser(user: User, link: Link)

case class User(var id: Pk[Long],
				var email: String,
				var firstName: Option[String] = None,
				var lastName: Option[String] = None,
				var twitter: Option[String] = None,
				var facebook: Option[String] = None,
				var phone: Option[String] = None,
				var website: Option[String] = None,
				var latitude: Option[BigDecimal] = None,
				var longitude: Option[BigDecimal] = None,
				var locationTime: Option[DateTime] = None,
				var contactEmailSent: Boolean = false) {
	
	// Display first name if present, otherwise email address before the @ sign
	def displayName = firstName.getOrElse(email.substring(0,email.indexOf("@")))
	
	def fullName = firstName match {
		case Some(fn) => fn + " " + lastName.getOrElse("")
		case _ => displayName
	}
	
	/** Inserts the user into the DB */
	def create = {
		Logger.debug("Creating User " + this)
		
		DB.withConnection { implicit connection =>
			val id = SQL("""insert into s1user (firstname, lastname, email, twitter, facebook, 
								phone, website, latitude, longitude, locationtime) 
							values ({firstName}, {lastName}, {email}, {twitter}, {facebook}, 
								{phone}, {website}, {latitude}, {longitude}, {locationTime})""").on(
						'firstName -> firstName,
						'lastName -> lastName,
						'email -> email,
						'twitter -> twitter,
						'facebook -> facebook,
						'phone -> phone,
						'website -> website,
						'latitude -> latitude.map(_.toString),
						'longitude -> longitude.map(_.toString),
						'locationTime -> locationTime.map(l => new Date(l.getMillis))).executeInsert()
						
			id.map {
				case id => {
					this.id = Id(id)
					this
				}
			}
		}
	}
	
	/** Updates the user on the DB */
	def update = {
		Logger.debug("Updating User " + this)
		
		DB.withConnection { implicit connection =>
			SQL("""update s1user set 
						firstname = {firstName},
						lastname = {lastName},
						email = {email}, 
						twitter = {twitter}, 
						facebook = {facebook}, 
						phone = {phone}, 
						website = {website},
						latitude = {latitude},
						longitude = {longitude},
						locationtime = {locationTime},
						contactEmailSent = {contactEmailSent}
					where id = {id}""").on(
				'firstName -> firstName,
				'lastName -> lastName,
				'email -> email,
				'twitter -> twitter,
				'facebook -> facebook,
				'phone -> phone,
				'website -> website,
				'latitude -> latitude.map(_.toString),
				'longitude -> longitude.map(_.toString),
				'locationTime -> locationTime.map(l => new Date(l.getMillis)),
				'contactEmailSent -> contactEmailSent,
				'id -> id).executeUpdate()
		} > 0
	}
	
	/** Deletes the speaker from the DB */
	def delete = {
		DB.withConnection { implicit connection =>
			SQL("delete from s1user where id = {id}")
						.on('id -> id)
						.executeUpdate()
		}
	}
	
	/** Updates the latitude and longitude of this user, and sets the locationTime */
	def setLocation(latitude: BigDecimal, longitude: BigDecimal) {
		this.latitude = Some(latitude)
		this.longitude = Some(longitude)
		this.locationTime = Some(new DateTime())
	}
}

object User {
	
	def apply(email: String,
				firstName: Option[String],
				lastName: Option[String],
				twitter: Option[String],
				facebook: Option[String],
				phone: Option[String],
				website: Option[String]):User  = {
		User(Id(0), email, firstName, lastName, twitter, facebook, phone, website)
	}
	
	def apply(email: String):User  = {
		User(Id(0), email)
	}
	
	/** Parses a row into a User object */
	private val user = {
		get[Pk[Long]]("id") ~
		get[Option[String]]("firstName") ~
		get[Option[String]]("lastName") ~
		get[String]("email") ~
		get[Option[String]]("twitter") ~
		get[Option[String]]("facebook") ~ 
		get[Option[String]]("phone") ~ 
		get[Option[String]]("website") ~ 
		get[Option[String]]("latitude") ~ 
		get[Option[String]]("longitude") ~ 
		get[Boolean]("contactEmailSent") ~ 
		get[Option[Date]]("locationtime") map {
			case id ~ firstName ~ lastName ~ email ~ twitter ~ facebook ~ phone ~ website ~ latitude ~ longitude ~ contactEmailSent ~ locationTime => 
				new User(id, email, firstName, lastName, twitter, facebook, phone, website, 
						latitude.map(BigDecimal(_)), longitude.map(BigDecimal(_)), 
						locationTime.map(new DateTime(_)), contactEmailSent)
		}
	}
		
	/** Fetches all Users */
	def findAll = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user").as(user *)
		}
	}
	
	/** Fetches all Users with email sent flag = false and at least one link to another user */
	def findAllNoContactEmailSent = {
		DB.withConnection { implicit connection =>
			SQL("""
				select * from s1user s
				where not contactEmailSent
				and (select count(*) from link where sourceid = s.id) > 0
			""").as(user *)
		}
	}
	
	/** Fetches all Users with a location */
	def findWithLocation = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user where latitude is not null and longitude is not null and locationtime is not null").as(user *)
		}
	}
	
	/** Fetches a user by id */
	def findById(id: Long) = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user where id = {id}").on('id -> id).as(user.singleOpt)
		}
	}
	
	/** Counts all users */
	def countAll = {
		DB.withConnection { implicit connection =>
			SQL("select count(*) from s1user").as(scalar[Long].single)
		}
	}
	
}