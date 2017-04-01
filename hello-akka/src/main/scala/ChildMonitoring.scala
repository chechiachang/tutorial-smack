import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

/**
  * Created by davidchang on 4/1/17.
  */
class Child extends Actor{
  override def receive: Receive = {
    case _ => println("Child received a message")
  }
}

class Dad extends Actor{
  val child = context.actorOf(Props[Child], name = "Son")
  context.watch(child)

  override def receive: Receive = {
    case Terminated(child) => println("This will not end here -_-")
    case _ => println("Dad received a message ...")
  }
}

object ChildMonitoring extends App{
  val system = ActorSystem("ChildMonitoring")

  val dad = system.actorOf(Props[Dad], name = "Dad")

  val child = system.actorSelection("/user/Dad/Son")

  child ! PoisonPill
  Thread.sleep(3000)

  println("Revenge!")
  system.terminate()
}
