package controllers

import models._
import play.api.i18n.Messages
import jobs.EmailJob

object Links extends APIController {

	private def sendEmail(user: User, contacts: List[Link]) = {
		
		val subject = Messages("email.welcome.subject")
		val body = views.html.contacts(user, contacts)
		
		EmailJob.send(user.email, subject, body.toString)
		
		user.contactEmailSent = true
		user.update
	}
		
	def email = SecuredAction {
		implicit request => {
			
			val promises =
			for {
				// Fetch all users
				user <- User.findAllNoContactEmailSent
				
				// Fetch all linked users
				contacts = models.Links.find(user.id.get)
			
				// Send email 
				email = sendEmail(user, contacts)
				
			} yield email
			 
			Ok("Emails sent: " + promises.size)
		}
	}
}
