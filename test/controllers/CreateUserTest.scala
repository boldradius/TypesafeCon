package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models.User
import play.api.test.Helpers._
import play.api.test._
import tools.TestTools.ValidResponse

class CreateUserTest extends Specification {
	
	"The Create User API call" should {
		
		"Return error when email is not provided" in new CreateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody("name" -> "John"))
					
				status(result) must equalTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				contentAsString(result) match {
					case ValidResponse(status, message, result) => message must equalTo("Missing parameter: email")
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		
		"Return error when creating user with an email already registered" in new CreateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody(
					"name" -> "John", 
					"email" -> "john@example.com"))
					
				val Some(result2) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody(
					"name" -> "Peter", 
					"email" -> "john@example.com"))
					
				status(result2) must equalTo(BAD_REQUEST)
				contentType(result2) must beSome("application/json")
				
				contentAsString(result2) match {
					case ValidResponse(status, message, result) => message must equalTo("Email is already registered")
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}	
		
		
		"Create a user when passed all valid parameters" in new CreateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody(
					"name" -> "John", 
					"twitter" -> "john", 
					"facebook" -> "johnDoe",
					"phone" -> "987-6543210", 
					"email" -> "john@example.com", 
					"website" -> "http://example.com"))
				status(result) must equalTo(OK)
				contentType(result) must beSome("application/json")
				
				val IdResponse = """.+"id":(\d+).+""".r
				
				contentAsString(result) match {
					case IdResponse(id) => 
						User.findById(id.toLong) match {
							case None => failure("User with id " + id + " was not found")
							case Some(user) => {
								user.name must beEqualTo(Some("John"))
								user.twitter must beEqualTo(Some("john"))
								user.facebook must beEqualTo(Some("johnDoe"))
								user.phone must beEqualTo(Some("987-6543210"))
								user.email must beEqualTo("john@example.com")
								user.website must beEqualTo(Some("http://example.com"))
							}
						}
					case json => failure("Response does not contain valid id: " + json)
				}
				
			}
		}
	}
}

trait CreateUserTestCase extends After {
	
	// Remove the test data
	def after = running(FakeApplication()) { 
		User.findByEmail("john@example.com").map(_.delete)
	}
}