package controllers

import play.api._
import play.api.mvc._

object Speakers extends Controller {

	// TODO implement
	def list = Action {
		Ok("""{
                 "status": "OK",
                 "message": "Success",
                 "result": {
                   "speakers" : [
                     {
                       "id": 1, 
                       "name": "John Doe", 
                       "title": "CTO of Example Inc.", 
                       "about": "John is in charge of the technical future of Example Inc, and is currently engaged in various conferences as a major speaker.", 
                       "email": "john@example.com",
                       "twitter": "typesafe", 
                       "url": "http://example.com/john" 
                     },
                     {
                       "id": 2, 
                       "name": "Jane Doe", 
                       "title": "Senior Architect at Example Org.", 
                       "about": "Jane is reponsible for the major architectural decisions at Example Org. Her experience with the Typesafe stack is fundamental to the direction of the organization.", 
                       "email": "jane@example.org",
                       "twitter": "typesafe", 
                       "url": "http://example.org/jane" 
                     }
                   ]
                 }
              }""")
	}

}