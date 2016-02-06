package mazerunner.actor.matchmaking

import akka.actor.{ActorRef, Actor}


class MatchMakerActor extends Actor {
  /* Stores all available & ready-to-play player refs*/
  val playerPool = Set[ActorRef]()

  def receive = ???
}
