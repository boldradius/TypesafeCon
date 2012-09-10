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

		if (User.countAll == 0) {
			val scala1 = User("Scala1", "scala1@example.com", "typesafe", None, None, "http://typesafe.com").create.get
			val alex = User("Alex", "alex@tindr.co", "andanthor", None, None, "http://alejandrolujan.com").create.get
			
			if (GeneralMessage.countAll == 0) {
				new GeneralMessage(Id(0), scala1.id.get, "Welcome to Scala1!").create
				new GeneralMessage(Id(0), scala1.id.get, "Enjoy the conference and don't be shy - connect to your fellow Scala enthusiasts!").create
			}
			
			if(PrivateMessage.countAll(scala1.id.get, alex.id.get) == 0) {
				new PrivateMessage(Id(0), scala1.id.get, "Hi Alex", alex.id.get).create
				new PrivateMessage(Id(0), alex.id.get, "Hey there!", scala1.id.get).create
			}
		}

		if (Speaker.countAll == 0 && S1Event.countAll == 0) {

			/********************************************************************************************************************/
			/*************************************** SPEAKERS *******************************************************************/
			/********************************************************************************************************************/

			val peter = Speaker(Id(0),
				"Peter Hausel",
				"Senior Software Engineer, Typesafe Inc",
				"Peter Hausel has more than fourteen years of software engineering experience. He is obsessed with web technologies, great user experience and open source. He was an early contributor to the Play framework and now leads Typesafe's Play initiative.",
				Some("peter_kovac11@hotmail.com"),
				Some("pk11"),
				Some("https://github.com/pk11/")).create.get

			val martin = Speaker(Id(0),
				"Martin Odersky",
				"Chief Architect, Typesafe Inc",
				"Martin Odersky is the inventor of the Scala language, a professor at EPFL in Lausanne, Switzerland, and a founder of Typesafe, Inc. His work concentrates on the fusion of functional and object-oriented programming. He believes the two paradigms are two sides of the same coin, to be unified as much as possible. To prove this, he has worked on a number of language designs, from Pizza to GJ to Functional Nets. He has also influenced the development of Java as a co-designer of Java generics and as the original author of the current javac reference compiler.",
				Some("martin.odersky@epfl.ch"),
				Some("odersky"),
				Some("http://lampwww.epfl.ch/~odersky/")).create.get

			val roland = Speaker(Id(0),
				"Roland Kuhn",
				"Software Developer, Typesafe Inc",
				"After earning a PhD in high-energy particle physics from TU München, Roland changed into the space business for four years. As a systems engineer and software developer for LSE Space he came in contact with the Akka project to which he started contributing in 2010. This ever-growing passion led to his employment by Typesafe in September 2011. Now he is one of the core developers of Akka (http://akka.io).",
				None,
				Some("rolandkuhn"),
				Some("https://github.com/rkuhn")).create.get

			val viktor = Speaker(Id(0),
				"Viktor Klang",
				"Akka Tech Lead, Typesafe Inc",
				"Viktor Klang, also known as √, is a passionate programmer with a taste for concurrency paradigms and performance optimization. Currently working as Tech Lead for the Akka project at Typesafe.",
				None,
				Some("viktorklang"),
				Some("http://klangism.tumblr.com/")).create.get

			val venkat = Speaker(Id(0),
				"Venkat Subramaniam",
				"President, Agile Developer, Inc.",
				"Dr. Venkat Subramaniam is an award-winning author, founder of Agile Developer, Inc., and an adjunct faculty at the University of Houston. He has trained and mentored thousands of software developers in the US, Canada, Europe, and Asia, and is a regularly-invited speaker at several international conferences. Venkat helps his clients effectively apply and succeed with agile practices on their software projects.\n\nVenkat is the author of \".NET Gotchas,\" the coauthor of 2007 Jolt Productivity Award winning \"Practices of an Agile Developer,\" the author of \"Programming Groovy: Dynamic Productivity for the Java Developer\" and \"Programming Scala: Tackle Multi-Core Complexity on the Java Virtual Machine\" (Pragmatic Bookshelf). His latest book is \"Programming Concurrency on the JVM: Mastering synchronization, STM, and Actors. \"",
				Some("venkats@agiledeveloper.com"),
				Some("venkat_s"),
				Some("http://www.agiledeveloper.com/")).create.get

			val jward = Speaker(Id(0),
				"James Ward",
				"Developer Evangelist, Heroku",
				"James Ward (www.jamesward.com) is a Principal Developer Evangelist at Heroku. Today he focuses on teaching developers how to deploy Java, Play! and Scala apps to the cloud. James frequently presents at conferences around the world such as JavaOne, Devoxx, and many other Java get-togethers. Along with Bruce Eckel, James co-authored First Steps in Flex. He has also published numerous screencasts, blogs, and technical articles. Starting with Pascal and Assembly in the 80′s, James found his passion for writing code. Beginning in the 90′s he began doing web development with HTML, Perl/CGI, then Java. After building a Flex and Java based customer service portal in 2004 for Pillar Data Systems he became a Technical Evangelist for Flex at Adobe. You can find him tweeting as @_JamesWard, answering questions on StackOverflow.com and posting code at github.com/jamesward.",
				Some("james@jamesward.org"),
				Some("_JamesWard"),
				Some("http://www.jamesward.com")).create.get

			val markus = Speaker(Id(0),
				"Markus Eisele",
				"Principal IT Architect, msg systems ag",
				"I am a principal technology consultant working for msg systems ag in Germany. As a software architect, developer and consultant. Also writing for German IT magazines. I work daily with customers and projects dealing with Enterprise Java and other new technologies, on a variety of platforms using different vendors. My main area of expertise are Java EE Middleware Servers.\nI am a member of the Java EE 7 EG and DOAG representative on the German iJUG.",
				None,
				Some("myfear"),
				Some("http://blog.eisele.net/")).create.get

			val luc = Speaker(Id(0),
				"Luc Duponcheel",
				"Instructor, ImagineJ",
				"Luc Duponcheel is a Java/Scala instructor/developer. He worked for Sun (Oracle now) since 1998 and works for his own company, ImagineJ, since 2008. Before he worked as a researcher on programming language and library design. His hobbies are cycling (especially in the mountains) and gardening (tomatos, paprikas, ... you name it).",
				None,
				Some("LucDup"),
				Some("http://www.linkedin.com/pub/luc-duponcheel/1/366/6b7")).create.get

			val bruno = Speaker(Id(0),
				"Bruno Borges",
				"Senior Architect, Jawsys",
				"Bruno works with Java since 2001 and has worked mostly with Web Development, specially in the Enterprise level. Has also worked with several web frameworks like JSF, Wicket, Tapestry, GWT, Spring MVC, Spring WebFlow, Struts and others. Now works at Oracle to help promote the Java EE platform and Oracle's JEE servers GlassFish and WebLogic in Latin America",
				Some("bruno.borges@gmail.com"),
				Some("brunoborges"),
				Some("http://www.brunoborges.com/")).create.get

			val ppilgrim = Speaker(Id(0),
				"Peter Pilgrim",
				"Developer Consultant, Private Individual",
				"Peter Pilgrim is a professional software developer, designer and architect. Since 1998 he has worked in the financial services industry, investment banking mainly, developing Information Technology solutions for clients. He is a well-known specialist in Java Enterprise Edition (Java EE) technology, focused on the server-side and the implementation of electronic commerce. Peter has built professional Java EE apps for top-tier investment banks for HSBC, State Street, Lloyds Banking Group, UBS, Credit Suisse, Royal Bank of Scotland and Deutsche Bank. Peter is the 91st Oracle Java Champion (Feb 2007). Peter is a Certified SCRUM Master from Martine Devos (May 2010).\nAs well as extensive Java development experience, Peter is an early adopter of technologies and a leading light for progression on the Java platform, especially by influencing the wider community. Peter founded and organised the JAVAWUG from 2004 to 2010.\nHe attended Chris Oliver’s seminal F3 talk at JavaOne 2007, which debuted the first ever public viewing of the, then, JavaFX (Script) technology. Peter has been interested in the JavaFX next generation user interface on the Java platform ever since. He developed a number of experimental custom controls.\nA great deal of Peter’s reputation is derived from his proactive search for new technologies, especially on the JVM. Peter attended Martin Odersky’s first ever Scala Workshop outside of Switzerland, in the UK (June 2010), he said of it at the time: “There is just nothing like learning new tech from the master who created it in the first place. You can’t beat that opportunity. If James Gosling (The proverbial Father of Java) had organised an Introduction to Java course in 1998 in London, I would have done my damnedest to get on it. Same idea: stay fresh, to be best”.",
				None,
				Some("peter_pilgrim"),
				Some("http://www.xenonique.co.uk/blog/")).create.get

			val andrey = Speaker(Id(0),
				"Andrey Breslav",
				"Lead Language Designer, JetBrains",
				"Andrey is the lead language designer working on Project Kotlin at JetBrains (http://jetbrains.com/kotlin). He also tries to make the Java language better serving as a Java Community Process expert in a group working on JSR-335 (\"Project Lambda\"). In what spare time is left he tries to make sure that his traveling is not all about work and teaches programming to high-school children. Used to teach OOP/Software Design at a university, but currently switched to speaking at software conferences. Audiences of Devoxx, OSCON, StrangeLoop, Jfokus and other events gave warm reception to his talks.",
				None,
				Some("abreslav"),
				Some("https://sites.google.com/site/abreslav2/")).create.get

			val talip = Speaker(Id(0),
				"Talip Ozturk",
				"Managing Partner, Hazel Bilisim Limited Sirketi",
				"Talip Ozturk is the founder of Hazelcast. He has been working with enterprise Java since 1999. He worked as a consultant at MIC (Virginia), developer at a start-up company, Syncline (Boston) and sales architect at Itochu Technologies (New York). In 2003, he got fascinated by Jini and developed an implementation of JavaSpaces. In 2008, his passion for distributed programming led him to develop Hazelcast. Before Hazelcast, Talip was the director of technology at Zaman Media Group (Istanbul). In his free time, he enjoys playing soccer.",
				None,
				Some("oztalip"),
				Some("http://www.hazelcast.com/")).create.get

			val brian = Speaker(Id(0),
				"Brian Tarbox",
				"Distinguished Engineer, Motorola Mobility",
				"Brian Tarbox is a Distinguished Member of Technical Staff at Motorola in the Systems Engineering group designing next generation video products. His open source music product Log4JFugue won the 2010 Duke's Choice Award for Innovation, and his 2009 talk on that topic earned Rock Star Status.\nHe hold several patents in the fields of User Interface design, CDN Caching and Three Screen TV.\nHe is a frequent contributor to the Pragmatic Programmer magazine and the No Fluff, Just Stuff Journal.\nHe plays numerous instruments including Native American Flute, shakuhachi, didgeridoo, and hulusi.\nHis primary interest in the JVM world is polyglot programming with Scala and Groovy.\nHe writes a blog on the intersection of software design, cognition, music, and creativity at briantarbox.blogspot.com.",
				None,
				None,
				Some("http://briantarbox.org/")).create.get

			val guillaume = Speaker(Id(0),
				"Guillaume Laforge",
				"Head of Groovy Development, VMware",
				"Guillaume Laforge is the project lead of Groovy, the highly popular and successful dynamic language for the JVM. He co-authored Manning's best seller \"Groovy in Action\" with Dierk König, and is working for SpringSource (a division of VMWare) where he's hacking full time on cool and Groovy stuff. You can meet Guillaume at conferences around the world where he evangelizes the Groovy dynamic language, Domain-Specific Languages in Groovy, the agile Grails web framework or the Gaelyk lightweight toolkit for Google App Engine.",
				None,
				Some("glaforge"),
				Some("http://glaforge.appspot.com/")).create.get

			val charles = Speaker(Id(0),
				"Charles Nutter",
				"JRuby Team",
				"",
				None,
				Some("headius"),
				Some("http://blog.headius.com/")).create.get

			val kai = Speaker(Id(0),
				"Kai Wähner",
				"IT Consultant, MaibornWolff et al GmbH",
				"Kai Wähner works as an IT-Consultant at MaibornWolff et al in Munich, Germany. His main area of expertise lies within the fields of Java EE, SOA, Cloud Computing, and Enterprise Architecture Management.\nHe is speaker at international IT conferences such as JavaOne or Jazoon, writes articles for professional journals, and shares his experiences with new technologies on his blog (www.kai-waehner.de/blog). Contact: kai.waehner@mwea.de or Twitter: @KaiWaehner.",
				Some("kai.waehner@mwea.de"),
				Some("KaiWaehner"),
				Some("http://www.kai-waehner.de/blog")).create.get

			val graeme = Speaker(Id(0),
				"Graeme Rocher",
				"VMware",
				"",
				Some("graeme.rocher@gmail.com"),
				Some("graemerocher"),
				Some("http://grails.io")).create.get

			val edward = Speaker(Id(0),
				"Edward Burns",
				"Consulting Member of Technical Staff, Oracle",
				"Ed Burns is a senior Java engineer at Oracle. Ed has worked on a wide variety of client and server side Web technologies since 1994, including NCSA Mosaic, Mozilla, the Sun Java Plugin, Jakarta Tomcat and, most recently JavaServer Faces. Ed is currently the co-spec lead for JSR 127, JavaServer Faces, a topic on which Ed recently co-authored a book for McGraw-Hill. Ed is an experienced international conference speaker, with consistently high attendance numbers and ratings at the JavaOne conference, JAOO, W-JAX, No Fluff Just Stuff, JA-SIG, The Ajax Experience, and JUGs and Linux User Groups.",
				None,
				Some("edburns"),
				Some("http://ridingthecrest.com/edburns/")).create.get

			val santiago = Speaker(Id(0),
				"Santiago Pericasgeertsen",
				"Software Engineer, Oracle",
				"",
				Some("santiago@cs.bu.edu"),
				None,
				Some("http://cs-people.bu.edu/santiago/")).create.get

			val stephen = Speaker(Id(0),
				"Stephen Chin",
				"Java Evangelist, Oracle",
				"Stephen Chin is a Java Evangelist at Oracle specializing in UI technology and co-author of the Pro JavaFX Platform 2 title, which is the leading technical reference for JavaFX. He has been featured at Java conferences around the world including Devoxx, Codemash, OSCON, J-Fall, GeeCON, Jazoon, and JavaOne, where he twice received a Rock Star Award. In his evenings and weekends, Stephen is an open-source hacker, working on projects including ScalaFX, a DSL for JavaFX in the Scala language, Visage, a UI oriented JVM language, JFXtras, a JavaFX component and extension library, and Apropos, an Agile Project Portfolio scheduling tool written in JavaFX. Stephen can be followed on twitter @steveonjava and reached via his blog: http://steveonjava.com",
				None,
				Some("steveonjava"),
				Some("http://steveonjava.com")).create.get

			val nic = Speaker(Id(0),
				"Nic Williams",
				"VP of Developer Evangelism at Engine Yard",
				"Dr Nic is a developer's developer. He writes blog posts for developers; creates tools, libraries and text editor extensions for developers; and speaks to developers at conferences. He's the VP of Developer Evangelism at Engine Yard, the premier platform as a service. He's Australian and living in the Bay Area. And he's funny; if you can understand his accent.",
				Some("drnicwilliams@gmail.com"),
				Some("drnic"),
				Some("http://drnicwilliams.com/")).create.get

			val raghavan = Speaker(Id(0),
				"Raghavan \"Rags\" Srinivas",
				"Developer Advocate, Couchbase",
				"Raghavan \"Rags\" Srinivas is a Developer Advocate at Couchbase getting his hands dirty with emerging technology directions and trends. His general focus area is in distributed systems, with a specialization in cloud computing. He worked on Hadoop and HBase during its early stages. He has spoken on a variety of technical topics at conferences around the world, conducted and organized Hands-on Labs and taught graduate classes in the evening. He is also a repeat JavaOne rock star speaker award winner.\nRags brings with him over 20 years of hands-on software development and over 10 years of architecture and technology evangelism experience. He worked for Digital Equipment Corporation, Sun Microsystems, Intuit and Accenture. He has worked on several technology areas, including internals of VMS, Unix and NT to Hadoop and HBase. He has evangelized and influenced the architecture of a number of technology areas including the early releases of JavaFX, Java, Java EE, Java and XML, Java ME, AJAX and Web 2.0, Java Security and so on.\nRags holds a Masters degree in Computer Science from the Center of Advanced Computer Studies at the University of Louisiana at Lafayette.",
				None,
				Some("ragss"),
				Some("http://ragss.wordpress.com/")).create.get

			val aaron = Speaker(Id(0),
				"Aaron Bedra",
				"Senior Software Engineer, Groupon",
				"Aaron Bedra is a senior software engineer at Groupon. He is a frequent contributor to the Clojure language and its supporting libraries as well as an active member of the Clojure community.\nAaron has led the development of several commercial Clojure projects and is the co-author of Programming Clojure, 2nd Edition and the upcoming Practical Software Security Book.",
				None,
				Some("abedra"),
				Some("http://www.aaronbedra.com/")).create.get

			val thomas = Speaker(Id(0),
				"Thomas Enebo",
				"JRuby guy, Red Hat",
				"Thomas Enebo is the co-lead of the JRuby project and an employee of Red Hat. He has been a practitioner of Java since the heady days of the HotJava browser, and he has been happily using Ruby since 2001. Thomas has spoken at numerous Java and Ruby conferences, co-authored \"Using JRuby\", and was awarded the \"Rock Star\" award at JavaOne. When Thomas is not working he enjoys biking, anime, and drinking a decent IPA.",
				None,
				Some("tom_enebo"),
				Some("http://blog.enebo.com/")).create.get

			/********************************************************************************************************************/
			/*************************************** EVENTS *********************************************************************/
			/********************************************************************************************************************/

			val CON4345 = S1Event(Id(0),
				"CON4345",
				"Up, Up, and Out: Scaling Software with Akka",
				"Developing concurrent and/or distributed applications often involves choosing between productivity and scalability: do I use easy-to-understand paradigms that don’t provide maximal throughput, or do I create complex and fast code that’s difficult to understand and maintain? This session introduces Akka, which was designed to eliminate the need to make this choice by adopting the actor concurrency model found in other languages such as Erlang and bringing it to the JVM. It is designed to both scale up (by using multiple cores or CPUs on a single machine) and out (by distributing work among multiple VMs) and to support both via configuration and automatic VM clustering rather than code changes. It is also open source, under the Apache V2 license.",
				new DateTime(2012, 10, 4, 11, 0),
				new DateTime(2012, 10, 4, 12, 0),
				"Hilton San Francisco - Golden Gate 3/4/5",
				Seq(viktor.id.get)).create

			val BOF6890 = S1Event(Id(0),
				"BOF6890",
				"Strategies for Testing Event-Driven Programs",
				"Testing strategies are well understood and established in traditional OO and/or functional settings. The same is not true for asynchronous, event-driven paradigms such as the actor model, in which the timing and execution context of assertions is important and temporal relations between generated events become test objectives. This session introduces the Akka TestKit, which supports a wide range of testing approaches, from white box to black box, detailing strengths and weaknesses of the different levels at which tests can be conducted. The TestKit’s mostly passive approach is complemented by specialized frameworks such as Basset and Setak, which control event schedules as well as static verification of finite state machines.",
				new DateTime(2012, 10, 2, 17, 30),
				new DateTime(2012, 10, 2, 18, 15),
				"Hilton San Francisco - Continental Ballroom 5",
				Seq(roland.id.get)).create

			val CON3454 = S1Event(Id(0),
				"CON3454",
				"Concurrency Without Pain in Pure Java",
				"Programming concurrency has turned into a Herculean task. This session’s speaker calls the traditional approach the “synchronize and suffer model.” Fortunately, there are other approaches to concurrency and you can reach out to those directly from your Java code. This presentation discusses actor-based concurrency and software transaction memory. It then develops examples with Akka and compares the power of these approaches with the traditional approach.",
				new DateTime(2012, 10, 1, 10, 0),
				new DateTime(2012, 10, 1, 11, 0),
				"Hilton San Francisco - Yosemite A/B/C",
				Seq(venkat.id.get)).create

			val CON3845 = S1Event(Id(0),
				"CON3845",
				"Introduction to the Play Framework",
				"The Play Framework is a lightweight, stateless Web framework for Java and Scala applications. It’s built on Java NIO, so it’s highly scalable. This session gives you an introduction to building Web applications with the Play Framework. You will learn how to set up routes and create controllers and views, plus how to deploy Play Framework applications in the cloud.",
				new DateTime(2012, 10, 2, 8, 30),
				new DateTime(2012, 10, 2, 9, 30),
				"Hilton San Francisco - Continental Ballroom 5",
				Seq(jward.id.get)).create

			val CON6008 = S1Event(Id(0),
				"CON6008",
				"Modern Java Web Development with Play Framework 2.0",
				"The original version of the Play framework brought back the fun to Java Web development. Developer-friendliness, rapid application development, and agile practices were taken to the next level. Play 2.0 (playframework.org) is trying to push the envelope even further by providing tools for dealing with the ever-changing and complex requirements of today’s Web, such as various ways of doing asynchronous request processing to handle JSON; forms; templates; asynchronous HTTP calls; functional tests; and even coffeescript, LESS, and JavaScript compilation. In this session, you will learn how Java developers can take advantage of all these exciting new features.",
				new DateTime(2012, 10, 2, 10, 0),
				new DateTime(2012, 10, 2, 11, 0),
				"Parc 55 - Cyril Magnin I",
				Seq(peter.id.get)).create

			val BOF4149 = S1Event(Id(0),
				"BOF4149",
				"Web Framework Smackdown 2012",
				"Much has changed since the first Web framework smackdown, at JavaOne 2005. Or has it? The 2012 edition of this popular panel discussion surveys the current landscape of Web UI frameworks for the Java platform. The 2005 edition featured JSF, Webwork, Struts, Tapestry, and Wicket. The 2012 edition features representatives of the current crop of frameworks, with a special emphasis on frameworks that leverage HTML5 and thin-server architecture. Java Champion Markus Eisele leads the lively discussion with panelists James Ward (Play), Graeme Rocher (Grails), Edward Burns (JSF) and Santiago Pericasgeertsen (Avatar).",
				new DateTime(2012, 10, 1, 20, 30),
				new DateTime(2012, 10, 1, 21, 15),
				"Parc 55 - Cyril Magnin II/III",
				Seq(markus.id.get, graeme.id.get, jward.id.get, edward.id.get, santiago.id.get)).create

			val CON5329 = S1Event(Id(0),
				"CON5329",
				"JavaFX and Scala, Like Milk and Cookies",
				"This presentation demonstrates the benefits of using JavaFX 2 together with the Scala programming language to provide a type-safe declarative syntax with support for lazy bindings and collections. It discusses advanced language features such as DelayedInit and @specialized and outlines ways of forcing prioritization of implicit conversions for n-level cases. Those who survive the pure technical geekiness of this talk will be rewarded with plenty of JavaFX UI eye candy.",
				new DateTime(2012, 10, 1, 11, 30),
				new DateTime(2012, 10, 1, 12, 30),
				"Hilton San Francisco - Imperial Ballroom B",
				Seq(luc.id.get, stephen.id.get)).create

			val CON3395 = S1Event(Id(0),
				"CON3395",
				"How Scala, Wicket, and Java EE Can Improve Web Development",
				"This session shows how to mix the Apache Wicket framework, the Scala language, and its DSL advantages on top of Java EE and the GlassFish platform, using the Gamboa Project with a full-featured Maven archetype.",
				new DateTime(2012, 10, 4, 15, 30),
				new DateTime(2012, 10, 4, 16, 30),
				"Parc 55 - Cyril Magnin I",
				Seq(bruno.id.get)).create

			val CON4648 = S1Event(Id(0),
				"CON4648",
				"Scala, JavaFX, Java EE 7, and Enterprise Integration",
				"This session shows you how to use Scala, one of the hottest technologies, to build a JavaFX application that talks to a Java EE 7 application server. Scala is an object-functional language that improves on Java in several areas. Java EE 7 is the enterprise edition “taking it to the cloud” with virtualization. This session shows how to build a Scala application with ScalaFX, a domain-specific language and framework, and tie it to an application that stores data to the cloud. It addresses the limitations of the technologies chosen to write the application. Finally, it also discusses the hot issue of the moment: Should I stick with the Spring Framework or dump it to go with the Java EE specification.",
				new DateTime(2012, 10, 3, 16, 30),
				new DateTime(2012, 10, 3, 17, 30),
				"Hilton San Francisco - Imperial Ballroom B",
				Seq(ppilgrim.id.get)).create

			val CON3470 = S1Event(Id(0),
				"CON3470",
				"Scala Tricks",
				"Scala is a very powerful hybrid functional pure object oriented language on the Java Virtual Machine (JVM). Scala is known for its conciseness and expressiveness. This presentation looks at some common tasks you do everyday in developing applications and shows how they manifest in Scala. You will learn about the strengths of Scala from application development point of view. Rather than focusing on the syntax of Scala, the focus here is on Scala idioms and powerful Scala libraries for performing routine tasks.",
				new DateTime(2012, 10, 2, 13, 0),
				new DateTime(2012, 10, 2, 14, 0),
				"Hilton San Francisco - Golden Gate 3/4/5",
				Seq(venkat.id.get)).create

			val CON6338 = S1Event(Id(0),
				"CON6338",
				"What’s New in Scala 2.10",
				"This session focuses on Scala 2.10. The main features of this latest version of the Scala language are a comprehensive reflection library, interoperability with dynamically typed languages through the Dynamic type, value classes that avoid boxing operations, flexible string interpolation, and a core concurrency library that is the foundation of parallel collections and actors. You’ll see a quick introduction to each new feature, together with illustrative use cases.",
				new DateTime(2012, 10, 3, 13, 0),
				new DateTime(2012, 10, 3, 14, 0),
				"Hilton San Francisco - Golden Gate 6/7/8",
				Seq(martin.id.get)).create

			val CON5774 = S1Event(Id(0),
				"CON5774",
				"Who’s More Functional: Kotlin, Groovy, Scala, or Java?",
				"What is “functional programming” (FP)? Is it a paradigm, a mind-set, or just a buzzword? What’s good about it? How can we benefit from it? This session is about functional aspects of modern programming languages and their costs and benefits. As the mainstream languages rely on object orientation, the session presents “functional style” as a set of design patterns that have been well known in the object-oriented (OO) community for the last two decades but that sometimes appear as a revelation to the FP community.",
				new DateTime(2012, 10, 3, 8, 30),
				new DateTime(2012, 10, 3, 9, 30),
				"Hilton San Francisco - Golden Gate 3/4/5",
				Seq(andrey.id.get)).create

			val BOF6806 = S1Event(Id(0),
				"BOF6806",
				"Hazelcast: Scalable Data Structures",
				"This session discusses distributed implementations of queue, map, list, multimap, lock, and CountDownLatch in Hazelcast. You’ll learn how they can be used to easily build highly scalable distributed applications.",
				new DateTime(2012, 10, 2, 18, 30),
				new DateTime(2012, 10, 2, 19, 15),
				"Parc 55 - Embarcadero",
				Seq(talip.id.get)).create

			val CON2552 = S1Event(Id(0),
				"CON2552",
				"Case Study: Rewriting an Open Source Music Program in Scala",
				"The best way to learn a new language is to use it, but there is an enormous gap between HelloWorld.scala and a “real” project. To help with his own effort to learn Scala, this session’s speaker rewrote his Log4JFugue open source project, which—at about 2,500 lines of code—was big enough to be real yet small enough to be manageable. This presentation briefly describes the problem space addressed by Log4JFugue and then summarizes the architecture of the Java version. The majority of the session is a hands-on, code-on-the-fly re-creation of the Scala version from scratch. You will see the differences between the languages and get a feel for coding in the functional paradigm. You will also understand that Scala need not be scary. No background in Scala is required.",
				new DateTime(2012, 10, 2, 15, 0),
				new DateTime(2012, 10, 2, 16, 0),
				"Hilton San Francisco - Golden Gate 6/7/8",
				Seq(brian.id.get)).create

			val CON3257 = S1Event(Id(0),
				"CON3257",
				"Script Bowl 2012: The Battle of the JVM-Based Languages",
				"Languages that run on the JVM are represented by their gurus in this session. These advocates show off the shiny new language features and battle for bragging rights for the most popular language. Returning from 2011 are language gurus representing Clojure, Groovy, JRuby, and Scala. After attending this fun-filled and technically invigorating session, attendees will be able to judge for themselves which scripting language meets their needs best. They’ll also be able to compare and contrast the respective languages and possibly spark some thought-provoking discussions with the panelists that will be beneficial to the entire Java community. And best of all, the audience will participate in selecting this year’s winner(s).",
				new DateTime(2012, 10, 3, 11, 30),
				new DateTime(2012, 10, 3, 12, 30),
				"Hilton San Francisco - Plaza A/B",
				Seq(guillaume.id.get, nic.id.get, raghavan.id.get, aaron.id.get)).create

			val CON2989 = S1Event(Id(0),
				"CON2989",
				"Leverage Enterprise Integration Patterns with Apache Camel and Twitter",
				"Learn how to take advantage of enterprise integration patterns with the Twitter social network as an example of datasource and destiny. This session shows how to start integrating systems with Apache Camel, the top Java EIP framework. It also covers how to design, define, and run routes with Java, XML, or Scala DSLs.",
				new DateTime(2012, 10, 3, 16, 30),
				new DateTime(2012, 10, 3, 17, 30),
				"Parc 55 - Mission",
				Seq(bruno.id.get)).create

			val CON6575 = S1Event(Id(0),
				"CON6575",
				"Polyglot for Dummies",
				"Why are people writing applications in languages other than Java? Can I really be productive with another language? Which language should I choose? Why would I use more than one? This session survey several popular JVM languages and explores how they can help you build better applications. It shows how to integrate multiple languages in a single app and walks through the speakers’ favorite features and libraries. Java, Ruby, Scala, Clojure, and Groovy ... all work harmoniously on the polyglot JVM.",
				new DateTime(2012, 10, 3, 11, 30),
				new DateTime(2012, 10, 3, 12, 30),
				"Hilton San Francisco - Golden Gate 6/7/8",
				Seq(charles.id.get, thomas.id.get)).create

			val CON2424 = S1Event(Id(0),
				"CON2424",
				"Lessons Learned: Use of Modern JVM Languages Besides Java",
				"Java is the main language for the JVM, but it has several shortcomings. In the last few years, several modern languages such as Groovy, Scala, and Clojure have emerged for the JVM besides the almighty Java to improve the programming experience and create sustainable software. This session presents a progress report that shows why some of these modern languages should be used in projects. The main goal is to provide information about the business value of introducing additional languages, but the presentation does include some code examples.",
				new DateTime(2012, 10, 1, 10, 0),
				new DateTime(2012, 10, 1, 11, 0),
				"Hilton San Francisco - Golden Gate 6/7/8",
				Seq(kai.id.get)).create

		}
	}
}