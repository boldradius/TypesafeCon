package controllers

import org.specs2.mutable.After
import org.specs2.mutable.Specification

import models.User
import play.api.test.Helpers._
import play.api.test._

class UpdateUserTest extends APISpecification {

	"The Update User API call" should {

		"Return error when token is not provided" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody("firstName" -> "John"))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when token is incorrect" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody("firstName" -> "John", "token" -> invalidToken))
				verifyBadResult(result, "Unauthorized access")
			}
		}

		"Return error when email is not provided" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody("firstName" -> "John", "token" -> validToken))
				verifyBadResult(result, "Missing parameter: email")
			}
		}

		"Return error when updating a user that does not exist" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + 1000000).withFormUrlEncodedBody("firstName" -> "John", "token" -> validToken))
				verifyBadResult(result, "User not found")
			}
		}

		"Return error when updating a user with a new email already registered" in new UpdateUserTestCase {
			running(FakeApplication()) {
				// Create a second user with a new email
				val Some(result) = routeAndCall(FakeRequest(POST, "/users").withFormUrlEncodedBody(
					"firstName" -> "Peter",
					"email" -> "john2@example.com",
					"token" -> validToken))

				// Update our test user to match Peter's email
				val Some(updateResult) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody(
					"email" -> "john2@example.com",
					"token" -> validToken))

				verifyBadResult(updateResult, "Email is already registered")
			}
		}

		"Update a user when passed all valid parameters, without changing his email" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody(
					"firstName" -> "John",
					"lastName" -> "Doe",
					"twitter" -> "john",
					"facebook" -> "johnDoe",
					"phone" -> "987-6543210",
					"email" -> "john@example.com",
					"website" -> "http://example.com",
					"token" -> validToken))

				verifyGoodResult(result) {
					response =>
						User.findById(testUser.id.get) match {
							case None => failure("User with email john@example.com was not found")
							case Some(user) => {
								user.firstName must beEqualTo(Some("John"))
								user.lastName must beEqualTo(Some("Doe"))
								user.twitter must beEqualTo(Some("john"))
								user.facebook must beEqualTo(Some("johnDoe"))
								user.phone must beEqualTo(Some("987-6543210"))
								user.email must beEqualTo("john@example.com")
								user.website must beEqualTo(Some("http://example.com"))
							}
						}
				}
			}
		}

		"Update a user when passed all valid parameters, including changing his email" in new UpdateUserTestCase {
			running(FakeApplication()) {
				val Some(result) = routeAndCall(FakeRequest(PUT, "/users/" + testUser.id).withFormUrlEncodedBody(
					"firstName" -> "John",
					"lastName" -> "Doe",
					"twitter" -> "john",
					"facebook" -> "johnDoe",
					"phone" -> "",
					"email" -> "johnny@example.com",
					"website" -> "http://example.com",
					"token" -> validToken))
				verifyGoodResult(result) {
					response =>
						User.findById(testUser.id.get) match {
							case None => failure("User with email john@example.com was not found")
							case Some(user) => {
								user.firstName must beEqualTo(Some("John"))
								user.lastName must beEqualTo(Some("Doe"))
								user.twitter must beEqualTo(Some("john"))
								user.facebook must beEqualTo(Some("johnDoe"))
								user.phone must beEqualTo(None)
								user.email must beEqualTo("johnny@example.com")
								user.website must beEqualTo(Some("http://example.com"))
							}
						}
				}
			}
		}
	}
}

trait UpdateUserTestCase extends After {

	var testUser: User = _

	// Create a test user before the test case
	running(FakeApplication()) {
		testUser = User("john@example.com").create.get
	}

	// Remove the test data
	def after = running(FakeApplication()) {
		User.findByEmail("john@example.com").map(_.delete)
		User.findByEmail("john2@example.com").map(_.delete)
		User.findByEmail("johnny@example.com").map(_.delete)
	}
}