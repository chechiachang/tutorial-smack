import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by davidchang on 4/1/17.
  */

class Scapegoat extends Actor{
  override def receive: Receive = {
    case s:String => println("Message received: " + s)
    case _ => println("What?")
  }
}

object StopExample extends App{
  val system = ActorSystem("StopExample")
  val sg = system.actorOf(Props[Scapegoat], name = "ScapeGoat")
  sg ! "ready?"

  system.stop(sg)
  system.terminate()
}
