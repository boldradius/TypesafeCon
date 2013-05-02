package controllers

import org.specs2.mutable.After

import models._
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
				val Some(result) = routeAndCall(FakeRequest(GET, "/users/" + john.id + "?token=" + validToken))
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
		
		"Retrieve a user and note for linked users" in new GetUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + jane.id.get + "?token=" + validToken + "&sourceid=" + john.id.get 
				val Some(result) = routeAndCall(FakeRequest(GET, url))
				verifyGoodResult(result){
					response => {
						response.status must beEqualTo("OK")
						response.result must contain(""""firstName":"Jane"""")
						response.result must contain(""""lastName":"Doe"""")
						response.result must contain(""""email":"jane@example.com"""")
						response.result must contain(""""twitter":"jane"""")
						response.result must contain(""""facebook":"JaneDoe"""")
						response.result must contain(""""phone":"987-1234567"""")
						response.result must contain(""""website":"example.com"""")
						response.result must contain(""""note":"Call Jane to discuss design"""")
					}
				}
			}
		}
		
		"Retrieve a user and no note for unlinked users" in new GetUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "?token=" + validToken + "&sourceid=" + jane.id.get
				val Some(result) = routeAndCall(FakeRequest(GET, url))
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
						response.result must not contain(""""note":""")
					}
				}
			}
		}
	}
}

trait GetUserTestCase extends After {
	
	var john:User = _
	var jane:User = _
	var peter:User = _
	var johnToJane:Link = _
	
	// Create a test user before the test case
	running(FakeApplication()) {
		john = User("john@example.com","John", "Doe", "john", "JohnDoe", "987-1234567", "example.com").create.get
		jane = User("jane@example.com","Jane", "Doe", "jane", "JaneDoe", "987-1234567", "example.com").create.get
		peter = User("peter@example.com","Peter", "Donald", "peter", "PeterDonald", "123-4567890", "peter.com").create.get
		
		johnToJane = Link(john.id.get, jane.id.get, "Call Jane to discuss design").create.get
	}
		
	// Remove the test data
	def after = running(FakeApplication()) { 
		// Remove users, links are deleted by cascade
		User.findById(john.id.get).map(_.delete)
		User.findById(jane.id.get).map(_.delete)
		User.findById(peter.id.get).map(_.delete)
	}
}