package controllers

import org.specs2.mutable.After

import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest

class LocationsTest extends APISpecification {

	"The Set Location API call" should {
		"Return error if the token is missing" in new LocationsTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location"))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error if the token is invalid" in new LocationsTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error if the latitude is missing" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("longitude" -> "0", "token" -> validToken))
				verifyBadResult(result, "Missing parameter: latitude")
			}
		}
		
		"Return error if the longitude is missing" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "0", "token" -> validToken))
				verifyBadResult(result, "Missing parameter: longitude")
			}
		}
		
		"Return error if the user does not exist" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/999999/location").withFormUrlEncodedBody("latitude" -> "0", "longitude" -> "0", "token" -> validToken))
				verifyBadResult(result, "User not found")
			}
		}
		
		"Return error if the longitude is invalid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "10", "longitude" -> "0a", "token" -> validToken))
				verifyBadResult(result, "Invalid parameter: longitude")
			}
		}
		
		"Return error if the latitude is invalid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "10%", "longitude" -> "0", "token" -> validToken))
				verifyBadResult(result, "Invalid parameter: latitude")
			}
		}
		
		"Succeed when the location is valid" in new LocationsTestCase {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id + "/location").withFormUrlEncodedBody("latitude" -> "42", "longitude" -> "42", "token" -> validToken))
				verifyGoodResult(result)(_ => ok)
			}
		}
	}
	
	"The List Locations API call" should {
		
		"Retrieve a list of locations" in new LocationsTestCase  {
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(GET, "/users?location=1&token=" + validToken))
				verifyGoodResult(result) {
					response => {
						response.result must contain("\"locationTime\":\"" + testUser.locationTime.get +"\"")
						response.result must contain("\"latitude\":\"45.4271\"")
						response.result must contain("\"longitude\":\"-75.7624\"")
					}
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