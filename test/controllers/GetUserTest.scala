package controllers

import org.specs2.mutable.After

import models.User
import play.api.test.Helpers._
import play.api.test.FakeApplication
import play.api.test.FakeRequest
import tools.StringTools._

class GetUserTest extends APISpecification {

	"The Get User API call" should {
		
		"Return error when token is missing" in new GetUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(GET, "/users/1000000"))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when token is invalid" in new GetUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(GET, "/users/1000000?token=" + invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}
		
		"Return error when requesting an inexisting id" in new GetUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(GET, "/users/1000000?token=" + validToken))
				verifyBadResult(result, "User not found")
			}
		}
		
		"Retrieve a user when provided with an existing id" in new GetUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(GET, "/users/" + testUser.id + "?token=" + validToken))
				verifyGoodResult(result){
					response => {
						response.status must beEqualTo("OK")
						response.result must contain(""""firstName":"John"""")
						response.result must contain(""""lastName":"Doe"""")
						response.result must contain(""""email":"john@example.com"""")
						response.result must contain(""""twitter":"john"""")
						response.result must contain(""""facebook":"JohnDoe"""")
						response.result must contain(""""phone":"987-1234567"""")
						response.result must contain(""""website":"example.com"""")
					}
				}
			}
		}
	}
}

trait GetUserTestCase extends After {
	
	var testUser:User = _
	
	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com","John", "Doe", "john", "JohnDoe", "987-1234567", "example.com").create.get
	}
		
	// Remove the test data
	def after = running(FakeApplication()) { 
		User.findById(testUser.id.get).map(_.delete)
	}
}