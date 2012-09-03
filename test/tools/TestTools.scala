package tools

object TestTools {
	val ValidResponse = """\{"status":"(.+?)","message":"(.*?)","result":(.*)\}""".r
	
	implicit def string2OptionString(s:String) = Some(s)
	
}