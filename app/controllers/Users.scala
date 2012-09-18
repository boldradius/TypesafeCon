package controllers

import scala.sys.process._
import com.tindr.pusher.Pusher
import json.JSONLocationGenerator._
import json.JsonUserWriter
import models.User
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.data.Form
import play.api.data.FormError
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.Logger
import tools.BigDecimalFormatter
import play.api.mvc.Request
import play.api.mvc.Result

object Users extends APIController {

	private val userForm: Form[User] = Form(
		mapping(
			"email" -> email,
			"firstName" -> optional(text(maxLength = 255)),
			"lastName" -> optional(text(maxLength = 255)),
			"twitter" -> optional(text(maxLength = 255)),
			"facebook" -> optional(text(maxLength = 255)),
			"phone" -> optional(text(maxLength = 255)),
			"website" -> optional(text(maxLength = 255))) { User.apply } { _ => None })

	private val locationForm = Form(
		mapping(
			"latitude" -> of[BigDecimal](BigDecimalFormatter),
			"longitude" -> of[BigDecimal](BigDecimalFormatter)) { Location.apply } { _ => None })

	/** Return a list of users. If location is set, search for users with/without a location. Otherwise, return all. */
	def list(location: Option[Boolean]) = SecuredAction {
		try {
			val users = location match {
				case Some(true) => User.findWithLocation
				case _ => User.findAll
			}

			Success(users, "users")(JsonUserWriter)
		} catch {
			case t: Throwable => ServerError(t.getMessage)
		}
	}

	/** Return a specific user */
	def get(id: Long) = SecuredAction {
		{
			try {
				User.findById(id) match {
					case Some(user) => Success(user)(JsonUserWriter)
					case _ => Error("User not found")
				}
			} catch {
				case t: Throwable =>
					Logger.error("Error creating user", t)
					ServerError(t.getMessage)
			}
		}
	}

	/** Create a new user. Emails must be unique. */
	def create = SecuredAction {
		implicit request =>
			{
				try {
					userForm.bindFromRequest.fold(

						// Error on the user parameters, send proper json error
						formWithErrors => ParamError(formWithErrors.errors(0)),

						// Parameters are fine, create user
						user => {
							if (User.findByEmail(user.email).isDefined) {
								Error("Email is already registered")
							} else {
								user.create match {
									case Some(newUser) => Success(Map("id" -> newUser.id.get))
									case None => Error("User cannot be created")
								}
							}
						})
				} catch {
					case t: Throwable =>
						Logger.error("Error creating user", t)
						ServerError(t.getMessage)
				}
			}
	}

	/** Update a user. Replaces the user information with the values submitted on the form fields. */
	def update(id: Long) = SecuredAction {
		implicit request =>
			{
				try {
					User.findById(id) match {
						case None => Error("User not found")
						case Some(existingUser) =>
							userForm.bindFromRequest.fold(

								// Error on the user parameters, send proper json error
								formWithErrors => ParamError(formWithErrors.errors(0)),

								// Parameters are fine, update user
								user => {
									// Verify if the email is already registered under another user
									User.findByEmail(user.email) match {
										case Some(user) if (user.id.get != id) => Error("Email is already registered")
										case _ => {
											existingUser.firstName = user.firstName
											existingUser.lastName = user.lastName
											existingUser.email = user.email
											existingUser.twitter = user.twitter
											existingUser.facebook = user.facebook
											existingUser.phone = user.phone
											existingUser.website = user.website
											if (existingUser.update)
												Success("Success")
											else
												ServerError("The user could not be updated")
										}
									}
								})
					}
				} catch {
					case t: Throwable =>
						Logger.error("Error updating user", t)
						ServerError(t.getMessage)
				}
			}
	}

	/** Update the user image with the one provided in the "picture" field of the form data. */
	def uploadImage(userid: Long) = SecuredAction {
		implicit request =>
			{
				User.findById(userid) match {
					case None => Error("User not found")
					case Some(user) => {
						request.body.asMultipartFormData.get.file("picture") match {
							case None => MissingParam("picture")
							case Some(picture) => {
								try {
									// Use ImageMagick to resize and convert images to JPG
									val resizeCommand = "convert -resize 300x300 " + picture.ref.file.getAbsolutePath() + " ./public/img/user/" + user.id.get + ".jpg"
									val res = resizeCommand.!!

									// Copy generated file to asset folder - play does not refresh automatically when in production mode
									val cpCommand = "cp ./public/img/user/" + user.id.get + ".jpg target/scala-2.9.1/classes/public/img/user"
									val res2 = cpCommand.!!

									Success("")
								} catch {
									case e => {
										Logger.error("Error processing image", e)
										Error("The image could not be processed")
									}
								}
							}
						}
					}
				}
			}
	}

	/** Updates the user location to the latitude and longitude provided. */
	def setLocation(userId: Long) = SecuredAction {
		implicit request =>
			{
				// Verify user exists
				User.findById(userId) match {
					case None => Error("User not found")
					case Some(user) => {
						// Grab data from the request, validate
						locationForm.bindFromRequest.fold(

							// Error on the user parameters, send proper json error
							formWithErrors => ParamError(formWithErrors.errors(0)),

							// Parameter are ok, update user with new location and send response
							location => {
								user.setLocation(location.latitude, location.longitude)
								if (user.update) {
									Pusher().trigger("locations", "newLocation", buildJson(location, user).toString())
									Success("Success")
								} else {
									ServerError("The location could not be updated")
								}
							})
					}
				}
			}
	}
}

case class Location(latitude: BigDecimal, longitude: BigDecimal)