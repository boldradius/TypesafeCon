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
				"Jonas Bon√©r",
				"CTO and co-founder of Typesafe",
				"Jonas Bon√©r is a geek, programmer, speaker, musician, writer and Java Champion. He is the CTO and co-founder of Typesafe and is an active contributor to the Open Source community; most notably founded the Akka Project and the AspectWerkz AOP compiler (now AspectJ)",
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

			val victor = Speaker(Id(0),
				"Victor Klang",
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
				"My name is Kevin Hoffman and I’ve been writing code since before I knew how to write cursive. Many of you many know me as “the .NET Addict” or from any of the 14+ publications to which I’ve contributed, including books on C#, ASP.NET, ADO.NET, e-Commerce, and many other aspects of enterprise development. I have since branched out from specializing in just .NET code.",
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
				"I am a computer scientist by training and a working software engineer and startup entrepreneur by occupation. My Ph.D. thesis form the University of Pennsylvania, titled “Mind Economy,” was the first large scale algorithmic behavior modeling of communication and influence in social networks. Performed while a full-time research scientist at Thayer School of Engineering at Dartmouth, it was based on one of the first streaming API access pipelines to Twitter garden hose. Metrics and findings developed there are still not approached by any of the existing scorers.",
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
				"CTO of Zenexity, Sadek is a software engineer specialized in bridging the gap between the problem domain and the solution domain.As a core Play developer and Play2 co-creator, he works on the design and implementation of the framework. ",
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
				"Michael Bevilacqua-Linn has been programming computers ever since he dragged an Apple IIGS that his parents got for opening a bank account into his fifth grade class to explain loops and variables to a bunch of pre-teenagers. He currently works for Comcast, where he builds distributed systems that power infrastructure for their next generation services, and is writing ‘Functional Programming Patterns In Scala and Clojure’ for the Pragmatic Press. In his spare time he likes rock climbing and good beer, though not at the same time.",
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
				"",
				"",
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

			val DV12_U_12_09_1 = S1Event(Id(0),
				"DV12_U_12_09_1",
				"First Steps with Scala",
				"In this session we will provide an introduction to the Scala programming language, a language that fuses object oriented programming with functional programming. The session will cover topics including (time permitting): Using the Scala REPL, Control structures in Scala (while, if, try), Type inference, Classes, Objects and Traits, Function Literals and Closures, Introduction to the Scala collections API, Avoiding nulls, For expressions, and Approaching functional programming in Scala",
				new DateTime(2012, 11, 12, 9, 30),
				new DateTime(2012, 11, 12, 12, 30),
				"Room 9",
				Seq(dick.id.get, bill.id.get)).create

			val DV12_U_12_05_1 = S1Event(Id(0),
				"DV12_U_12_05_1",
				"Play Framework - Crash Course",
				"Play Framework, the popular Java and Scala web framework was launched only half a year ago but it‚Äôs fair to say that it has taken the JVM web development community by storm. In this university session the Play core team will teach you how to get the best out of Play including a session about \"Real world application development techniques\" and another that will introduce you to \"Realtime Web Programming\". This session will contain two parts: 1) Play Development techniques in the Real World, and 2) Playframework: Real Time Web for the JVM",
				new DateTime(2012, 11, 12, 9, 30),
				new DateTime(2012, 11, 12, 12, 30),
				"Room 5",
				Seq(peter.id.get, sadek.id.get, gbort.id.get)).create

			val DV12_U_13_09_1 = S1Event(Id(0),
				"DV12_U_13_09_1",
				"Scala advanced concepts and best practices",
				"This session is intended for current scala practitioners with at least a few months of experience in writing Scala. It is not intended as a follow on course from Session 1, and may be bewildering or difficult to follow for newcomers to Scala (it is not recommended for newcomers). Topics covered (time permitting) include: * Idiomatic Scala, * Scala API design and design patterns, * Implicit conversions, parameters and implicit context, * Advanced pattern matching, partial functions, actors, * Extractors, * What's new in Scala 2.10 and * Tail calls, trampolines and continuations",
				new DateTime(2012, 11, 13, 9, 30),
				new DateTime(2012, 11, 13, 12, 30),
				"Room 9",
				Seq(dick.id.get, bill.id.get)).create
				
			val DV12_C_15_07_3 = S1Event(Id(0),
				"DV12_C_15_07_3",
				"Up up and Out: Scaling software with Akka",
				"We believe that one should never have to choose between productivity and scalability, which has been the case with traditional approaches to concurrency and distribution. The cause of that has been the wrong tools and the wrong layer of abstraction and Akka is here to change that. Akka is an open source, unified runtime and programming model for scaling both UP (utilizing multi-core processors) and OUT (utilizing the grid/cloud). With Akka this will be taken to a whole new level with its \"Distributed by Design\". Akka provides location transparency by abstracting away both these tangents of scalability by turning them into an operations and configuration task. This gives the Akka runtime freedom to do adaptive automatic load-balancing, cluster rebalancing, replication and partitioning. In this talk you will learn what Akka is and how it can be used to solve hard scalability problems. Akka is available at http://akka.io (under Apache 2 license).",
				new DateTime(2012, 11, 15, 14, 0),
				new DateTime(2012, 11, 15, 15, 0),
				"Room 7",
				Seq(jonas.id.get)).create
				
			val CODE = S1Event(Id(0),
				"CODE",
				"Functional Compilers: From CFG to EXE",
				"Martin Fowler once said that there are two types of people in this world: those who didn't take compilers at the university and are afraid of them, and those who took compilers at the university and are afraid of them. Compilers are some of the most complex and theoretically-rich pieces of software in existence. Each one requires careful and well considered design to avoid becoming a tangled morass of meta-generators and bailing wire. This talk will look at the construction of a simple compiler in Scala. We will examine some of the tools Scala gives us that can dramatically simplify the task of writing and testing a compiler, and in the process, gain an appreciation for what compilers look like and how to build your own",
				new DateTime(2012, 11, 15, 16, 40),
				new DateTime(2012, 11, 15, 17, 40),
				"Room 9",
				Seq(daniel.id.get)).create
				
			val DV12_C_15_09_3 = S1Event(Id(0),
				"DV12_C_15_09_3",
				"Simplicity in Scala Design",
				"This talk will focus on how to achieve simplicity in Scala library, DSL, and application design. It will highlight general principles that can be applied to any programming language, and show specific techniques that can be used in Scala to \"implement\" the general principles. This talk will give you a set of concrete guidelines that can help you manage complexity in your Scala projects.",
				new DateTime(2012, 11, 15, 14, 0),
				new DateTime(2012, 11, 15, 15, 0),
				"Room 9",
				Seq(bill.id.get)).create
				
			val DV12_C_14_06_5 = S1Event(Id(0),
				"DV12_C_14_06_5",
				"Effective Scala",
				"This talk covers best practices of using the Scala language on the JVM. In this session, hear how to avoid null pointer exceptions, what the use of traits implies on object oriented design and how to best interface Java and Scala programs. This talk promotes general best practices in Scala, as exemplified in the book: Scala In Depth.",
				new DateTime(2012, 11, 14, 17, 50),
				new DateTime(2012, 11, 14, 18, 50),
				"Room 6",
				Seq(joshua.id.get)).create
				
			val DV12_H_13_02_2 = S1Event(Id(0),
				"DV12_H_13_02_2",
				"Practical - Scala Koans",
				"This hands-on lab will work through the Scala Koans with instructor guidance and help. Scala Koans is an open source project that provides small, manageable coding lessons to learn the basics of Scala upon the path to Scala enlightenment. Attendees will need their own laptop and instructions will be provided ahead of time to get the Scala compiler and related tools working for the session. The Koans themselves are built around a sequence of tests that you have to get working to move forward to the next problem. This session will provide hands on experience with Scala coding and confidence to carry on both the koans and general scala development solo after the session is completed.",
				new DateTime(2012, 11, 13, 13, 30),
				new DateTime(2012, 11, 13, 16, 30),
				"BOF 2",
				Seq(dick.id.get, bill.id.get)).create
				
			val DV12_B_12_01_2 = S1Event(Id(0),
				"DV12_B_12_01_2",
				"Scala Puzzlers",
				"One of the most advanced programming languages of today has surely outgrown the gotchas, puzzlers and head-scratchers of the past. Really? Prepare to be surprised, entertained and...well, puzzled! We'll present a selection of seemingly simple examples which demonstrate that there's plenty of head-scratching left in Scala! Let your mind be challenged by unexpected and unintuitive behaviour and results and learn something about your favourite language in the process. And unlike in a real magic show, we'll even be giving away the solutions :)",
				new DateTime(2012, 11, 12, 20, 0),
				new DateTime(2012, 11, 12, 21, 0),
				"BOF 1",
				Seq(andrew.id.get)).create
				
			val DV12_B_12_01_1 = S1Event(Id(0),
				"DV12_B_12_01_1",
				"Scala Enthusiasts Gathering",
				"BeScala, the Belgian Scala Enthusiasts group, would like to invite all Scala users and fans coming to Devoxx for an informal meet-up. This year we will organise a Q&A with some Devoxx' Scala speakers. It will be a great opportunity for the community to ask questions about the past, present and future of our favorite programming language.",
				new DateTime(2012, 11, 12, 19, 0),
				new DateTime(2012, 11, 12, 20, 0),
				"BOF 1",
				Seq(renato.id.get, tim.id.get, luc.id.get, louis.id.get)).create
				
			val DV12_T_12_04_1 = S1Event(Id(0),
				"DV12_T_12_04_1",
				"Rapid Application Development with Play 2",
				"Play 2 http://www.playframework.org/ the popular Java and Scala web framework is well known for its rapid application development support. In this talk you will learn about all the exciting features that should help you to get things done. A few topics being covered: how to change your code and compile on the fly, - how to take advantage of Play2's type safe templates and routes, - play2 front end technology integration (coffeescript, LESS and google closure compiler integration), - packaging an application, - peeking into your application using the REPL, - running tests",
				new DateTime(2012, 11, 12, 16, 45),
				new DateTime(2012, 11, 12, 17, 15),
				"Room 4",
				Seq(peter.id.get)).create
		}
	}
}