name := """twitter-kafka"""
organization := "kafka"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.0.0",
  ws,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  "org.apache.kafka" % "kafka-clients" % "2.0.0")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "kafka.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "kafka.binders._"
