Akka
===

# Actor Model

### Why Actor

* A mathematical model by Carl Hewitt(1973)
* When hardware was expensive(relative to software), we have to think in assembly language, memory location
* Today, we use high level language, think in abstraction
* OOP is facing problem: implementation of concurrency and parallelism
* Race condition, semaphores, mutexes, locks, shared data, and multi-threading

### Actors 101

* An actors is a object that sends and receives messages
* Order of received messages is not relevant
* Mailbox is a stack where messages are consumed

### Between OOP and actors

* Unit. objects are the smallest unit in OOP, as the actors in the actor model
* Encapsulation. The encapsulates state of actors are determined by the messages. The actor will wait for messages if empty mailbox.
* Access. Access to actor's methods or fields is strictly prohibited. All the communication must be done by messages;
* Globals. Global variables don't exist
* Messages. Messages between actors are strictly immutable.
* Exceptions. If an actor is going to crash, "let it crash".
* Concurrent and parallelism. Everything follows the actor convention, and there is no problem with parallelism

### Lightbend recommendations

* Actors as employees, actor model as a company
* Actor's siblings as people in the same hierarchical level
* Actor's children as the employee's subordinates
* An actor has one (and only one) supervisor, the actor who created it.
* Actor models success is the delegation to subordinates

Ligntbend is a family of five members: Scala, Akka, Spark, Lagom, Play

### Akka implementation

* When creating an actor, Akka provides an ActorRef
* Actors run in real Java threads. Some actors could share the same thread
* Three mailbox types: Unbounded, Bounded, and Priority
* Actors can scan their mailboxes to look for specific messages
* A dead letter mailbox with all the actors's terminated messages

### Build first App

* import akka.actor.{Actor, ActorSystem, Props}
* define a class of type Actor
* implements receives() method
* create a Main object extends App
* create a actor system
* actorSystem.actorOf to liven up an actor. The actor will start asynchronously
* send messages with the ! operator
* call the shutdown method in the actor system(Deprecated)

### Actor System
Like a theater company
* Hierarchy: each actor always has a supervisor and can have siblings and children
* Actors share dispatcher, deployments, and addresses within an actor system
* Actor system is the meeting point where actors are created and searched
* A thread controller, decide when to create threads for app
* App will continue running as the system does not turn off actors

### Actor Reference
Like an actor's agent, represents actor and receives messages
* actorOf: start the actor asynchronously and return the ActorRef requested.
* A handler. Cannot directly access actor.
* Follows the facade pattern over the actor, communicate without directly access the actor
* ActorRef is immutable. It's only a reference.
* Actor to ActorRef is a one-to-one relationship
* ActorRef is serializable and server independent. Can be distributed across the network

### Actor Communication

* use ! to send a message to an actor
* ! operator work with ActorRefs
* An actor receives a reference to the actor sent the message. Reference is accessed by the sender variable
* sender is a reserved word

### Actor Lifecycle

Methods:
0. (constructor): instantiate as in Java
* preStart: called immediately after actor started
* postStop: called immediately after actor stopped for cleaning work
* preRestart: called immediately after actor restarted. Receive Throwable causing the restart
* postRestart: called immediately after actor restarted. postRestart receive a Throwable causing the restart
* receive

### Actors Do Not Block Each Other

* An actor blocks another actor, the first actor cannot attend to a request
* An actor is locked while working on the first, you cannot attend the second request
* deadlock: Actors block each other
* prioritize message to prevent lock
* Never use Thread.sleep, avoid use Thread. Modify your actor classes to achieve your goals

### Communication is only via Messages

### Messages must be immutable

### Messages must be Self-Contained
