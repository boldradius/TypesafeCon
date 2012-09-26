package controllers

import play.api._
import play.api.mvc._
import com.tindr.pusher.Pusher

object Application extends Controller {

	def index = Action {
		implicit request => Ok(views.html.index())
	}

	def landing = Action {
		implicit request => Ok(views.html.landing())
	}

	def playground = Action {
		implicit request => Ok(views.html.playground())
	}
}