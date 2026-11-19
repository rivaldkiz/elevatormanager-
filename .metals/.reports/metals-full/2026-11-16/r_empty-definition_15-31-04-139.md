error id: file:///C:/Users/franc/Desktop/elevatormanager/build.sbt:
file:///C:/Users/franc/Desktop/elevatormanager/build.sbt
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -libraryDependencies.
	 -libraryDependencies#
	 -libraryDependencies().
	 -scala/Predef.libraryDependencies.
	 -scala/Predef.libraryDependencies#
	 -scala/Predef.libraryDependencies().
offset: 516
uri: file:///C:/Users/franc/Desktop/elevatormanager/build.sbt
text:
```scala
name := """liftmanager"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.18"

libraryDependencies += guice
libraryDependencies += "org.playframework" %% "play-slick" % "6.1.0" // or your current version
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.2" % Test
libraryDependencies +=
  "org.mindrot" % "jbcrypt" % "0.4"

// Adds the Slick dependencies
libraryDependencies@@ ++= Seq(
  guice,
  jdbc,
  "com.typesafe.slick" %% "slick" % "3.5.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.2",
  "com.oracle.database.jdbc" % "ojdbc11" % "23.6.0.24.10",
  "org.mindrot" % "jbcrypt" % "0.4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

```


#### Short summary: 

empty definition using pc, found symbol in pc: 