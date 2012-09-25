package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models.User
import play.api.test.Helpers._
import play.api.test._

class CreateUserTest extends APISpecification {

	"The Create User API call" should {

		"Return error when token is not provided" in {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody("firstName" -> "John"))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when token is incorrect" in {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody("firstName" -> "John", "token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when email is not provided" in {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody("firstName" -> "John", "token" -> validToken))
				verifyBadResult(result, "Missing parameter: email")
			}
		}

		"Create a user when passed all valid parameters" in new CreateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody(
					"firstName" -> "John",
					"lastName" -> "Doe",
					"twitter" -> "john",
					"facebook" -> "johnDoe",
					"phone" -> "987-6543210",
					"email" -> "alex@tindr.ca",
					"website" -> "http://example.com",
					"token" -> validToken))

				verifyGoodResult(result) {
					response =>
						{
							val IdJson = """\{"id":(\d+)\}""".r
							response.result match {
								case IdJson(id) => {
									User.findById(id.toLong) match {
										case None => failure("User with id " + id + " was not found")
										case Some(user) => {
											testUser = user
											user.firstName must beEqualTo(Some("John"))
											user.lastName must beEqualTo(Some("Doe"))
											user.twitter must beEqualTo(Some("john"))
											user.facebook must beEqualTo(Some("johnDoe"))
											user.phone must beEqualTo(Some("987-6543210"))
											user.email must beEqualTo("alex@tindr.ca")
											user.website must beEqualTo(Some("http://example.com"))
										}
									}
								}
								case json => failure("Response does not contain valid id: " + contentAsString(result))
							}
						}
				}
			}
		}
	}
}

trait CreateUserTestCase extends After {

	var testUser: User = _
	
	// Remove the test data
	def after = running(FakeApplication()) {
		testUser.delete
	}
}