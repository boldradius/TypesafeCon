package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models.PrivateMessage
import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import tools.TestTools.ValidResponse

class PrivateMessagesTest extends Specification {

	"The Add private message API call" should {

		"Return error when the content is missing" in new PrivateMessagesTestCase {

			running(FakeApplication()) {

				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")

				// Verify the error message
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Missing parameter: content")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}

		"Return error when the sender does not exist" in new PrivateMessagesTestCase {

			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/999999/" + user2.id).withFormUrlEncodedBody("content" -> "Hey Peter"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")

				// Verify the error message
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Sender does not exist")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}

		"Return error when the user does not exist" in new PrivateMessagesTestCase {

			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/999999").withFormUrlEncodedBody("content" -> "Hey Peter"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")

				// Verify the error message
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("User does not exist")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}

		"Create a new message when called with the proper parameters" in new PrivateMessagesTestCase {

			running(FakeApplication()) {
				val content = "Hey Peter"

				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id).withFormUrlEncodedBody("content" -> content))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")

				// Sanity check to verify our message was created
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")

						val id = result.toLong
						PrivateMessage.findById(id) match {
							case Some(message) =>
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(user1.id.get)
								message.toUserId must beEqualTo(Some(user2.id.get))
							case _ => failure("The message was not created")
						}
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
	}
}

trait PrivateMessagesTestCase extends After {

	var user1, user2: User = _

	// Create test users before the test case
	running(FakeApplication()) {
		user1 = User("john@example.com").create.get
		user2 = User("peter@example.com").create.get
	}

	// Remove the test data
	def after = running(FakeApplication()) {
		user1.delete
		user2.delete
	}
}