import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import ScalateKeys._

val ScalatraVersion = "2.5.1"

ScalatraPlugin.scalatraSettings

scalateSettings

organization := "com.gaguena"

name := "NewGames"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.1"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-json" % ScalatraVersion,
  "org.json4s"   %% "json4s-jackson" % "3.5.0",
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "net.databinder.dispatch" %% "dispatch-core" % "0.12.0",
  "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container",
  "mysql" % "mysql-connector-java" % "5.1.42",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)

libraryDependencies ++= Seq(
 "org.specs2" %% "specs2-core" % "3.9.0" % "test",
 "org.specs2" %% "specs2-junit" % "3.9.0" % "test",
 "org.hsqldb" % "hsqldb" % "2.3.4" % "test",
 "junit" % "junit" % "4.11" % "test",
 "org.scalatra" %% "scalatra-specs2" % "2.5.1" % "test",
 "br.com.six2six" % "fixture-factory" % "3.1.0" % "test",
 "org.scalamock" %% "scalamock-specs2-support" % "3.6.0" % "test"
)

enablePlugins(JettyPlugin)

javaOptions ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000"
)
