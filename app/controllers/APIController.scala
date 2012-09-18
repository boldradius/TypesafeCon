package controllers

import play.api.Play.current
import play.api.data.FormError
import play.api.libs.json.Json.toJson
import play.api.libs.json.JsValue
import play.api.libs.json.Writes
import play.api.mvc.Request
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.Controller
import play.api.mvc.Result
import play.api.mvc.SimpleResult

class APIController extends Controller {

	private val securityToken = current.configuration.getString("security.token").get

	def Error(message: String) =
		BadRequest(toJson(Map("status" -> "ERROR",
			"message" -> message,
			"result" -> "")))

	def ServerError(message: String) =
		InternalServerError(toJson(Map("status" -> "ERROR",
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

	def Success(message: String): SimpleResult[JsValue] = Success(message, "")

	def Success[A](result: A, message: String = "success")(implicit formatter: Writes[A]) =
		Ok(toJson(Map("status" -> toJson("OK"),
			"message" -> toJson(message),
			"result" -> toJson(result))))

	def Success[A](list: Seq[A], listName: String)(implicit formatter: Writes[A]) =
		Ok(toJson(Map("status" -> toJson("OK"),
			"message" -> toJson("Success"),
			"result" -> toJson(Map(listName -> toJson(list))))))

	def SecuredAction(f: Request[AnyContent] => Result) = Action {
		implicit request =>
			getToken(request) match {
				case Some(token) if (token == securityToken) => f(request)
				case _ => UnauthorizedAccess
			}
	}

	def SecuredAction(f: => Result) = Action {
		implicit request =>
			getToken(request) match {
				case Some(token) if (token == securityToken) => f
				case _ => UnauthorizedAccess
			}
	}

	private def getToken(request: Request[AnyContent]) = {
		request.queryString.getOrElse("token",
			request.body.asFormUrlEncoded.map(body => body.getOrElse("token", List())).getOrElse(List())).toList match {
				case token :: Nil => Some(token)
				case _ => None
			}
	}
}
