package controllers

import org.specs2.mutable.After

import models.GeneralMessage
import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest

class GeneralMessagesTest extends APISpecification {

	"The Add general message API call" should {

		"Return an error if the token is missing" in new GeneralMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general"))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return an error if the token is incorrect" in new GeneralMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody("token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return an error if the senderId is missing" in new GeneralMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody(
					"content" -> "If a message gets sent by nobody, can it still be read?",
					"token" -> validToken))
				verifyBadResult(result, "Missing parameter: senderId")
			}
		}

		"Return an error if the content is missing" in new GeneralMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody(
					"senderId" -> testUser.id.get.toString,
					"token" -> validToken))
				verifyBadResult(result, "Missing parameter: content")
			}
		}

		"Return an error if the sender does not exist" in new GeneralMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody(
					"senderId" -> "9999999",
					"content" -> "If a message gets sent by nobody, can it still be read?",
					"token" -> validToken))
				verifyBadResult(result, "User does not exist")
			}
		}

		"Create a new message when called with the proper parameters" in new GeneralMessagesTestCase {

			running(FakeApplication()) {
				val content = "Hi, this is a test message. Neat, eh?"

				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody(
					"senderId" -> testUser.id.get.toString, "content" -> content,
					"token" -> validToken))
				verifyGoodResult(result) {
					response =>
						{
							val id = response.result.toLong
							GeneralMessage.findById(id) match {
								case Some(message) =>
									message.content must beEqualTo(content)
									message.senderId must beEqualTo(testUser.id.get)
								case _ => failure("The message was not created")
							}
						}
				}
			}
		}
	}
}

trait GeneralMessagesTestCase extends After {

	var testUser: User = _

	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com").create.get
	}

	// Remove the test data
	def after = running(FakeApplication()) {
		testUser.delete
	}
}