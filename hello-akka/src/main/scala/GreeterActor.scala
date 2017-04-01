import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by davidchang on 4/1/17.
  */
class GreeterActor extends Actor {
  override def receive: Receive = {
    case "en" => println("Good day")
    case "es" => println("Buen dia")
    case "fr" => println("Bonjour")
    case "de" => println("Guten Tag")
    case "pt" => println("Bom dia")
    case _ => println(":(")
  }
}

object Main extends App {
  // build ActorSystem
  val actorSystem = ActorSystem("MultilangSystem")

  // instantiate the actor
  val greeter = actorSystem.actorOf(Props[GreeterActor], name = "GreeterActor")

  greeter ! "en"
  greeter ! "es"
  greeter ! "fr"
  greeter ! "de"
  greeter ! "pt"
  greeter ! "zh-tr"

  actorSystem.shutdown()
}

