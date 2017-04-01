import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Created by davidchang on 4/1/17.
  */

case object SendANewCat
case object LiveALife
case object BackToHeaven
case object LifeSpended{
  var remaining = 0
}

class God(indulged: ActorRef) extends Actor {
  override def receive: Receive = {
    case SendANewCat =>
      println("GOD: Go!, you have seven lives")
      indulged ! LiveALife
    case LifeSpended =>
      if( LifeSpended.remaining == 0){
        println("GOD: Time to Return!")
        indulged ! BackToHeaven
      }else{
        println("GOD: one live spent, " + LifeSpended.remaining + " remaining.")
        indulged ! LiveALife
      }

    case _ => println("GOD: Sorry, I don't understand")
  }
}

class Cat extends Actor {
  var lives = 7

  override def receive: Receive = {
    case LiveALife =>
      println("CAT: Thanks God, I still have " + lives + " lives")
      lives -= 1
      LifeSpended.remaining = lives
      sender ! LifeSpended
    case BackToHeaven =>
      println("CAT: No more lives, going to Heaven")
      context.stop(self)
    case _ => println("CAT: Sorry, I don't understand")
  }
}

object CatLife extends App{
  val system = ActorSystem("CatLifeSystem")
  val sylvester = system.actorOf(Props[Cat], name = "Sylvester")
  val catsGod = system.actorOf(Props(new God(sylvester)), name = "CatsGod")

  catsGod ! SendANewCat
  //catsGod ! SendANewCat
  system.terminate()
}
