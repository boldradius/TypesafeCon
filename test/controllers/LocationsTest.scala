package controllers

import org.joda.time.DateTime
import org.specs2.mutable.After
import org.specs2.mutable.Specification
import anorm.Id
import models.Speaker
import play.api.test.Helpers._
import play.api.test._
import tools.TestTools.ValidResponse
import tools.LoremIpsum
import models.User

class LocationsTest extends Specification {

	"The Set Location API call" should {
		"Return error if the latitude is missing" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("longitude" -> "0"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Missing parameter: latitude")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Return error if the longitude is missing" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "0"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Missing parameter: longitude")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Return error if the user does not exist" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/999999/location").withFormUrlEncodedBody("latitude" -> "0", "longitude" -> "0"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("User not found")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Return error if the longitude is invalid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "10", "longitude" -> "0a"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Invalid parameter: longitude")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Return error if the latitude is invalid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "10%", "longitude" -> "0"))
				status(result) must beEqualTo(BAD_REQUEST)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("ERROR")
						message must beEqualTo("Invalid parameter: latitude")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
		
		"Succeed when the location is valid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "42", "longitude" -> "42"))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
	}
	
	"The List Locations API call" should {
		
		"Retrieve a list of locations" in new LocationsTestCase  {
			
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(GET, "/users?location=1"))
				status(result) must beEqualTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				contentAsString(result) match {
					case ValidResponse(status, message, result) => {
						status must beEqualTo("OK")
						result must contain("\"locationTime\":\"" + testUser.locationTime.get +"\"")
						result must contain("\"latitude\":\"45.4271\"")
						result must contain("\"longitude\":\"-75.7624\"")
					}
					case content => failure("Invalid response format: '" + content + "'")
				}
			}
		}
	}
}

trait LocationsTestCase extends After {
	
	var testUser: User = _
	
	// Create a couple test entries to verify the results
	running(FakeApplication()) {
		val user = User("john@example.com")
		user.setLocation(BigDecimal("45.4271"), BigDecimal("-75.7624"))
		testUser = user.create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) { 
		testUser.delete
	}
}