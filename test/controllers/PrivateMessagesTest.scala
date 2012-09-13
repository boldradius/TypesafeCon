package controllers

import org.specs2.mutable.After

import models._
import play.api.test.Helpers._
import play.api.test._

class PrivateMessagesTest extends APISpecification {

	"The Add private message API call" should {

		"Return error when the token is missing" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when the token is invalid" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id).withFormUrlEncodedBody("token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when the content is missing" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id).withFormUrlEncodedBody("token" -> validToken))
				verifyBadResult(result, "Missing parameter: content")
			}
		}

		"Return error when the sender does not exist" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/999999/" + user2.id).withFormUrlEncodedBody("content" -> "Hey Peter", "token" -> validToken))
				verifyBadResult(result, "Sender does not exist")
			}
		}

		"Return error when the user does not exist" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/999999").withFormUrlEncodedBody("content" -> "Hey Peter", "token" -> validToken))
				verifyBadResult(result, "User does not exist")
			}
		}

		"Create a new message when called with the proper parameters" in new PrivateMessagesTestCase {
			running(FakeApplication()) {
				val content = "Hey Peter"
					
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/private/" + user1.id + "/" + user2.id).withFormUrlEncodedBody("content" -> content, "token" -> validToken))
				verifyGoodResult(result) {
					response => {
						val id = response.result.toLong
						PrivateMessage.findById(id) match {
							case Some(message) =>
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(user1.id.get)
								message.toUserId must beEqualTo(Some(user2.id.get))
							case _ => failure("The message was not created")
						}
					}
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