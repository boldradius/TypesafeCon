import org.joda.time.DateTime

import anorm.Id
import models._
import play.api.Application
import play.api.GlobalSettings
import tools.LoremIpsum

object Global extends GlobalSettings {

	override def onStart(app: Application) {
		InitialData.insert()
	}
}

/**
 * Initial set of data to be imported
 * in the sample application.
 */
object InitialData {

	def insert() = {

		// Add speakers if none exist
		if(Speaker.findAll.isEmpty) {
			(1 to 10).foreach(i => {
				val handle = "john" + i
				Speaker(Id(0), "John Doe " + i, LoremIpsum.words(3), LoremIpsum.paragraph, 
						Some(handle + "@example.com"), Some(handle), Some("http://example.com/" + handle)).create
			})
		}
		
		// Add events if none exist
		if(S1Event.findAll.isEmpty) {
			(1 to 10).foreach(i => {
				val start = new DateTime(2012, 10, 1, 8+i, 0)
				val end = start.plusHours(1)
				S1Event(Id(0), "Event " + i, LoremIpsum.paragraphs(3), start, end, "Room " + i, i).create
			})
		}
	}

}