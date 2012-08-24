package controllers

import play.api._
import play.api.mvc._

object Events extends Controller {

	// TODO implement
	def list = Action {
		Ok("""{
                 "status": "OK",
                 "message": "Success",
                 "result": {
                   "events" : [
                     {
                       "id": 1, 
                       "title": "Play! 101", 
                       "description": "A great session for those with little or no experience in the Play Framework.", 
                       "start": "2012-09-30 08:30:00", 
                       "end": "2012-09-30 10:00:00",
                       "location": "Room 42", 
                       "speakerId": 2
                     },
                     {
                       "id": 2, 
                       "title": "Scala and functional programming", 
                       "description": "An introduction to Scala in the context of functional programming.", 
                       "start": "2012-10-01 10:15:00",
                       "end": "2012-10-01 12:00:00",
                       "location": "Room X", 
                       "speakerId": 1
                     }
                   ]
                 }
              }""")
	}

}