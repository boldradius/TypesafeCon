package controllers

import anorm.Id
import play.api.data.Forms._
import play.api.data.Form
import play.api.mvc.Action
import play.api.Logger
import models.User

object Users extends APIController {

	private val userForm: Form[User] = Form(
		mapping(
			"name" -> optional(text(maxLength = 255)),
			"email" -> email.verifying("Email is already registered", email => User.findByEmail(email).isEmpty), // Verify uniqueness of email
			"twitter" -> optional(text(maxLength = 255)),
			"facebook" -> optional(text(maxLength = 255)),
			"phone" -> optional(text(maxLength = 255)),
			"website" -> optional(text(maxLength = 255)))
			{
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

}

