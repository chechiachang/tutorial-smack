name := """hello-smack"""

version := "1.0"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.4.0"
lazy val sparkVersion = "2.1.0"
lazy val kafkaVersion = "0.10.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",

  // https://mvnrepository.com/artifact/org.apache.spark/spark-core_2.11
  "org.apache.spark" % "spark-core_2.11" % sparkVersion,
  "org.apache.spark" % "spark-streaming_2.11" % sparkVersion,

  // https://mvnrepository.com/artifact/com.datastax.spark/spark-cassandra-connector_2.11
  "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.1",

  // https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.11
  "org.apache.kafka" % "kafka_2.11" % kafkaVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
