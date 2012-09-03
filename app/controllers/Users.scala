package controllers

import anorm.Id
import play.api.data.Forms._
import play.api.data.Form
import play.api.mvc.Action
import play.api.Logger
import models.User
import scala.sys.process._

object Users extends APIController {

	private val userForm: Form[User] = Form(
		mapping(
			"name" -> optional(text(maxLength = 255)),
			"email" -> email.verifying("Email is already registered", email => User.findByEmail(email).isEmpty), // Verify uniqueness of email
			"twitter" -> optional(text(maxLength = 255)),
			"facebook" -> optional(text(maxLength = 255)),
			"phone" -> optional(text(maxLength = 255)),
			"website" -> optional(text(maxLength = 255))) {
				User.apply
			} {
				user => None // TODO implement if necessary
			})

	def create = Action {
		implicit request =>
			{
				try {
					userForm.bindFromRequest.fold(

						// Error on the user parameters, send proper json error
						formWithErrors => ParamError(formWithErrors.errors(0)),

						// Parameters are fine, create user
						user => {
							user.create match {
								case Some(newUser) => Success(Map("id" -> newUser.id.get))
								case None => Error("User cannot be created")
							}
						})
				} catch {
					case t: Throwable =>
						Logger.error("Error creating user", t)
						ServerError(t.getMessage)
				}
			}
	}
	
	def update(id: Long) = TODO

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
									val resizeCommand = "convert -resize 300x300 " + picture.ref.file.getAbsolutePath() + " ./public/img/profile/" + user.id.get + ".jpg"
									val res = resizeCommand.!!

									// Copy generated file to asset folder
									val cpCommand = "cp ./public/img/profile/" + user.id.get + ".jpg target/scala-2.9.1/classes/public/img/profile"
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
}

