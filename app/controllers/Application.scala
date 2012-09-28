package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.Messages

import com.tindr.pusher.Pusher
import jobs.EmailJob._

object Application extends Controller {

	val instructionsForm = Form("email" -> email)
				
	def index = Action {
		implicit request => Ok(views.html.index())
	}

	def landing = Action {
		implicit request => Ok(views.html.landing())
	}

	// Sends an email with playground instructions to the email entered by the user
	def sendInstructions = Action {
		implicit request => {
			val userAgent = request.headers.get("user-agent").getOrElse("")
			
			// Bind data from request and validate
			instructionsForm.bindFromRequest.fold(
				formWithErrors => {
					// Respond with error, send form with errors
					BadRequest(views.html.playground(userAgent, formWithErrors))
				},
				email => {
					// All seems good, so send email and respond with success message
					sendEmail(email, Messages("email.welcome.subject"), "emailTemplates/welcome.en.html")
					Redirect(routes.Application.playground).flashing("success" -> Messages("success.instructions"))
				})
		}
	}

	def playground = Action {
		implicit request => {
			val userAgent = request.headers.get("user-agent").getOrElse("")
			Ok(views.html.playground(userAgent, instructionsForm))
		}
	}
}