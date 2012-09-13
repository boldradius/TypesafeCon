package controllers

import org.specs2.mutable.After

import anorm.Id
import models.Speaker
import play.api.test.Helpers.running
import play.api.test.FakeApplication
import tools.LoremIpsum

// TODO remove
trait SpeakerTestCasea extends After {
	
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