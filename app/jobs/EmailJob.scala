package jobs

import com.typesafe.plugin._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import play.Configuration
import play.api.i18n.Lang
import play.api.Logger
import scala.io.Source

object EmailJob {
	
	private val from = current.configuration.getString("email.from").getOrElse("")
	
	def sendEmail(recipient: String, subject: String, templatePath: String) = Akka.future {
	
		val content = scala.io.Source.fromFile(templatePath).mkString
		println("Sending email with subject '" + subject + "' to " + recipient + " from " + from + " with content: '" + content + "'")
		
		val mail = use[MailerPlugin].email
		mail.setSubject(subject)
		mail.addRecipient(recipient)
		mail.addFrom(from)
		mail.sendHtml(content)
	}
}