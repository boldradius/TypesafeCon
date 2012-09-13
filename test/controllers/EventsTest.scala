package controllers

import org.joda.time.DateTime
import org.specs2.mutable.After

import anorm.Id
import models.S1Event
import models.Speaker
import play.api.test.Helpers._
import play.api.test._
import tools.LoremIpsum

class EventsTest extends APISpecification {

	"The List Events API call" should {
		"Retrieve a list of events" in new EventsTestCase  {
			
			running(FakeApplication()) {
				// Verify the result is a 200 code with the proper JSON content type
				val Some(result) = routeAndCall(FakeRequest(GET, "/events"))
				verifyGoodResult(result){
					response => {
						response.status must beEqualTo("OK")
						response.result must contain("\"location\":\"Room 1\"")
						response.result must contain("\"title\":\"Event\"")
						response.result must contain("\"code\":\"TEST\"")
						response.result must contain("\"speakerIds\":[" + speaker.id.get + "]")
					}
				}
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
		event = S1Event(Id(0), "TEST", "Event", LoremIpsum.paragraphs(3), new DateTime, new DateTime, "Room 1", Seq(speaker.id.get)).create.get
	}
	
	// Remove the test data
	def after = running(FakeApplication()) { 
		event.delete
		speaker.delete
	}
}