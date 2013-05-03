package jobs

import com.typesafe.plugin._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import play.Configuration
import play.api.i18n.Lang
import play.api.Logger
import scala.io.Source
import scala.collection.mutable.StringBuilder

object EmailJob {
	
	private val from = current.configuration.getString("email.from").getOrElse("")
	
	def sendEmail(recipient: String, subject: String, templatePath: String) = {
		val body = scala.io.Source.fromFile(templatePath).mkString
		send(recipient, subject, body)
	}
	
	def send(recipient: String, subject: String, body: String) = Akka.future {
		val mail = use[MailerPlugin].email
		mail.setSubject(subject)
		mail.addRecipient(recipient)
		mail.addFrom(from)
		mail.sendHtml(body)
	}
	
}