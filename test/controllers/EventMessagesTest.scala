package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification
import models.EventMessage
import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import tools.TestTools.ValidResponse
import models.S1Event
import anorm.Id
import org.joda.time.DateTime

class EventMessagesTest extends Specification {

	"The Add event message API call" should {
		
		"Return error when the sender id is missing" in new EventMessagesTestCase  {
			
			running(FakeApplication()) {
				val content = "This event is awesome."
					
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("content" -> content))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Verify the error message
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Missing parameter: senderId")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Return error when the content is missing" in new EventMessagesTestCase  {
			
			running(FakeApplication()) {
					
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("senderId" -> testUser.id.get.toString))
				status(result) must beEqualTo(OK)
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
		
		"Return error when the event does not exist" in new EventMessagesTestCase  {
			
			running(FakeApplication()) {
				val content = "This event is awesome."
					
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/999999").withFormUrlEncodedBody("senderId" -> testUser.id.get.toString, "content" -> content))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our message was created
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")
						println(message)
						val id = result.toLong
						EventMessage.findById(id) match {
							case Some(message) =>
								testMessage = message
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(testUser.id.get)
								message.eventId must beEqualTo(Some(testEvent.id.get))
							case _ => failure("The message was not created")
						}
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Create a new message when called with the proper parameters" in new EventMessagesTestCase  {
			
			running(FakeApplication()) {
				val content = "This event is awesome."
					
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/event/" + testEvent.id).withFormUrlEncodedBody("senderId" -> testUser.id.get.toString, "content" -> content))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our message was created
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")
						println(message)
						val id = result.toLong
						EventMessage.findById(id) match {
							case Some(message) =>
								testMessage = message
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(testUser.id.get)
								message.eventId must beEqualTo(Some(testEvent.id.get))
							case _ => failure("The message was not created")
						}
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
	}
}

trait EventMessagesTestCase extends After {
	
	var testUser:User = _
	var testMessage: EventMessage = _
	var testEvent: S1Event = _
	
	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com").create.get
		testEvent = S1Event(Id(0), "TEST", "Not a real Event", "", new DateTime, new DateTime, "", Seq(1L)).create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) {
		testUser.delete
	}
}