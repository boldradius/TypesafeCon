package controllers

import anorm.Id
import play.api.data.Forms._
import play.api.data.Form
import play.api.mvc.Action
import play.api.Logger
import models.User
import scala.sys.process._
import json.JsonUserWriter
import play.api.data.format.Formatter
import play.api.data.FormError
import tools.BigDecimalFormatter

object Users extends APIController {

	private val userForm: Form[User] = Form(
		mapping(
			"email" -> email,
			"firstName" -> optional(text(maxLength = 255)),
			"lastName" -> optional(text(maxLength = 255)),
			"twitter" -> optional(text(maxLength = 255)),
			"facebook" -> optional(text(maxLength = 255)),
			"phone" -> optional(text(maxLength = 255)),
			"website" -> optional(text(maxLength = 255))) {
				User.apply
			} {
				_ => None // implement if necessary
			})

	private val locationForm = Form(
		mapping(
			"longitude" -> of[BigDecimal](BigDecimalFormatter),
			"latitude" -> of[BigDecimal](BigDecimalFormatter)) { Location.apply } { _ => None })

	def list(location: Option[Boolean]) = Action {
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

	def get(id: Long) = Action {
		implicit request =>
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

	def create = Action {
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

	def update(id: Long) = Action {
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

	// Uses ImageMagick to resize and convert images to JPG
	def uploadImage(userid: Long) = Action {
		implicit request =>
			{
				User.findById(userid) match {
					case None => Error("User not found")
					case Some(user) => {
						request.body.asMultipartFormData.get.file("picture") match {
							case None => MissingParam("picture")
							case Some(picture) => {
								try {
									val resizeCommand = "convert -resize 300x300 " + picture.ref.file.getAbsolutePath() + " ./public/img/user/" + user.id.get + ".jpg"
									val res = resizeCommand.!!

									// Copy generated file to asset folder
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

	def setLocation(userId: Long) = Action {
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
								if (user.update)
									Success("Success")
								else
									ServerError("The location could not be updated")
							})
					}
				}
			}
	}
}

case class Location(latitude: BigDecimal, longitude: BigDecimal)