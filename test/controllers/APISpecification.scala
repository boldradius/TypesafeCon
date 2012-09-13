package controllers

import org.specs2.matcher.MatchResult
import org.specs2.mutable.Specification

import play.api.mvc.Result
import play.api.test.Helpers._

class APISpecification extends Specification {

	val ValidResponse = """\{"status":"(.+?)","message":"(.*?)","result":(.*)\}""".r

	val validToken   = "openSesame"
	val invalidToken = "wrongToken"

	def verifyBadResult(result: Result, expectedMessage: String) =
		verifyResult(result, BAD_REQUEST, "ERROR")(response => response.message must beEqualTo(expectedMessage))

	def verifyGoodResult[A](result: Result)(verify: APIResponse => MatchResult[A]) = {
		verifyResult(result, OK, "OK")(verify)
	}

	def verifyResult[A](result: Result, expectedStatus: Int, expectedAPIStatus: String)(verify: APIResponse => MatchResult[A]) = {
		status(result) must beEqualTo(expectedStatus)
		contentType(result) must beSome("application/json")

		// Verify the error message
		contentAsString(result) match {
			case ValidResponse(status, message, result) => {
				status must beEqualTo(expectedAPIStatus)
				verify(APIResponse(status, message, result))
			}
			case content => failure("Invalid response format: '" + content + "'")
		}
	}
}

case class APIResponse(status: String, message: String, result: String)