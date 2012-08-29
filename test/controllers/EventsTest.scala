package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import anorm.Id
import org.joda.time.DateTime
import models.S1Event
import models.Speaker
import tools.LoremIpsum

class EventsTest extends Specification {

	"The List Events API call" should {
		
		"Retrieve a list of events" in new EventsTestCase  {
			
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(GET, "/events"))
				status(result) must equalTo(OK)
				contentType(result) must beSome("application/json")
				
				// Sanity check to verify our event is in the results
				val json = contentAsString(result) 
				json must contain("\"status\":\"OK\"")
				json must contain("\"location\":\"Room 1\"")
				json must contain("\"title\":\"Event\"")
				json must contain("\"code\":\"FAKECODE\"")
				json must contain("\"speakerIds\":[" + speaker.id.get + "]")
			}
		}
	}
}

trait EventsTestCase extends After {
	
	var event: S1Event = _
	var speaker: Speaker = _
	
	// Create a couple test entries to verify the results
	running(FakeApplication()) {
		speaker = Speaker(Id(0), "John Doe", LoremIpsum.words(3), LoremIpsum.paragraph, 
					Some("john@example.com"), Some("john"), Some("http://example.com/john")).create.get
		event = S1Event(Id(0), "FAKECODE", "Event", LoremIpsum.paragraphs(3), new DateTime, new DateTime, "Room 1", Seq(speaker.id.get)).create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) { 
		event.delete
		speaker.delete
	}
}