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

			val peter = Speaker(Id(0),
				"Peter Hausel",
				"Senior Software Engineer, Typesafe Inc",
				"Peter Hausel has more than fourteen years of software engineering experience. He is obsessed with web technologies, great user experience and open source. He was an early contributor to the Play framework and now he is a member of the Play core team. He works at Typesafe.",
				Some("peter_kovac11@hotmail.com"),
				Some("pk11"),
				Some("https://github.com/pk11")).create.get
			
			val dick = Speaker(Id(0),
				"Dick Wall",
				"Member of the JavaPosse, Partner with Escalate Software",
				"Software Developer and Java Posse podcaster",
				None,
				Some("dickwall"),
				Some("http://javaposse.com")).create.get
				
			val bill = Speaker(Id(0),
				"Bill Venners",
				"President of Artima Inc.",
				"Bill Venners is president of Artima, Inc., publisher of Artima Developer. He is author of the book, Inside the Java Virtual Machine, a programmer-oriented survey of the Java platform's architecture and internals. His popular columns in JavaWorld magazine covered Java internals, object-oriented design, and Jini. Active in the Jini Community since its inception, Bill led the Jini Community's ServiceUI project, whose ServiceUI API became the de facto standard way to associate user interfaces to Jini services. Bill is also the lead developer and designer of ScalaTest, an open source testing tool for Scala and Java developers, and coauthor with Martin Odersky and Lex Spoon of the book, Programming in Scala.",
				None,
				Some("bvenners"),
				Some("http://www.artima.com")).create.get
				
			val gbort = Speaker(Id(0),
				"Guillaume Bort",
				"Cofounder of Zenexity",
				"Guillaume Bort is cofounder of Zenexity, the french ‘Web Oriented Architecture’ company. Former J2EE expert, he worked several years on constructing Web frameworks for large scale companies including banks, until he decided to sum up his experience by creating Play framework focusing back on simplicity. He still leads development of the Play framework.",
				None,
				Some("guillaumebort"),
				Some("http://guillaume.bort.fr")).create.get
				
			val sadek = Speaker(Id(0),
				"Sadek Drobi",
				"A monad and CTO at Zenexity, Play2 co-creator",
				"Sadek Drobi est directeur technique de Zenexity, cabinet spécialisé dans les architectures orientées Web. Il est un architecte expérimenté, spécialisé dans la création d'application d'entreprise fortement distribuées, avec un intérêt particulier dans les langages de programmation et la création d'API et de framework pour résoudre les problèmes complexes au bon niveau d'abstraction. Il est `Core Developer` sur le project Play.",
				None,
				Some("sadache"),
				Some("http://sadache.tumblr.com")).create.get
				
			val jonas = Speaker(Id(0),
				"Jonas Bonér",
				"CTO and co-founder of Typesafe",
				"Jonas Bonér is a geek, programmer, speaker, musician, writer and Java Champion. He is the CTO and co-founder of Typesafe and is an active contributor to the Open Source community; most notably founded the Akka Project and the AspectWerkz AOP compiler (now AspectJ)",
				None,
				Some("jboner"),
				Some("http://jonasboner.com")).create.get
				
			val daniel = Speaker(Id(0),
				"Daniel Spiewak",
				"Scala fanatic. Functional devotee. Overly-fascinated by programming esoterica.",
				"Daniel Spiewak is a software developer based out of Boulder, CO. Over the years, he has worked with Java, Scala, Ruby, C/C++, ML, Clojure and several experimental languages. He currently spends most of his free time researching parser theory and methodologies, particularly areas where the field intersects with functional language design, domain-specific languages and type theory. Daniel has written a number of articles on his weblog, Code Commit, including his popular introductory series, Scala for Java Refugees.",
				None,
				Some("djspiewak"),
				Some("http://www.codecommit.com/blog")).create.get
				
			val joshua = Speaker(Id(0),
				"Joshua Suereth",
				"Software Engineer at Typesafe",
				"Josh Suereth is a Senior Software Engineer at Typesafe and the author of \"Scala In Depth\". He has been a Scala enthusiast ever since he came to know this beautiful language in 2007. He started his professional career as a software developer in 2004, cutting his teeth with C++, STL and Boost. Around the same time, Java fever was spreading and his interest was migrating to web-hosted distributed Java-delivered solutions to aide health departments discover the outbreaks of disease. Everything from EJB to Hibernate/Spring and even some Applets. He introduced Scala into his company code base first in 2007 and soon after he was infected by Scala fever, contributing to the Scala IDE, maven-scala-plugin and Scala itself. In 2009 he began writing the book \"Scala In Depth\" which provides practical support for using Scala in every day applications. Today, Josh is the author of several open source scala projects, including the scala automated resource management library, the PGP sbt plugin, as well as contributing to key components in the Scala ecosystem, like the maven-scala-plugin. His current work at Typesafe Inc. has him doing anything from building MSIs to profiling performance issues. Josh regularly shares his expertise in articles and talks.",
				None,
				Some("jsuereth"),
				Some("http://jsuereth.com/")).create.get
				
			val andrew = Speaker(Id(0),
				"Andrew Phillips",
				"Software Developer at XebiaLabs",
				"Specializing in concurrency and high performance, Andrew has substantial experience of enterprise application environments and currently is responsible for product management of XebiaLabs deployment automation platform Deployit. He worked on Multiverse, the STM behind Akka and enjoys the diversity of the JVM ecosystem, especially Clojure and Scala. Andrew also contributes to jclouds, the leading Java cloud library.",
				None,
				None,
				None).create.get
				
			val renato = Speaker(Id(0),
				"Renato Cavalcanti",
				"Founder of BeScala",
				"Renato Guerra Cavalcanti is an independent senior analyst/developer particularly experienced in design and development of complex and multitiered applications. Having studied psychology (in Rio) and philosophy (in Paris), he started programming in 1999 to finance his studies. In 2002 he decided to dedicate himself full-time to programming. In his free time, he is experimenting with new technologies, java frameworks and programming languages, specially Scala. In 2011, Renato, together with Tim Romberg and Louis Jacomet, founded BeScala, the Belgium Scala Enthusiasts group with the objective to promote Scala adoption in Belgium. He's is also steering committee member of BeJUG, the Belgium Java User Group.",
				None,
				Some("renatocaval"),
				Some("http://www.xavena.eu/")).create.get
				
			val tim = Speaker(Id(0),
				"Tim Romberg",
				"POSITION",
				"Tim has been active for 15 years as developer, researcher, and consultant with a focus on Enterprise Collaboration and Healthcare, in Germany and Belgium. His experience with MVC dates back to an ERP-related assignment on an early version of Borland Delphi.",
				None,
				Some("rombergt"),
				Some("URL")).create.get
				
			val louis = Speaker(Id(0),
				"Louis Jacomet",
				"",
				"Louis Jacomet has been perfecting his knowledge of enterprise Java for nearly 10 years. Initially as a developer, his role evolved over the last years as technical team leader or coach. As an avid learner, Louis is now getting to know Scala and its ecosystem as a new challenge. In addition to the bits and bytes, Louis is interested in people management skills mandatory to create a productive project team. Discovering Agile 5 years ago was a revelation and has underpinned all his work since, whether on an Agile project or not. Louis works in Belgium as a freelance contractor.",
				None,
				Some("ljacomet"),
				None).create.get
				
			val luc = Speaker(Id(0),
				"Luc Duponcheel",
				"Founder of ImagineJ",
				"Java and Scala Developer/Instructor.",
				None,
				Some("LucDup"),
				Some("http://www.linkedin.com/pub/luc-duponcheel/1/366/6b7")).create.get
				
			val nick = Speaker(Id(0),
				"NAME",
				"POSITION",
				"BIO",
				None,
				Some("TWITTER"),
				Some("URL")).create.get

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
				"Play Framework, the popular Java and Scala web framework was launched only half a year ago but it’s fair to say that it has taken the JVM web development community by storm. In this university session the Play core team will teach you how to get the best out of Play including a session about \"Real world application development techniques\" and another that will introduce you to \"Realtime Web Programming\". This session will contain two parts: 1) Play Development techniques in the Real World, and 2) Playframework: Real Time Web for the JVM",
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