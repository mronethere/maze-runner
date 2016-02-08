package mazerunner.actor

import com.google.inject.AbstractModule
import mazerunner.actor.session.SessionActor
import play.api.libs.concurrent.AkkaGuiceSupport


class ActorBinder extends AbstractModule with AkkaGuiceSupport {
  def configure() = {
    bindActor[SessionActor]("session-actor")
  }
}
