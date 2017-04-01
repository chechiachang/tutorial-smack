import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

/**
  * Created by davidchang on 4/1/17.
  */

case class Hire(name: String)
case class Name(name: String)

class Boss extends Actor{
  override def receive: Receive = {
    case Hire(name) =>
      println(s"$name is about to be hired")
      val employee = context.actorOf(Props[Employee], name = s"$name")
    case _ => println(s"The boss can't handle this message")
  }
}

class Employee extends Actor{
  var name = "Employee Name"

  override def postStop(): Unit = {
    println(s"I'm ($name) and Mr. Burns fired me: ${self.path}")
  }

  override def receive: Receive = {
    case Name(name) => this.name = name
    case _ => println(s"The Employee $name can't handle this message")
  }
}

object StartingActorsDemo extends App{
  val actorSystem = ActorSystem("StartingActorSystem")
  val mrBurns = actorSystem.actorOf(Props[Boss], name = "MrBurns")

  mrBurns ! Hire("HomerSimpson")
  mrBurns ! Hire("FrankGrimes")

  Thread.sleep(4000)

  println("===Firing Frank Grimes ...")
  val grimes = actorSystem.actorSelection("../user/MrBurns/FrankGrimes")

  grimes ! PoisonPill
  println("===Frank Grimes is fired")
}
