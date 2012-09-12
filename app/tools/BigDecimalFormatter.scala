package tools
import play.api.data.format.Formatter
import play.api.data.FormError

object BigDecimalFormatter extends Formatter[BigDecimal] {
	override def bind(key: String, data: Map[String, String]) = {
		data.get(key) match {
			case Some(value) => {
				try {
					Right(BigDecimal(value))
				} catch {
					case t => Left(Seq(FormError(key, "error.invalidParameter", Nil)))
				}
			}
			case _ => Left(Seq(FormError(key, "error.required", Nil)))
		}
	}

	override def unbind(key: String, value: BigDecimal) = Map(key -> value.toString)
}