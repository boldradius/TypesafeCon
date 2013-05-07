import org.joda.time.DateTime
import anorm.Id
import models._
import play.api.Application
import play.api.GlobalSettings
import tools.LoremIpsum
import play.api.Logger
import tools.StringTools._

object Global extends GlobalSettings {

	override def onStart(app: Application) {
		InitialData.insert()
	}
}

/**
 * Initial set of data to be imported
 * in the sample application.
 */
object InitialData {

	def insert() = {

		if (Speaker.countAll == 0 && S1Event.countAll == 0) {

			/********************************************************************************************************************/
			/*************************************** SPEAKERS *******************************************************************/
			/********************************************************************************************************************/

		  //MK - setting up for Scala Days 2013

			val martin = Speaker(Id(0),
				"Martin Odersky",
				"Chief Architect, Typesafe Inc",
				"Martin Odersky is the inventor of the Scala language, a professor at EPFL in Lausanne, Switzerland, and a founder of Typesafe, Inc. His work concentrates on the fusion of functional and object-oriented programming. He believes the two paradigms are two sides of the same coin, to be unified as much as possible. To prove this, he has worked on a number of language designs, from Pizza to GJ to Functional Nets. He has also influenced the development of Java as a co-designer of Java generics and as the original author of the current javac reference compiler.",
				Some("martin.odersky@epfl.ch"),
				Some("odersky"),
				Some("http://lampwww.epfl.ch/~odersky")).create.get

			val jonas = Speaker(Id(0),
				"Jonas Bonér",
				"CTO and co-founder of Typesafe",
				"Jonas Bonér is a geek, programmer, speaker, musician, writer and Java Champion. He is the CTO and co-founder of Typesafe and is an active contributor to the Open Source community; most notably founded the Akka Project and the AspectWerkz AOP compiler (now AspectJ)",
				None,
				Some("jboner"),
				Some("http://jonasboner.com")).create.get

			val joshua = Speaker(Id(0),
				"Joshua Suereth",
				"Software Engineer at Typesafe",
				"Josh Suereth is a Senior Software Engineer at Typesafe and the author of \"Scala In Depth\". He has been a Scala enthusiast ever since he came to know this beautiful language in 2007. He started his professional career as a software developer in 2004, cutting his teeth with C++, STL and Boost. Around the same time, Java fever was spreading and his interest was migrating to web-hosted distributed Java-delivered solutions to aide health departments discover the outbreaks of disease. Everything from EJB to Hibernate/Spring and even some Applets. He introduced Scala into his company code base first in 2007 and soon after he was infected by Scala fever, contributing to the Scala IDE, maven-scala-plugin and Scala itself. In 2009 he began writing the book \"Scala In Depth\" which provides practical support for using Scala in every day applications. Today, Josh is the author of several open source scala projects, including the scala automated resource management library, the PGP sbt plugin, as well as contributing to key components in the Scala ecosystem, like the maven-scala-plugin. His current work at Typesafe Inc. has him doing anything from building MSIs to profiling performance issues. Josh regularly shares his expertise in articles and talks.",
				None,
				Some("jsuereth"),
				Some("http://jsuereth.com/")).create.get

			val etoreeborre = Speaker(Id(0),
				"Eric Torreborre",
				"",
				"I  grew up using C++/Java/OO/UML as a consultant for various industries (telecommunications, finance,...) then in a startup to create a product doing Model-Driven-Testing. Right after falling in love with Scala, I created the specs project, then superseded by specs2, to create executable software specifications in Scala. I work now for NICTA, helping research to be transformed into successful companies and contribute to the Scoobi project to bridge the gap between Hadoop and Scala.",
				None,
				Some("etorreborre"),
				Some("http://etorreborre.blogspot.com")).create.get

			val viktor = Speaker(Id(0),
				"Viktor Klang",
				"Director of Engineering at Typesafe",
				"Viktor, \"the legend of\", Klang been hooked on Scala since 2007 and is an honorary Akka Core Team member, an occasional speaker and Director of Engineering at Typesafe. Personal heroes are, including but not limited to–Doug Lea, Walter Sobchak and Daniel Spiewak.",
				None,
				Some("viktorklang"),
				Some("http://klangism.tumblr.com")).create.get

			val roland = Speaker(Id(0),
				"Roland Kuhn",
				"Akka Tech Lead at Typesafe",
				"After earning a PhD in high-energy particle physics and working four years as a systems engineer in the space business, Roland came in contact with the Akka project. He started contributing in 2010 and has been employed by Typesafe since 2011 where he has been leading the Akka project since November 2012.",
				None,
				Some("rolandkuhn"),
				Some("http://letitcrash.com")).create.get

			val heiko = Speaker(Id(0),
				"Heiko Seeberger",
				"Director of Professional Services at Typesafe",
				"Heiko Seeberger is the Director Professional Services at Typesafe. He has been a Scala enthusiast ever since he came to know this beautiful language in 2008. He has more than fifteen years of professional expertise in consulting and software development on the Java platform, actively contributes to Scala community projects and regularly shares his expertise in articles and talks. Heiko is the main author of the German Scala book \"Durchstarten mit Scala\".",
				None,
				Some("hseeberger"),
				Some("http://heikoseeberger.name/")).create.get

			val torsten = Speaker(Id(0),
				"Torsten Uhlmann",
				"Founder of AGYNAMIX",
				"Torsten Uhlmann is a Germany-based software developer with a passion to create usable and useful software. For a few years now Scala is his language of choice. Using the secure and productive Lift web framework he designs and implements web based applications for his clients. He is the founder of AGYNAMIX, a small consulting firm, he's a Lift committer and author of the \"Lift Web Applications How-to\".",
				None,
				Some("agynamix"),
				Some("http://www.agynamix.de")).create.get

			val dick = Speaker(Id(0),
				"Dick Wall",
				"Member of the JavaPosse, Partner with Escalate Software",
				"Software Developer and Java Posse podcaster",
				None,
				Some("dickwall"),
				Some("http://javaposse.com")).create.get
				
			val kevin = Speaker(Id(0),
				"Kevin Hoffman",
				"Chief Software Architect for Exclaim Computing",
				"My name is Kevin Hoffman and I’ve been writing code since before I knew how to write cursive. Many of you many know me as \"the .NET Addict\" or from any of the 14+ publications to which I’ve contributed, including books on C#, ASP.NET, ADO.NET, e-Commerce, and many other aspects of enterprise development. I have since branched out from specializing in just .NET code.",
				None,
				Some("KevinHoffman"),
				Some("http://www.kotancode.com/")).create.get

			val christopher = Speaker(Id(0),
				"Christopher Severs",
				"Search Science Applied Research Group at eBay",
				"Chris Severs works in the Search Science applied research group at eBay. Chris fell in love with Scala at first sight and has been one of the main drivers of Scala adoption at eBay.",
				None,
				Some("ccsevers"),
				None).create.get
				
			val vitaly = Speaker(Id(0),
				"Vitaly Gordon",
				"LinkedIn Product Data Science Team",
				"Vitaly Gordon is a senior data scientist on the LinkedIn Product Data Science team where he develops data products that most of you use every day.",
				None,
				Some("BigDataSc"),
				None).create.get

			val ryan = Speaker(Id(0),
				"Ryan LeCompte",
				"Backend Scala Developer at Quantified",
				"Long-time Ruby developer who recently made the switch to Scala. Currently a backend Scala developer at Quantifind in the Bay Area. Creator of the popular \"redis_failover\" project that provides automatic failover for Redis on top of ZooKeeper.",
				None,
				Some("ryanlecompte"),
				Some("http://github.com/ryanlecompte")).create.get

			val jamie = Speaker(Id(0),
				"Jamie Allen",
				"Director of Consulting for Typesafe",
				"Jamie Allen is the Director of Consulting for Typesafe, having worked since 1994 with top firms including Price Waterhouse and Chariot Solutions.  He has a long track record of working closely with clients to build high-quality, mission-critical systems that scale to meet the needs of their businesses, and has worked in myriad industries including automotive, retail, pharmaceuticals, telecommunications and more.  Jamie has been coding in Scala and actor-based systems since 2009, and is the author of the upcoming \"Effective Akka\" book from O'Reilly.",
				None,
				Some("jamie_allen"),
				None).create.get

			val bjorn = Speaker(Id(0),
				"Björn Antonsson",
				"Senior Software Engineer for Typesafe",
				"In depth knowledge and experience of operating systems, compilers, managed runtimes. Interested in building high throughput infrastructure that solves real world problems.",
				None,
				Some("bantonsson"),
				None).create.get

			val patrik = Speaker(Id(0),
				"Patrik Nordwall",
				"Senior Software Engineer for Typesafe",
				"Patrik Nordwall has been professional developer since 1996. He was early adopter of Java. He is a skilled software architect with experience from many different projects and roles. His areas of expertise include Scala, Akka, Java EE, Spring, event-driven architecture, and various products for highly scalable systems.  Patrik is currently working at Typesafe with product development of Akka and helping customers develop highly scalable systems.",
				None,
				Some("patriknw"),
				None).create.get

			val kevinb = Speaker(Id(0),
				"Kevin Brown",
				"PhD Candidate at Stanford University",
				"Kevin Brown is a PhD candidate in the Pervasive Parallelism Lab at Stanford University. His research focuses on simplifying parallel and distributed programming using compiler and runtime systems to target heterogeneous hardware from domain-specific languages.",
				None,
				Some("kevin_j_brown"),
				None).create.get

			val ryank = Speaker(Id(0),
				"Ryan Knight",
				"Consultant at Typesafe",
				"Ryan Knight is a consultant and trainer for Typesafe. He has over 15 years of experience with enterprise software development. During this time he has worked with wide range of business, such as genealogy, telecommunications, finance and video games. Ryan was quickly converted to Scala when he was first introduced to the language, realizing the potential for writing much higher quality software that scales ad infinitum.",
				None,
				Some("knight_cloud"),
				None).create.get

			val alexy = Speaker(Id(0),
				"Alexy Khrabrov",
				"Co-Founder of Versal.com",
				"I am a computer scientist by training and a working software engineer and startup entrepreneur by occupation. My Ph.D. thesis form the University of Pennsylvania, titled \"Mind Economy,\" was the first large scale algorithmic behavior modeling of communication and influence in social networks. Performed while a full-time research scientist at Thayer School of Engineering at Dartmouth, it was based on one of the first streaming API access pipelines to Twitter garden hose. Metrics and findings developed there are still not approached by any of the existing scorers.",
				None,
				Some("khrabrov"),
				Some("http://mindeconomy.com/")).create.get

			val matei = Speaker(Id(0),
				"Matei Zaharia",
				"PhD Student at UC Berkley",
				"Matei Zaharia is a PhD student at UC Berkeley, working on topics in systems, cloud computing and big data. He is the originator of the Spark project, as well as a committer on Apache Mesos and Apache Hadoop. His work is supported by a Google PhD fellowship.",
				None,
				Some("matei_zaharia"),
				Some("http://www.cs.berkeley.edu/~matei")).create.get

			val reynold = Speaker(Id(0),
				"Reynold Xin",
				"PhD Student at UC Berkley",
				"Reynold Xin is a PhD student in the AMP Lab and the Database Group at UC Berkeley. He is the lead developer of the Shark project and the GraphX project. Before graduate school, he had 3 short engineering stints at Google, IBM, and Altera. His interests include data management systems, distributed systems, and algorithms for large-scale data processing.",
				None,
				Some("rxin"),
				Some("http://www.cs.berkeley.edu/~rxin/")).create.get

			val tiark = Speaker(Id(0),
				"Tiark Rompf",
				"Post Doctoral Researcher at Oracle Labs and EPFL",
				"Tiark Rompf is a post-doctoral researcher at Oracle Labs and EPFL.",
				None,
				Some("tiarkrompf"),
				Some("http://tiarkrompf.github.io/")).create.get

			val rose = Speaker(Id(0),
				"Rose Katherine Toomey",
				"Software Engineer at Novus Partners",
				"",
				None,
				Some("prasinous"),
				Some("https://github.com/rktoomey")).create.get

			val joshw = Speaker(Id(0),
				"Josh Wills",
				"Data Scientist at Cloudera",
				"",
				None,
				Some("josh_wills"),
				Some("http://crunch.apache.org")).create.get
				
			val sadek = Speaker(Id(0),
				"Sadek Drobi",
				"A monad and CTO at Zenexity, Play2 co-creator",
				"CTO of Zenexity, Sadek is a software engineer specialized in bridging the gap between the problem domain and the solution domain. As a core Play developer and Play2 co-creator, he works on the design and implementation of the framework. ",
				None,
				Some("sadache"),
				Some("http://sadache.tumblr.com")).create.get
				
			val seth = Speaker(Id(0),
				"Seth Tisue",
				"Lead Developer at NetLogo",
				"Seth Tisue loves programming languages. He works at Northwestern University as the lead developer of NetLogo, a do-it-yourself programming language for kids, teachers, and scientists. He's been living and breathing Scala full time since 2008.",
				None,
				Some("SethTisue"),
				Some("http://tisue.net")).create.get

				
			val jan = Speaker(Id(0),
				"Jan Machacek",
				"Technical Director at Cake Solutions",
				"Jan Machacek is the technical director at Cake Solutions, author of Pro Spring 2.5, Pro Spring and other books and articles. He regularly works on open source projects; he is the author of Specs2 Spring, Scalad, Spock Spring Integration and Spring Workflow Extension. Jan's technical interests and expertise include lightweight JVM-based applications in Scala and Java with asynchronous, resilient and scalable messaging.",
				None,
				Some("honzam399"),
				Some("http://www.eigengo.com/")).create.get
				
			val rod = Speaker(Id(0),
				"Rod Johnson",
				"CEO of SpringSource",
				"Founder and CEO of SpringSource, the international software company behind the Spring Framework, which provides enterprise-grade production and development support for Spring, and comprehensive training and professional services. Specialist in J2EE architecture. Author of best-selling \"Expert One-on-One J2EE Design and Development\" and \"J2EE without EJB.\" Founder of the Spring Framework open source project.",
				None,
				Some("springrod"),
				Some("http://blog.springsource.org/author/rodj/")).create.get

			val eugene = Speaker(Id(0),
				"Eugene Burmako",
				"PhD Student at EPFL",
				"Programming languages enthusiast, PhD student at EPFL, member of Scala team, Scala macros guy",
				None,
				Some("xeno_by"),
				Some("http://xeno.by")).create.get

			val micro = Speaker(Id(0),
				"Mirco Dotta",
				"Software Engineer at Typesafe",
				"Software Engineer at Typesafe and Scala-IDE (http://scala-ide.org/) committer",
				None,
				Some("mircodotta"),
				Some("http://scala-ide.org/")).create.get

			val michael = Speaker(Id(0),
				"Michael Bevilacqua-Linn",
				"Senior Software Architect at Comcast",
				"Michael Bevilacqua-Linn has been programming computers ever since he dragged an Apple IIGS that his parents got for opening a bank account into his fifth grade class to explain loops and variables to a bunch of pre-teenagers. He currently works for Comcast, where he builds distributed systems that power infrastructure for their next generation services, and is writing 'Functional Programming Patterns In Scala and Clojure' for the Pragmatic Press. In his spare time he likes rock climbing and good beer, though not at the same time.",
				None,
				Some("NovusTiro"),
				Some("http://mblinn.com/")).create.get
				
			val john = Speaker(Id(0),
				"John Sullivan",
				"Principal Software Engineer at the Broad Institute",
				"A Java programmer since 1996, I’ve fallen in love with Scala over the last two to three years. I’m extremely fortunate to work more or less full time in Scala at my current position at The Broad Institute. Interests include software engineering, agile software development, OO and functional programming, and Scala language and libraries.",
				None,
				Some("_john_sullivan_"),
				Some("http://scabl.blogspot.ca/")).create.get

			val shadaj = Speaker(Id(0),
				"Shadaj Laddad",
				"Software and Games Developer",
				"Shadaj is a 13 year old, who loves to program. He has programmed in Logo, NXT Mindstorm, Ruby, Python, and C. However, he loves programming in Scala. Besides programming, he likes Math and Science. When he grows up, he wants to be a robotic scientist. Shadaj hosts his projects on GitHub, and has a channel on Youtube. He has presented at Scala Days 2012 and the Bay Area Scala Enthusiast group showing his Scala projects. When not doing his school work or programming, he plays guitar, sitar, and games, some of which he created.",
				None,
				Some("ShadajL"),
				Some("http://www.youtube.com/user/shadajProgramming")).create.get
				
			val andrey = Speaker(Id(0),
				"Andrey Cheptsov",
				"Product Manager at JetBrains for IntelliJ IDEA",
				"A lot of my free time I devote to my own studies in software development. Interested in creation of web-services. Generally focused on usability, functionality of services in the Internet.",
				None,
				Some("andrey_cheptsov"),
				None).create.get
				
			val philipp = Speaker(Id(0),
				"Philipp Haller",
				"Consultant at Typesafe",
				"",
				None,
				Some("philippkhaller"),
				None).create.get
				
			val jason = Speaker(Id(0),
				"Jason Zaugg",
				"Compiler Engineer at Typesafe",
				"",
				None,
				Some("retronym"),
				None).create.get
				
			val heather = Speaker(Id(0),
				"Heather Miller",
				"PhD student at EPFL",
				"I am a US NSF Graduate Research Fellow and second year PhD student working on the Scala programming language at the Programming Methods Laboratory (LAMP for short) under the supervision of Professor Martin Odersky. Before moving to Switzerland, I was born in and lived in different places in the US. I am also the Scala \"Documentation Czar,\" and as such am (newly) responsible for state of official Scala documentation.",
				None,
				Some("heathercmiller"),
				Some("http://heather.miller.am/")).create.get

			val arjen = Speaker(Id(0),
				"Arjen Poutsma",
				"Staff Engineer at SpringSource",
				"Arjen Poutsma has been a Staff Engineer at SpringSource (a division of VMware) for over eight years. He founded the Spring Web Services, worked on the REST support in Spring 3.0 and 3.1, and recently started the Spring Scala project, aimed to make using Spring in Scala easier.",
				None,
				Some("poutsma"),
				None).create.get

			val endre = Speaker(Id(0),
				"Endre Sándor Varga",
				"Software Engineer at Typesafe",
				"Computer Scientist with a strong interest in algorithmic problems, distributed systems. Disciplined user of Object Oriented and/or Functional design. Keen autodidact with a strong focus on bleeding-edge technologies.  Proud Open Source developer.",
				None,
				Some("drewhk"),
				None).create.get

			val tathagata = Speaker(Id(0),
				"Tathagata Das",
				"Grad student at CS Berkeley",
				"I am currently a graduate student in University of California, Berkeley and I am interested in problems related to Cloud Computing and Big Data.",
				None,
				Some("tathadas"),
				None).create.get

			val miles = Speaker(Id(0),
				"Miles Sabin",
				"Principal Engineer at Precog",
				"Technologist and entrepreneur combining broad commercial good sense with deep technical knowledge.",
				None,
				Some("milessabin"),
				Some("http://www.chuusai.com/blog")).create.get


			val christopher = Speaker(Id(0),
				"Christopher Vogt",
				"Software Engineer at EPFL",
				"",
				None,
				Some("cvogt"),
				None).create.get


			val stefan = Speaker(Id(0),
				"Stefan Zeiger",
				"Tech Lead for Slick at Typesafe",
				"I am a senior software engineer, currently employed by a large European bank, where I am working mostly with Java, JavaScript, XML and database technology. My main software-related interests are programming language / interpreter / compiler design, web technology and usability.",
				None,
				Some("StefanZeiger"),
				Some("http://szeiger.de/")).create.get

			val rex = Speaker(Id(0),
				"Rex Kerr",
				"Neurobiologist at the HHMI Janelia Farm Research Campus",
				"Rex Kerr is a neurobiologist at the HHMI Janelia Farm Research Campus who uses Scala for high-performance image analysis, behavioral quantification, and statistics. In his 24 years of software development experience he has used everything from x86 assembly through Scala, with significant detours to C, Java, LabView, and Matlab, in pursuit of software that compactly expresses complex manipulations of data without requiring him to wait too long. Writing performant code is a hobby as well as key aspect of research: he has also contributed to many of the Scala benchmarks in the Computer Languages Benchmark Game.",
				None,
				None,
				None).create.get

			val ignacio = Speaker(Id(0),
				"Ignacio Cases",
				"PhD Student",
				"PhD student Linguistics. Semiotics, Computational Linguistics, Astrophysics. Scala programmer.",
				None,
				Some("ignaciocases"),
				None).create.get

			val ismael = Speaker(Id(0),
				"Ismael Juma",
				"Director of Architecture and Personalisation at TimeOut",
				"Scala, JVM, programming languages, start-ups, personalisation, recommenders, innovation, scalability, performance (in no particular order) and more.",
				None,
				Some("ijuma"),
				Some("http://blog.juma.me.uk")).create.get

			val alois = Speaker(Id(0),
				"Alois Cochard",
				"Software Engineer at TimeOut",
				"Passionate Hacker",
				None,
				Some("aloiscochard"),
				Some("http://aloiscochard.blogspot.ca/")).create.get

			val sebastien = Speaker(Id(0),
				"Sébastien Doeraene",
				"Software Engineer at EPFL",
				"Sébastien Doeraene is a compiler/runtime systems hacker and a Scala enthusiast. He currently works at EPFL in the programming methods laboratory (LAMP) led by Martin Odersky, also known as the Scala team, where he designs and develops Scala.js. He holds bachelor and master degrees in computer science engineering from Université Catholique de Louvain in Belgium. When he is not busy coding, he sings in choirs and a cappella groups, or he plays unicycle basketball.",
				None,
				Some("sjrdoeraene"),
				Some("http://lampwww.epfl.ch/~doeraene/")).create.get

			val andrewp = Speaker(Id(0),
				"Andrew Phillips",
				"Software Developer at XebiaLabs",
				"Specializing in concurrency and high performance, Andrew has substantial experience of enterprise application environments and currently is responsible for product management of XebiaLabs deployment automation platform Deployit. He worked on Multiverse, the STM behind Akka and enjoys the diversity of the JVM ecosystem, especially Clojure and Scala. Andrew also contributes to jclouds, the leading Java cloud library.",
				Some("andrew@scalapuzzlers.com"),
				None,
				Some("http://scalapuzzlers.com/")).create.get

			val nermin = Speaker(Id(0),
				"Nermin Serifovic",
				"Founder and Principal Trainer/Consultant at Composable Solutions",
				"Nermin Serifovic has 10 years of experience building enterprise software applications using Java technologies. For the past 5 years he has focused on architecting, designing and developing backend platforms. Nermin has been a Scala enthusiast since 2009 and practitioning it professionally since mid 2011. He has given various talks at JavaOne, Scala Days and Northeast Scala Symposium, as well as at local Scala and Java user groups. Nermin is a founder and co-organizer of Boston Area Scala Enthusiasts user group and co-founder and co-organizer of Northeast Scala Symposium. Furthermore, he is a co-creator and co-maintainer of the Scala Puzzlers website. Nermin holds a MEng in Computer Science from Cornell University and his areas of interest include distributed systems along with concurrent, parallel and functional programming.",
				None,
				Some("higherkinded"),
				Some("http://composables.com")).create.get


				
			/********************************************************************************************************************/
			/*************************************** EVENTS *********************************************************************/
			/********************************************************************************************************************/

			val KEYNOTE1 = S1Event(Id(0),
				"KEYNOTE1",
				"Keynote - Scala With Style",
				"Scala gives you awesome expressive power, but how to make best use of it? In my talk I will discuss the question what makes good Scala style. We will start with syntax and continue with how to name things, how to mix objects and functions, where (and where not) to use mutable state, and when to use which design pattern. As most questions of style, the discussion will be quite subjective, and some of it might be controversial. I am looking forward to discuss these topics with the conference attendees.",
				new DateTime(2013, 06, 10, 19, 00),
				new DateTime(2013, 06, 10, 20, 30),
				"",
				Seq(martin.id.get)).create

/***** Track 1 Tuesday Morning *****/

			val T1_S1 = S1Event(Id(0),
				"T1_S1",
				"Building Scalable, Highly Concurrent and Fault-Tolerant Systems: Lessons Learned",
				"The skills of building Scalable, Highly Concurrent and Fault-Tolerant Systems are becoming increasingly important in our new world of Cloud Computing, multi-core processors, Big Data and Real-Time Web. Unfortunately, many people are still doing it wrong; using the wrong tools, techniques, habits and ideas. In this talk we will look at some of the most common (and some not so common but superior) practices; what works - what doesn't work - and why.",
				new DateTime(2013, 06, 11, 9, 15),
				new DateTime(2013, 06, 11, 10, 0),
				"",
				Seq(jonas.id.get)).create

			val T1_S2 = S1Event(Id(0),
				"T1_S2",
				"Concurrency – The good, the bad, the ugly",
				"With a plethora of different concurrency programming models available to developers today, in this talk, we'll put on our haz-mat suits and explore the different models–getting a 360 degree view on what's good, what's bad and what's just plain ugly. We're going to cover Threads, pessimistic locking, optimistic locking, event-handlers, actors and more, so don't forget to mute your cellphone, bring popcorn & soda and be prepared for a real thriller!",
				new DateTime(2013, 06, 11, 10, 15),
				new DateTime(2013, 06, 11, 11, 0),
				"",
				Seq(viktor.id.get, roland.id.get)).create

			val T1_S3 = S1Event(Id(0),
				"T1_S3",
				"Simple API Design, Lessons Learned",
				"Scala is one of the most feature rich, expressive and flexible languages around at present. It offers more ways to solve a problem than virtually any other language around, since it mixes Object Orientation, Functional Programming, Static Typing and much more. The down side of all this power and functionality is all too commonly observable in the libraries available for Scala. Many libraries forget the audience for whom they are being written, and instead expose details like the underlying model (which may be very clever, but should mostly be orthogonal to the resulting API and certainly should not directly affect it). To put it another way, if your database structure dictates your user interface or web page, something is wrong with the design of your site (too many failed model driven design projects back this assertion). In the same way, APIs that are dictated by the innermost workings of your library have likely failed to take into account the needs of the user. While writing SubCut, and further enhancing the feature set for SubCut 2.0, I learned many lessons about the advantages of teasing apart your semantic model from your library API. This is not a talk about SubCut, but a talk about some of the techniques I used to provide what I believe is the smallest, simplest and most streamlined API, while hiding most of the details of the implementation. I can also speak to the advantage of that approach with the enhancements made in SubCut 2.0 - the small targeted API let me change much about the inner workings, and while the API was affected for some new features, the new features were always added in a way that kept the API familiar to current users, and more importantly provided an easy, often non-breaking transition path.",
				new DateTime(2013, 06, 11, 11, 15),
				new DateTime(2013, 06, 11, 12, 0),
				"",
				Seq(dick.id.get)).create

/***** Track 2 Tuesday Morning *****/

			val T2_S1 = S1Event(Id(0),
				"T2_S1",
				"Effective SBT",
				"An Introduction and dive into the best practices of creating and maintaining builds in SBT, including how to debug a broken build. During the talk, we'll set up a non-trivial project including an Akka backend and Play frontend, both communicating to each other. If you've struggled learning SBT up to this point, here is the talk for you!",
				new DateTime(2013, 06, 11, 9, 15),
				new DateTime(2013, 06, 11, 10, 0),
				"",
				Seq(joshua.id.get)).create

			val T2_S2 = S1Event(Id(0),
				"T2_S2",
				"Scala in Action",
				"You don't yet speak Scala? Then let us invite you to a journey on which we will explore the outstanding features of this programming language for the Java Virtual Machine. As an introduction we will briefly talk about Scala's key characteristics. Then we will explore this language and some of its typical applications, e.g. concise OO and powerful functional collections. If you are a developer and expect vivid examples and live coding, then you will like this session.",
				new DateTime(2013, 06, 11, 10, 15),
				new DateTime(2013, 06, 11, 11, 0),
				"",
				Seq(heiko.id.get)).create

			val T2_S3 = S1Event(Id(0),
				"T2_S3",
				"Building a Line of Business Application with Play, Scala, and Akka",
				"I've been tasked with building a large, complex, data-driven application that has a rich HTML5, CSS, jQuery UI featuring drag and drop, real-time feedback, and much more and I've done it all using Play, Scala, and Akka. In this session I'll discuss my experiences building a powerful, modern, scalable web application on the Typesafe Stack.",
				new DateTime(2013, 06, 11, 11, 15),
				new DateTime(2013, 06, 11, 12, 0),
				"",
				Seq(kevin.id.get)).create

/***** Track 3 Tuesday Morning *****/

			val T3_S1 = S1Event(Id(0),
				"T3_S1",
				"A Walk Down the Beach, How Kiama Helps Implementing Distributed Collections on Top of Hadoop",
				"This talk will give you an brief overview of the BigData tooling landscape and where Scoobi, a distributed collection Scala library for Hadoop, stands. Then we'll see what are the challenges in translating Scoobi abstractions to Hadoop constructs and how Scala, as programming language, and Kiama (http://code.google.com/kiama), as a graph-processing library, can be leveraged to support this translation. In particular, we'll show: - How rewriting rules based on partial functions are a very succinct way to pre-process the computation graph and to optimise it. - How attribute grammars can be used to implement general graph traversal algorithms",
				new DateTime(2013, 06, 11, 9, 15),
				new DateTime(2013, 06, 11, 10, 0),
				"",
				Seq(etoreeborre.id.get)).create

			val T3_S2 = S1Event(Id(0),
				"T3_S2",
				"Lift 3 and client side JavaScript frameworks",
				"The currently developed upcoming Lift 3 (Scala 2.10+) contains innovative ways to craft web applications, like for instance actors that communicate between JS client and Lift server.  The talk will demonstrate the new features of Lift 3 on one side and also touches the topic of integrating client side JavaScript frameworks like AngularJs, Knockout, Backbone, etc into an application architecture.",
				new DateTime(2013, 06, 11, 10, 15),
				new DateTime(2013, 06, 11, 11, 0),
				"",
				Seq(torsten.id.get)).create

			val T3_S3 = S1Event(Id(0),
				"T3_S3",
				"Scalable and Flexible Machine Learning With Scala",
				"Machine learning turns data into predictions about the real world in an almost magical fashion. In this talk we'll show why Scala is a great language for machine learning practitioners and show the audience of Scala programmers how easy it is to start performing machine learning magic themselves.",
				new DateTime(2013, 06, 11, 11, 15),
				new DateTime(2013, 06, 11, 12, 0),
				"",
				Seq(christopher.id.get, vitaly.id.get)).create

/***** Track 1 Tuesday Afternoon *****/

			val T1_S4 = S1Event(Id(0),
				"T1_S4",
				"Confessions of a Ruby Developer Whose Heart Was Stolen by Scala",
				"Ruby has a long history of \"making the developer happy\" by allowing for ease of programming and prototyping. Rails has taken off by storm and allowed startup companies to quickly create web applications that solve features required by the business. However, recently the Ruby community has realized that scaling Ruby in the real world can be a challenge due to the lack of type safety, the global interpreter lock, lack of a robust threading library, and the general lack of focus on performance in the Ruby world.  In this talk my goal will be to illustrate how I've made the transition from the dynamic programming world of Ruby to the type safe and highly async/concurrent world of Scala and Akka. I think that Ruby developers shy away from Scala due to its static type safety and lack of understanding of its powerful and expressive features. I want to illustrate how Scala does a much better job at allowing the developer to be expressive while avoiding common pitfalls with Ruby (e.g., Ruby's dynamic mixins vs. Scala's type safe trait mixins). I will use examples from my own redis_failover project and illustrate how using Scala could've saved me at times.  The Ruby/Rails community currently has a huge force behind it, and I think that some of these users will make the transition to Scala/Akka/Play with the proper illustrative comparisons and knowledge.",
				new DateTime(2013, 06, 11, 13, 30),
				new DateTime(2013, 06, 11, 14, 15),
				"",
				Seq(ryan.id.get)).create

			val T1_S5 = S1Event(Id(0),
				"T1_S5",
				"Bring Your Own Laptop",
				"Reactive Architectures are the next major evolution of the Internet. They allow for applications to be built in a completely non-blocking, asynchronous, and reactive manner. The Play Framework fully embraces this new paradigm of programming which allows developers to write asynchronous applications using either Scala or Java. In this hands-on session we will be building a reactive Play application. The application will use Play to asynchronously read from a web service and stream it to the client using web sockets and JSON. In building this sample you will learn the basics of how a Play application is structured and the tools it provides for building event-driven applications.",
				new DateTime(2013, 06, 11, 14, 30),
				new DateTime(2013, 06, 11, 15, 15),
				"",
				Seq(ryank.id.get)).create

			val T1_S6 = S1Event(Id(0),
				"T1_S6",
				"Project Lancet: Surgical Precision JIT compilers",
				"Profile-driven JIT compilers like those in most JVMs provide good performance on average, but they are a black box with highly nondeterministic behavior. Thus, achieving top performance, predictably and consistently, is very hard. This talk will present Lancet, a research JIT compiler written in Scala that allows the running application to take full control of the JIT process. This includes the ability to compile multiple specialized versions of code paths at runtime, which is key for removing abstraction overhead of high-level, generic code. Another key feature of Lancet are JIT macros, which enable execution of user code at JIT-compile time and thus open the door for \"smart libraries\" that come with domain-specific optimizations and checks. Unlike Scala macros, JIT macros are not a front-end system, but tightly integrated with regular JIT compiler optimizations and VM functionality like speculative optimization and re-optimization.",
				new DateTime(2013, 06, 11, 15, 30),
				new DateTime(2013, 06, 11, 16, 15),
				"",
				Seq(tiark.id.get)).create

			val T1_S7 = S1Event(Id(0),
				"T1_S7",
				"Realtime Web, The Urge for a Programming Model",
				"Realtime Web Applications are applications making use of Websockets, Server Sent Events, Comet or other protocols to offer an open socket between the browser and the server for continuous communication. Several web frameworks target the development of this type of applications but they mostly feature a basic API that allows developers to push/receive messages from/to an open channel: channel.push(message) //and channel.onMessage { callback } These APIs fall short when it comes to manipuling a rich set of streams of data, which is central to the Realtime Web model. It presents indeed several challenges in terms of creating, adapting, manipulating, filtering and merging streams of data as well as in terms of synchronization involved. To respond to this challenges, it is crucial to have a programming model that identifies clearly what a stream of data is and defines composable components to deal with it. Play2 uses Iteratees together with Futures for dealing reactively with streams of data, providing a very rich model for programming rich Realtime Web Applications.",
				new DateTime(2013, 06, 11, 16, 30),
				new DateTime(2013, 06, 11, 17, 15),
				"",
				Seq(sadek.id.get)).create



/***** Track 2 Tuesday Afternoon *****/

			val T2_S4 = S1Event(Id(0),
				"T2_S4",
				"Real-World Akka Recipes",
				"In the brave new world of actor programming conventional design patterns frequently are not applicable, as is witnessed by questions we get on the mailing list and at conferences. That is why we have collected a number of common solutions and best practices for solving typical problems you will encounter when building scalable and robust systems with Akka actors. In this session we will show you how to implement flow control, distributed workers, blocking resources, reliable messaging and more.",
				new DateTime(2013, 06, 11, 13, 30),
				new DateTime(2013, 06, 11, 14, 15),
				"",
				Seq(jamie.id.get, bjorn.id.get, patrik.id.get)).create

			val T2_S5 = S1Event(Id(0),
				"T2_S5",
				"Versal: A Scala-backed Startup",
				"Versal is an edtech platform. We have a full Scala backend for RESTful API and a JavaScript frontend. In this talk we outline the whole trajectory from nothing to a working company where Scala does the heavy lifting. We describe the event sourcing architecture, and Jellyfish, an open-source project which decouples the web framework from logic. We show how abstraction allows us to not get married to any particular implementation, replacing them by more performant ones as needed. We also describe how our community relationships -- running a Scala for Startups meetup -- helps us stay on top of the best practices for Scala engineering.",
				new DateTime(2013, 06, 11, 14, 30),
				new DateTime(2013, 06, 11, 15, 15),
				"",
				Seq(alexy.id.get)).create

			val T2_S6 = S1Event(Id(0),
				"T2_S6",
				"Practical type mining in Scala",
				"As the author of an open-source serialization library in Scala, I've undergone a lot of struggle to understand and harness the power of Scala's type system. My library was based on parsing pickled Scala signatures, which was a subterranean and sparely documented feature of Scala 2.8. I wanted to serialize and deserialize options, lists and maps, which required defeating type erasure when serializing while skating by on type erasure when deserializing. I struggled with multiple constructors, checking for annotation types, specialization, more. The new reflection libraries introduced in Scala 2.10 provided easier access to the same information I had been getting from the pickled signatures. This talk will address practical aspects of type mining, providing a library of hands-on examples using the Scala 2.10 reflection API.",
				new DateTime(2013, 06, 11, 15, 30),
				new DateTime(2013, 06, 11, 16, 15),
				"",
				Seq(rose.id.get)).create

			val T2_S7 = S1Event(Id(0),
				"T2_S7",
				"Lenses: Fields as Values",
				"A lens represents a readable and \"settable\" location in a possibly nested immutable object. Lenses aren't in the Scala standard library, but several libraries provide them, including Scalaz and Shapeless. I'll show how you to use the Shapeless version, and we'll look at how it's implemented. This may interest you if: ...you use immutable objects ...you use nested immutable objects ...you want to abstract over different fields in your immutable objects ...you're interested in functional programming This is an informal, introductory talk, suitable for newcomers to Scala and newcomers to functional programming.",
				new DateTime(2013, 06, 11, 16, 30),
				new DateTime(2013, 06, 11, 17, 15),
				"",
				Seq(seth.id.get)).create

/***** Track 3 Tuesday Afternoon *****/

			val T3_S4 = S1Event(Id(0),
				"T3_S4",
				"High Performance Cluster Computing with Delite",
				"Traditionally in order to write applications that run across heterogeneous hardware, programmers must manually combine code for multiple programming models together in ad-hoc ways. Domain-specific languages (DSLs) offer an alternative approach, as high-level implicitly parallel domain abstractions can be transparently lowered to multiple heterogeneous architectures. Delite is a framework for building high performance DSLs embedded in Scala. This talk will describe how we have extended Delite to run single-source, implicitly parallel DSL applications across clusters of machines of CPUs and GPUs. Using examples from existing Delite DSLs for data querying and machine learning, we will show how we can achieve high performance with a flexible high-level programming model using a set of powerful compiler analyses and optimizations. Finally we will compare Delite's performance with Hadoop and Spark across of set of applications."
				new DateTime(2013, 06, 11, 13, 30),
				new DateTime(2013, 06, 11, 14, 15),
				"",
				Seq(kevinb.id.get))).create

			val T3_S5 = S1Event(Id(0),
				"T3_S5",
				"The Spark Stack: Fast and Expressive Big Data Analytics in Scala",
				"As big data becomes a concern for more and more organizations, there is a need for both faster tools to process it and easier-to-use APIs. Spark is a Hadoop-compatible cluster computing engine that addresses these needs through (1) in-memory computing primitives that let it run 100x faster than Hadoop and (2) a concise and high-level Scala API that can be used both in standalone programs and interactively from the Scala shell. Increasingly, Spark is also being used to power a stack of higher-level computing frameworks, including Shark, a port of the Hive SQL engine, and GraphX, a graph computing package that can describe algorithms like PageRank in a few lines of code. In this talk, we'll introduce the Spark ecosystem, focusing on Spark's native Scala API and GraphX. We'll also cover use cases from Spark's open source community, which has grown significantly since we released the project in 2010 -- in the past year, 15 companies have contributed code to Spark."
				new DateTime(2013, 06, 11, 14, 30),
				new DateTime(2013, 06, 11, 15, 15),
				"",
				Seq(matei.id.get, reynold.id.get)).create

			val T3_S6 = S1Event(Id(0),
				"T3_S6",
				"Scala for Data Pipelines",
				"Scala's mix of functional and object oriented programming paradigms is ideal for building data pipelines. Many smart people have noticed this, and as a result, we now have three distinct APIs for creating MapReduce pipelines in Scala (Scrunch, Scoobi, and Scalding), as well as a standalone data processing framework developed purely in Scala (Spark). Although all of these tools showcase the power and elegance of Scala, there is a larger opportunity for the community to give pipeline developers a single API to use for both in-memory and batch-style data processing. We'll discuss some of the challenges in creating an API that balances the features of both in-memory and batch processing and demonstrate the benefits that this creates in ETL development, exploratory data analysis, and machine learning.",
				new DateTime(2013, 06, 11, 15, 30),
				new DateTime(2013, 06, 11, 16, 15),
				"",
				Seq(joshw.id.get)).create

			val T3_S7 = S1Event(Id(0),
				"T3_S7",
				"Akka in Heterogenous Environments",
				"Jan will show how to use RabbitMQ to connect components on different platforms. After a few introductory slides explaining the main concepts, the rest of the talk will be live code. The coding will begin by showing simple Scala / Akka code to connect to RabbitMQ to send and receive messages--think ``java.lang.String`` values back and forth to start with. We will then increase the complexity of the messages and marshal the messages into JSON. Jan will then show the real power of messaging infrastructures by replacing the RPC server component by native code in C++: we will write image processing component. To make matters even better--faster--we will then use CUDA to perform the image processing.  Come to Jan's talk and demo if you want to find out how to use Scala, Akka and RabbitMQ in the core of your application, especially if your application needs to tie together code on completely different platforms and if it performs heavy number crunching or image processing. You do not need to have deep knowledge of Scala, Akka, RabbitMQ, Boost, OpenCV, ...; all that you will need is your _inner geek_.",
				new DateTime(2013, 06, 11, 16, 30),
				new DateTime(2013, 06, 11, 17, 15),
				"",
				Seq(jan.id.get)).create

/***** Wednesday Keynote *****/

			val KEYNOTE2 = S1Event(Id(0),
				"KEYNOTE2",
				"Keynote - Scala in 2018",
				"It's 2018. Scala is now 15 years old and the dominant programming language for enterprise apps. But how did we get here? What hurdles did we tackle and what hurdles tackled us?",
				new DateTime(2013, 06, 12, 8, 45),
				new DateTime(2013, 06, 11, 10, 0),
				"",
				Seq(rod.id.get)).create

/***** Track 1 Wednesday Morning *****/

			val T1_S8 = S1Event(Id(0),
				"T1_S8",
				"Half a Year in Macro Paradise",
				"Announced right before 2.10.0-final, macro paradise became the home for experimentation in the macro land. In this talk I will cover the ideas we played with and outline what panned out and what did not. What's going to happen to quasiquotes? Are type macros useful enough? Do macro annotations make sense? Come over and find out the answers.",
				new DateTime(2013, 06, 12, 10, 15),
				new DateTime(2013, 06, 12, 11, 0),
				"",
				Seq(eugene.id.get)).create

			val T1_S9 = S1Event(Id(0),
				"T1_S9",
				"Taming the Cake Pattern with Type Macros",
				"In this talk, we discuss some interesting extensions to the cake pattern as described in Real­World Scala: Dependency Injection (DI) by Jonas Bonér. We consider the use of the cake pattern to express hierarchical components, and the high­level design constraints between them. We also discuss encapsulating the details of a composite component. The cake pattern suffers from three major problems that limit its adoption: 1. Verbosity: Lots of boilerplate. 2. Opacity: Hard to follow the code because the language idioms used do not signify the user's intent. 3. Compiler error messages are confusing. We present a specification for a suite of type macros to address the problems of verbosity and opacity. We provide a status report on the implementation of these macros to date.",
				new DateTime(2013, 06, 12, 11, 15),
				new DateTime(2013, 06, 12, 12, 0),
				"",
				Seq(john.id.get)).create

/***** Track 2 Wednesday Morning *****/

			val T2_S8 = S1Event(Id(0),
				"T2_S8",
				"Scala IDE: Present & Future",
				"In this session we will have a round at all new functionalities introduced in the latest sand greatest Scala IDE V3.0 release, and outline the future direction of the tool. Semantic highlighting, implicit highlighting, and a new shiny Scala debugger are the spotlights of version 3.0. Scala code is now easier to read, write, test and debug. Furthermore, a growing ecosystem of plug-ins is available right at your fingertips for customizing your Scala environment just the way it fits you best.  The focus of the next releases is on enhancing the Scala debugger, further improving the editor's responsiveness, and add first-class support for both Play2 and Sbt.  Sounds too good to be true? Come and see for yourself the Scala IDE in action!",
				new DateTime(2013, 06, 12, 10, 15),
				new DateTime(2013, 06, 12, 11, 0),
				"",
				Seq(micro.id.get)).create

			val T2_S9 = S1Event(Id(0),
				"T2_S9",
				"Fun Programming in Scala: Games, Algorithms, and Apps",
				"Scala continues to be fun! Implementing algorithms to solve Sudoku puzzles, bioinformatic problems, and analyzing time-series data has been a great learning experience. Creating video games and mods have made my game time much more enjoyable and gave me an opportunity to impress my friends! To do all these, I used a myriad of technologies from Scala IDE, sbt, and giter8 to git, Android, and Play! In the process, I learned a lot more about Scala's features such as traits, iterators, streams, lazy vals, implicit classes, and typeclasses. In this talk, I will share a few games, Android apps, and algorithms that show how Scala made implementing complex programs simple. I will also demonstrate a few awesome Minecraft mods written in Scala and a \"mind blowing\" project.",
				new DateTime(2013, 06, 12, 11, 15),
				new DateTime(2013, 06, 12, 12, 0),
				"",
				Seq(shadaj.id.get)).create

/***** Track 3 Wednesday Morning *****/

			val T3_S8 = S1Event(Id(0),
				"T3_S8",
				"How Scala and Akka helped build Sirius at Comcast's Interactive Media division",
				"A case study of how Scala and Akka helped a team of software developers without a whole lot of distributed system programming experience build a fairly sophisticated, production quality distributed system with a custom multipaxos implementation at its core. The name of the system is Sirius, and we're using it to power the next generation of our API layer at Comcast's Interactive Media division. The talk will focus on how we used Akka and Scala to build the core of the system. Our basic approach was to read up on all the Paxos literature we could find. We then took the psuedocode in \"Paxos Made Moderately Complex\" and translated it wholesale into straightforward Scala using functions and tuples. Then we slowly massaged that code into something cleaner using case classes before moving it into Akka actors. Finally, we went through that naive implementation to optimize our usage of memory and network resources to finish the core Paxos implementation. We've had this running in production for about half a year now with only minor issues. Given that we had very limited experience with distributed systems, this a major miracle that we couldn't have pulled off without Akka.",
				new DateTime(2013, 06, 12, 10, 15),
				new DateTime(2013, 06, 12, 11, 0),
				"",
				Seq(michael.id.get)).create

			val T3_S9 = S1Event(Id(0),
				"T3_S9",
				"Scala Developer Tools in IntelliJ IDEA: SBT, Play and Scalate",
				"With IntelliJ IDEA frequently called the most intelligent Java IDE, JetBrains is working to replicate similar levels of code assistance and developer tools for Scala. This presentation will showcase some of the IDE’s trickiest features that help developers to be more productive, and will provide a brief overview of IntelliJ IDEA's plugin infrastructure for Scala development, including support for SBT, Play Framework and Scalate.",
				new DateTime(2013, 06, 12, 11, 15),
				new DateTime(2013, 06, 12, 12, 0),
				"",
				Seq(andrey.id.get)).create

/***** Track 1 Wednesday Afternoon *****/

			val T1_S10 = S1Event(Id(0),
				"T1_S10",
				"Scala Async: A New Way to Simplify Asynchronous Code (Make the Compiler Do It!)",
				"Ever wished the compiler could make asynchronous programming easier? Enter Scala Async. Do asynchronous I/O like \"normal\" blocking I/O, program with Futures and Promises even more naturally! Scala Async makes it possible to \"suspend\" at arbitrary points in a block of regular Scala code, and to \"resume\" from that point later— all without blocking. This not only makes it possible to make concurrent code look sequential, it makes it possible to actually use even the most unfamiliar asynchronous libraries in a familiar blocking style. What's more, not only does it come out-of-the-box seamlessly integrated with Scala 2.10's new Futures and Promises API, but you can also easily use it with any other event-driven Scala or Java library of your choice.",
				new DateTime(2013, 06, 12, 13, 30),
				new DateTime(2013, 06, 12, 14, 15),
				"",
				Seq(philipp.id.get, jason.id.get)).create

			val T1_S11 = S1Event(Id(0),
				"T1_S11",
				"Actor Based Asynchronous IO in Akka",
				"Asynchronous IO is one of the most important building blocks when designing high-performance systems. Over the years various patterns emerged on top of the selector based services provided by the operating system. In this talk I will give a quick overview of the most important asynchronous IO patterns from callbacks to iteratees, demonstrating various approaches to error handling, backpressure/throttling and exposing low level behavior. Finally I will show how these approaches map to the actor world, introducing the new IO model designed by the Akka and Spray team available in Akka 2.2.",
				new DateTime(2013, 06, 12, 14, 30),
				new DateTime(2013, 06, 12, 15, 15),
				"",
				Seq(endre.id.get)).create

			val T1_S12 = S1Event(Id(0),
				"T1_S12",
				"Slick vs. ORM",
				"Slick is not an object-relational mapper, but a functional-relational mapper leading to unique benefits. Using Slick efficiently may be counter-intuitive to people familiar with ORM systems. This talk explains how common ORM use cases should me implemented differently using Slick and what the benefits are. In short, ORM systems suffer from the consequences of the object-relational impedance mismatch, where in contrast Slick's mapping to functional programming constructs is rather straight forward. This allows for tight integration with Scala and highly re-useable code for composing queries.",
				new DateTime(2013, 06, 12, 15, 30),
				new DateTime(2013, 06, 12, 16, 15),
				"",
				Seq(christopher.id.get, stefan.id.get)).create

			val T1_S13 = S1Event(Id(0),
				"T1_S13",
				"One API to rule them all",
				"Designing a REST API for multiple applications and devices is typically an exercise in trade-offs. A single API offers efficiency in terms of building and maintenance costs; specialised APIs, though tedious to build, can be optimised for each particular use-case, removing unnecessary computational overheads. At Time Out (http://www.timeout.com/), we are experimenting with a Graph-based approach that takes full advantage of Scala's functionality to provide the best of both worlds: an optimised and flexible API with no boilerplate. In this talk, we discuss how your domain can be modeled as a graph and introduce Shona, a library which facilitates integration of data from heterogeneous data sources and links it together in a consistent and flexible Graph API. We compare Shona with other approaches (LinkedIn, Netflix and Facebook) highlighting its strengths along with possible areas for development.",
				new DateTime(2013, 06, 12, 16, 30),
				new DateTime(2013, 06, 12, 17, 15),
				"",
				Seq(ismael.id.get, alois.id.get)).create

/***** Track 2 Wednesday Afternoon *****/

			val T2_S10 = S1Event(Id(0),
				"T2_S10",
				"On Pickles and Spores: Improving Scala's Support for Distributed Programming",
				"Despite unifying frameworks like Akka, distributed systems often look like a patchwork of special-purpose tools, libraries, and frameworks. For example, the essential task of persisting objects by pickling (or serializing) them to a binary or text representation is typically outsourced to a third-party serialization framework. Many of these frameworks, like Google's Protocol Buffers, lack support by the Scala compiler or integration with the Scala standard library, resulting in suboptimal performance, unsatisfying Scala support, or both. Furthermore, to obtain good performance using one of these frameworks, it's often necessary to roll your own type-specialized custom serializers, requiring significant boilerplate. This talk presents a new pickling framework designed for Scala with a few attractive properties: (1) using the framework requires little to no boilerplate, (2) using Scala's implicit parameters, users can add their own easily-swappable pickle format enabling users to persist to a number of formats, binary, JSON, or your own custom format, (3) using the type class pattern, users can provide their own custom picklers to override the default behavior of the pickling framework, (4) static, macro-based generation of picklers enables significant performance improvements. In microbenchmarks our framework achieves a factor 6 speedup over Java Serialization, and performs on par or up to 3 times faster than popular fast Java serialization frameworks like Kryo. We'll show how this new framework can be used as a drop-in replacement for other serialization frameworks, and what's in store for Java compatibility. Finally, we'll present an experimental abstraction, called spores, well-behaved, composable pieces of functional behavior that can be cleanly pickled, transported over the wire, unpickled, planted, and more.",
				new DateTime(2013, 06, 12, 13, 30),
				new DateTime(2013, 06, 12, 14, 15),
				"",
				Seq(heather.id.get)).create

			val T2_S11 = S1Event(Id(0),
				"T2_S11",
				"Spark Streaming: Fast Distributed Stream Processing with a High-Level API",
				"Spark Streaming is a new extension to the Spark cluster computing framework that enables high-speed, fault-tolerant stream processing through a high-level Scala API. It builds on a new execution model called \"discretized streams\" to provide exactly-once processing without the heavy cost of transactions required by previous systems (e.g. Storm), allowing it to process significantly higher rates of data per node while still recovering from faults in seconds. It also greatly simplifies stream programming by providing a set of high-level operators (e.g. maps, filters, and windows) in Scala. Perhaps the most exciting feature of Spark Streaming, however, is that it combines seamlessly with Spark's interactive and batch processing features, allowing ad-hoc queries on stream state and programs that combine streaming and historical data. Spark Streaming scales linearly to 100 nodes and has been used to build applications including session-level metrics reporting and online machine learning.",
				new DateTime(2013, 06, 12, 14, 30),
				new DateTime(2013, 06, 12, 15, 15),
				"",
				Seq(tathagata.id.get)).create

			val T2_S12 = S1Event(Id(0),
				"T2_S12",
				"Designing for performance",
				"Scala provides a wide variety of productivity- and correctness-enhancing features, but some of those come at the cost of performance. I will discuss how to design Scala applications to take maximal advantage of Scala's best features while still yielding Java-like performance--or better since you can spend your time thinking and optimizing instead of writing boilerplate! Topics will include how and when to write microbenchmarks, the intrinsic speed of various common library routines, the pitfalls of the first-get-it-working-then-run-the-profiler mode of optimization when performance really matters (and how to let the profiler help you), and patterns to favor when you know a priori or via profiling or benchmarking that performance is critical.",
				new DateTime(2013, 06, 12, 15, 30),
				new DateTime(2013, 06, 12, 16, 15),
				"",
				Seq(rex.id.get)).create

			val T2_S13 = S1Event(Id(0),
				"T2_S13",
				"Scala.js: write in Scala for the browser",
				"Ever dreamed of writing you next Rich Internet Application in Scala, enjoying all the Scala goodness, but not sacrificing JavaScript interoperability? Then come and meet Scala.js, a JavaScript backend for Scala. Scala.js compiles full-fledged Scala code down to JavaScript, which can be integrated in your Web application. It provides very good interoperability with JavaScript code, both from Scala.js to JavaScript and vice versa. E.g., use jQuery and HTML5 from your Scala.js code, either in a typed or untyped way. Or create Scala.js objects and call their methods from JavaScript. For the most part of the talk, we will show off Scala.js and its features through some examples and code samples. We will then present our future plans for Scala.js, and we'll be very interested in knowing what *you* want Scala.js to be.",
				new DateTime(2013, 06, 12, 16, 30),
				new DateTime(2013, 06, 12, 17, 15),
				"",
				Seq(sebastien.id.get)).create

/***** Track 3 Wednesday Afternoon *****/

			val T3_S10 = S1Event(Id(0),
				"T3_S10",
				"Using Spring in Scala",
				"The Spring Framework is one of the most popular Java frameworks used today. While it is possible to use Spring in Scala, it does feel awkward in certain places, because of the \"Javaism\" peering through. For instance, using callback interfaces feels strange in Scala; functions would be a better match. In October 2012, I introduced the Spring Scala project, which solves this particular issue, and aims to make it easier to use Spring in Scala in general. In this session, we discuss the Spring Scala portfolio project and its feature set.",
				new DateTime(2013, 06, 12, 13, 30),
				new DateTime(2013, 06, 12, 14, 15),
				"",
				Seq(arjen.id.get)).create

			val T3_S11 = S1Event(Id(0),
				"T3_S11",
				"Expanding eta-expansion: shapeless polymorphic function values meet macros",
				"Polymorphic function values are one of the key abstractions in shapeless: they are crucial to enabling sequence-like operations such as map, flatMap and filter to be performed on HLists (data structures which combine the heterogeneous typing of tuples with list-like characteristics). Their encoding in Scala has been refined over a number of iterations, but remains a little heavyweight relative to the syntactic ease with which ordinary monomorphic functions or polymorphic methods can be defined. In this talk I will show how we can use macros to automatically promote polymorphic methods to polymorphic function values and how that improves the usability of shapeless.",
				new DateTime(2013, 06, 12, 14, 30),
				new DateTime(2013, 06, 12, 15, 15),
				"",
				Seq(miles.id.get)).create

			val T3_S12 = S1Event(Id(0),
				"T3_S12",
				"Deciphering Maya Hieroglyphic Writing with Scala",
				"The decipherment of Mesoamerican hieroglyphic writing systems advanced at previously unparalleled rates in recent decades. One of the keys to this success is the application of methodologies developed and applied successfully to other writing systems. The purpose of this presentation is to show how some of these methodologies, from Natural Language Processing and Hermeneumatics, can be successfully applied to the Maya hieroglyphic writing system thanks to the power and versatility of Scala. In particular, the research framework presented here makes extensive use of the facilities that Scala provides to build external Domain Specific Languages that epigraphers can use both to provide accurate transcriptions and transliterations of hieroglyphic texts, and to query the linguistic corpora for quantitative and qualitative analyses.",
				new DateTime(2013, 06, 12, 15, 30),
				new DateTime(2013, 06, 12, 16, 15),
				"",
				Seq(ignacio.id.get)).create

			val T3_S13 = S1Event(Id(0),
				"T3_S13",
				"Scala Puzzlers Reloaded",
				"One of the most advanced programming languages of today has surely outgrown the gotchas, puzzlers and head-scratchers of the past. Really? Scala Puzzlers returns with a totally new range of seemingly simple examples which demonstrate that there's plenty of head-scratching left in Scala 2.10! Let your mind be challenged by unexpected and unintuitive behaviour and results and learn something about your favourite language in the process. And unlike in a real magic show, we'll even be giving away the solutions :-)",
				new DateTime(2013, 06, 12, 16, 30),
				new DateTime(2013, 06, 12, 17, 15),
				"",
				Seq(andrewp.id.get, nermin.id.get)).create


		}
	}
}
