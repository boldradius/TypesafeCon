import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

	val appName = "Scala1"
	val appVersion = "1.0"

	val appDependencies = Seq(
		// Add your project dependencies here,
		"postgresql" % "postgresql" % "9.1-901-1.jdbc4",
		"tindr" % "play2pusher_2.9.1" % "1.0"
	)

	val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings( // Add your own project settings here     
		resolvers += Resolver.url("Tindr's Play module repository", url("http://tindr.github.com/releases/"))(Resolver.ivyStylePatterns)
	)

}