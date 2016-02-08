package mazerunner.controller

import java.util.UUID
import javax.inject.{Named, Singleton, Inject}

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import mazerunner.actor.session.SessionActor._
import mazerunner.common.ControllerUtils
import mazerunner.common.model.{User, Credentials}
import mazerunner.db.dao.UserRepository
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.duration._

@Singleton
class SecurityController @Inject()(@Named("session-actor") sessionActor: ActorRef,
                                   userRepository: UserRepository)
  extends Controller with ControllerUtils {

  implicit val timeout: Timeout = 5.seconds

  def login = Action.async(parse.json) { request =>
    transformAsFuture(request.body.validate[Credentials])
      .flatMap(creds => userRepository.findByUsernameAndPassword(creds.username, creds.password))
      .flatMap { user =>
        val token = SecurityHelper.generateToken
        (sessionActor ? CacheUser(user.username, token)).mapTo[CacheStatus].map {
          case CacheAccepted => Ok(token)
          case CacheDeclined => Forbidden("forbidden")
        }
      }
      .recover(withRecover)
  }
}

object SecurityHelper {
  def generateToken = UUID.randomUUID().toString
}
