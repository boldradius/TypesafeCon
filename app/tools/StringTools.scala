package tools

object StringTools {
	implicit def string2OptionString(s:String) = Some(s)
	
	def cleanurl(url: String) = url.replaceAll("http.?://","").replaceAll("/$", "") 

}