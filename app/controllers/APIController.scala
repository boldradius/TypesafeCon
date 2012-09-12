package controllers

import play.api.Play.current
import play.api.libs.json.Json.toJson
import play.api.libs.json.Writes
import play.api.mvc.Controller
import play.api.Logger
import play.api.data._
import play.api.mvc.SimpleResult
import play.api.libs.json.JsValue

class APIController extends Controller {
	
	def Error(message: String) = 
		BadRequest(toJson(Map(	"status" -> "ERROR",
								"message" -> message,
								"result" -> "")))
								
	def ServerError(message: String) = 
		InternalServerError(toJson(Map(	"status" -> "ERROR",
										"message" -> message,
										"result" -> "")))
								
	def MissingParam(name: String) = Error("Missing parameter: " + name)
	
	def InvalidParam(name: String, details: String) = Error("Invalid parameter: " + name)
	
	def ParamError(error: FormError) = {
		error.message match {
			case "error.required" => MissingParam(error.key)
			case "error.minLength" => InvalidParam(error.key, error.message)
			case "error.maxLength" => InvalidParam(error.key, error.message)
			case "error.invalidParameter" => InvalidParam(error.key, error.message)
			case _ => Error(error.message)
		}
	}

	def UnauthorizedAccess = Error("Unauthorized access")
								
	def Success(message:String): SimpleResult[JsValue] = Success(message, "")
	
	def Success[A](result: A, message: String = "success")(implicit formatter: Writes[A]) =
		Ok(toJson(Map(	"status" -> toJson("OK"),
						"message" -> toJson(message),
						"result" -> toJson(result))))
						
	def Success[A](list: Seq[A], listName: String)(implicit formatter: Writes[A]) =
		Ok(toJson(Map(	"status" -> toJson("OK"),
						"message" -> toJson("Success"),
						"result" -> toJson(Map(listName -> toJson(list))))))
}
