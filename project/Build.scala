import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

	val appName = "Scala1"
	val appVersion = "1.0"

	val appDependencies = Seq(
		// Add your project dependencies here,
		"postgresql" % "postgresql" % "9.1-901-1.jdbc4",
		"play2pusher" % "play2pusher_2.9.1" % "1.0-SNAPSHOT"
	)

	val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings( // Add your own project settings here     
		resolvers += "Local Play Repository" at "file://user/local/play-2.0.2/repository/local"
	)

}
