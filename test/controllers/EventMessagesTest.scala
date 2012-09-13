package controllers

import org.joda.time.DateTime
import org.specs2.mutable.After

import anorm.Id
import models.EventMessage
import models.S1Event
import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest

class EventMessagesTest extends APISpecification {
	
	"The Add event message API call" should {
		
		"Return error when the token is missing" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when the token is invalid" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when the sender id is missing" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("token" -> validToken))
				verifyBadResult(result, "Missing parameter: senderId")
			}
		}
		
		"Return error when the content is missing" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("senderId" -> testUser.id.get.toString, "token" -> validToken))
				verifyBadResult(result, "Missing parameter: content")
			}
		}
		
		"Return error when the event does not exist" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/999999").withFormUrlEncodedBody("senderId" -> testUser.id.get.toString, "content" -> "Test", "token" -> validToken))
				verifyBadResult(result, "Event does not exist")
			}
		}
		
		"Create a new message when called with the proper parameters" in new EventMessagesTestCase  {
			running(FakeApplication()) {
				val content = "This event is awesome."
					
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody(
						"senderId" -> testUser.id.get.toString, "content" -> content, "token" -> validToken))
				verifyGoodResult(result) {
					response => {
						val id = response.result.toLong
						EventMessage.findById(id) match {
							case Some(message) =>
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(testUser.id.get)
								message.eventId must beEqualTo(Some(testEvent.id.get))
							case _ => failure("The message was not created")
						}
					}
				}
			}
		}
	}
}

trait EventMessagesTestCase extends After {
	
	var testUser:User = _
	var testEvent: S1Event = _
	
	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com").create.get
		testEvent = S1Event(Id(0), "TEST", "Not a real Event", "", new DateTime, new DateTime, "", Seq(1L)).create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) {
		testUser.delete
		testEvent.delete
	}
}