package mazerunner.actor.session

import javax.inject.Inject

import akka.actor.Actor
import mazerunner.actor.session.SessionActor._
import play.api.cache.CacheApi

class SessionActor @Inject()(cache: CacheApi) extends Actor {

  def receive = {

    case CacheUser(username, token) => sender() ! {
      if (cache.get[String](username).isDefined) CacheDeclined
      else {
        cache.set(username, token)
        CacheAccepted
      }
    }

    case RemoveUser(username) => cache.remove(username)
  }

}

object SessionActor {
  case class CacheUser(username: String, token: String)
  case class RemoveUser(username: String)

  sealed trait CacheStatus
  case object CacheAccepted extends CacheStatus
  case object CacheDeclined extends CacheStatus
}
