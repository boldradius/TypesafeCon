package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models._
import play.api.test.Helpers._
import play.api.test._
import tools.StringTools._

class LinkUserTest extends APISpecification {

	
	"The Linkup User API call" should {

		"Return error when token is not provided" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/" + peter.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody("note" -> "This is a note"))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when token is incorrect" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/" + peter.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> "this is not the real token"))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when sourceId is not provided" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users//link/" + peter.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				status(result) must beEqualTo(NOT_FOUND)
			}
		}
		
		"Return error when targetId is not provided" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/"
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				status(result) must beEqualTo(NOT_FOUND)
			}
		}
		
		
		"Return error when there is no user with sourceId" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/999999/link/" + peter.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				verifyBadResult(result, "Source user not found")
			}
		}
		
		"Return error when there is no user with targetId" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/999999"
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				verifyBadResult(result, "Target user not found")
			}
		}
		
		"Return error when sourceId is the same as targetId" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/" + john.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				verifyBadResult(result, "Source and target users are the same")
			}
		}
		
		"Return error when source and target users are already linked" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/" + jane.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
						"note" -> "This is a note",
						"token" -> validToken))
				verifyBadResult(result, "Source and target users are already linked")
			}
		}
		
		"Create a link when when all parameters are valid" in new LinkUserTestCase {
			running(FakeApplication()) {
				val url = "/users/" + john.id.get + "/link/" + peter.id.get
				val Some(result) = routeAndCall(FakeRequest(POST, url).withFormUrlEncodedBody(
					"note" -> "This is a note",
					"token" -> validToken))

				verifyGoodResult(result) {
					response => 
					println(response.result)
					response.result === "{}"
					
//						{
//							val IdJson = """\{"id":(\d+)\}""".r
//							response.result match {
//								case IdJson(id) => {
//									User.findById(id.toLong) match {
//										case None => failure("User with id " + id + " was not found")
//										case Some(user) => {
//											testUser = user
//											user.firstName must beEqualTo(Some("John"))
//											user.lastName must beEqualTo(Some("Doe"))
//											user.twitter must beEqualTo(Some("john"))
//											user.facebook must beEqualTo(Some("johnDoe"))
//											user.phone must beEqualTo(Some("987-6543210"))
//											user.email must beEqualTo("alex@tindr.ca")
//											user.website must beEqualTo(Some("http://example.com"))
//										}
//									}
//								}
//								case json => failure("Response does not contain valid id: " + contentAsString(result))
//							}
//						}
				}
			}
		}
	}
}

trait LinkUserTestCase extends After {

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
		User.findById(john.id.get).map(_.delete)
		User.findById(jane.id.get).map(_.delete)
		User.findById(peter.id.get).map(_.delete)
		Links.find(john.id.get, jane.id.get).map(_.delete)
	}
}