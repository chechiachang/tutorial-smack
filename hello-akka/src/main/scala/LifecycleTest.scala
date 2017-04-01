import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by davidchang on 4/1/17.
  */

case object GetAngry

class Hulk extends Actor{

  println("==constructor==")

  override def preStart: Unit ={
    println("==preStart==")
  }

  override def postStop: Unit ={
    println("==postStop==")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit ={
    println("==preRestart==")
    println(s" preRestart message: ${message.getOrElse("")}")
    println(s" preRestart reason: ${reason.getMessage}")
    super.preRestart(reason, message)
  }
  override def postRestart(reason: Throwable): Unit ={
    println("==postRestart==")
    println(s" postRestart reason: ${reason.getMessage}")
    super.postRestart(reason)
  }
  override def receive: Receive = {
    case GetAngry => throw new Exception("ROAR!")
    case _ => println("Hulk received a message...")
  }
}

object LifecycleTest extends App{
  val system = ActorSystem("LifeCycleSystem")
  val hulk = system.actorOf(Props[Hulk], name = "TheHulk")
  println("**sending Hulk a message")
  hulk ! "hello Hulk"
  Thread.sleep(5000)
  println("**making hulk get angry")
  hulk ! GetAngry
  Thread.sleep(5000)
  println("**stopping Hulk")
  system.stop(hulk)
  println("**shutting down Hulk system")
  system.terminate()
}
