package tools

object StringTools {
	implicit def string2OptionString(s:String) = Some(s)
}