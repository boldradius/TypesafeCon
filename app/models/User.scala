package models

import anorm._
import org.joda.time.DateTime
import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger
import java.util.Date

case class User(var id: Pk[Long],
				var email: String,
				var name: Option[String] = None,
				var twitter: Option[String] = None,
				var facebook: Option[String] = None,
				var phone: Option[String] = None,
				var website: Option[String] = None,
				var latitude: Option[BigDecimal] = None,
				var longitude: Option[BigDecimal] = None,
				var locationTime: Option[DateTime] = None) {
	
	/** Inserts the user into the DB
	  */
	def create = {
		Logger.debug("Creating User " + this)
		
		DB.withConnection { implicit connection =>
			val id = SQL("""insert into s1user (name, email, twitter, facebook, phone, website, latitude, longitude, locationtime) 
							values ({name}, {email}, {twitter}, {facebook}, {phone}, {website}, {latitude}, {longitude}, {locationTime})""").on(
						'name -> name,
						'email -> email,
						'twitter -> twitter,
						'facebook -> facebook,
						'phone -> phone,
						'website -> website,
						'latitude -> latitude.map(_.toString),
						'longitude -> longitude.map(_.toString),
						'locationTime -> locationTime.map(a => new Date(a.getMillis))).executeInsert()
						
			id.map {
				case id => {
					this.id = Id(id)
					this
				}
			}
		}
	}
	
	/** Updates the user on the DB
	  */
	def update = {
		Logger.debug("Updating User " + this)
		
		DB.withConnection { implicit connection =>
			SQL("""update s1user set 
						name = {name}, 
						email = {email}, 
						twitter = {twitter}, 
						facebook = {facebook}, 
						phone = {phone}, 
						website = {website}
					where id = {id}""").on(
				'name -> name,
				'email -> email,
				'twitter -> twitter,
				'facebook -> facebook,
				'phone -> phone,
				'website -> website,
				'id -> id).executeUpdate()
		} > 0
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
	
	def setLocation(latitude: BigDecimal, longitude: BigDecimal) {
		this.latitude = Some(latitude)
		this.longitude = Some(longitude)
		this.locationTime = Some(new DateTime())
	}
}

object User {
	
	def apply(email: String,
				name: Option[String],
				twitter: Option[String],
				facebook: Option[String],
				phone: Option[String],
				website: Option[String]):User  = {
		User(Id(0), email, name, twitter, facebook, phone, website)
	}
	
	def apply(email: String):User  = {
		User(Id(0), email)
	}
	
	private val user = {
		get[Pk[Long]]("id") ~
		get[Option[String]]("name") ~
		get[String]("email") ~
		get[Option[String]]("twitter") ~
		get[Option[String]]("facebook") ~ 
		get[Option[String]]("phone") ~ 
		get[Option[String]]("website") ~ 
		get[Option[String]]("latitude") ~ 
		get[Option[String]]("longitude") ~ 
		get[Option[Date]]("locationtime") map {
			case id ~ name ~ email ~ twitter ~ facebook ~ phone ~ website ~ latitude ~ longitude ~ locationTime => 
				User(id, email, name, twitter, facebook, phone, website, 
						latitude.map(BigDecimal(_)), longitude.map(BigDecimal(_)), 
						locationTime.map(new DateTime(_)))
		}
	}
		
	/** Fetches all Users
	  */
	def findAll = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user").as(user *)
		}
	}
	
	/** Fetches all Users with a location */
	def findWithLocation = {
		DB.withConnection { implicit connection =>
			SQL("select * from s1user where latitude is not null and longitude is not null and locationtime is not null").as(user *)
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