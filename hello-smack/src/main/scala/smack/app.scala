package smack

import java.util.UUID

import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by davidchang on 4/3/17.
  */

case class Campaign(id: UUID, ad_id: UUID, campaign: UUID, ts: Long, `type`: String)

object app extends App{
  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")

  val sc = new SparkContext("spark://192.168.0.21:7077", "appName", conf)

  val rdd = sc.cassandraTable[Campaign]("mykeyspace", "compaign")

  println(rdd.count)
  println(rdd.first)
  println(rdd.where("true"))

//  val connector = CassandraConnector(sc.getConf)
//  connector.withSessionDo(session =>
//    session.execute("CREATE KEYSPACE test2 WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 }")
//  )
}


