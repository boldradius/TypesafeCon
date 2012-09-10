package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models.GeneralMessage
import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import tools.TestTools.ValidResponse

class GeneralMessagesTest extends Specification {

	"The Add general message API call" should {
		
		"Return an error if the senderId is missing" in new GeneralMessagesTestCase  {
			
			running(FakeApplication()) {
					
				// Verify the result is a 400 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody("content" -> "If a message gets sent by nobody, can it still be read?"))
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
		
		"Return an error if the content is missing" in new GeneralMessagesTestCase  {
			
			running(FakeApplication()) {
					
				// Verify the result is a 400 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody("senderId" -> testUser.id.get.toString))
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
		
		"Create a new message when called with the proper parameters" in new GeneralMessagesTestCase  {
			
			running(FakeApplication()) {
				val content = "Hi, this is a test message. Neat, eh?"
					
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(POST, "/messages/general").withFormUrlEncodedBody("senderId" -> testUser.id.get.toString, "content" -> content))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our message was created
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")
						
						val id = result.toLong
						GeneralMessage.findById(id) match {
							case Some(message) =>
								testMessage = message
								message.content must beEqualTo(content)
								message.senderId must beEqualTo(testUser.id.get)
							case _ => failure("The message was not created")
						}
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
	}
}

trait GeneralMessagesTestCase extends After {
	
	var testUser:User = _
	var testMessage: GeneralMessage = _
	
	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com").create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) {
		testUser.delete
	}
}