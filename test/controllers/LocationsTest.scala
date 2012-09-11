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

	// TODO move to users tests, add location update tests
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