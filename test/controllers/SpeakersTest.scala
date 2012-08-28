package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import anorm.Id
import org.joda.time.DateTime
import models.S1Event
import models.Speaker
import tools.LoremIpsum

class SpeakersTest extends Specification {

	"The List Speakers API call" should {
		
		"Retrieve a list of speakers" in new SpeakerTestCase  {
			
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(GET, "/speakers"))
				status(result) must equalTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				val json = contentAsString(result) 
				json must contain("\"status\":\"OK\"")
				json must contain("\"email\":\"john@example.com\"")
				json must contain("\"name\":\"John Doe\"")
				json must contain("\"url\":\"http://example.com/john\"")
				json must contain("\"twitter\":null")
			}
		}
	}
}

trait SpeakerTestCase extends After {
	
	var speaker: Speaker = _
	
	// Create a couple test entries to verify the results
	running(FakeApplication()) {
		speaker = Speaker(Id(0), "John Doe", LoremIpsum.words(3), LoremIpsum.paragraph, 
					Some("john@example.com"), None, Some("http://example.com/john")).create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) { 
		speaker.delete
	}
}