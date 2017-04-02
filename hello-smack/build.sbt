name := """hello-smack"""

version := "1.0"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.4.0"
lazy val sparkVersion = "1.6.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.apache.spark" % "spark-core_2.11" % "2.1.0",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
