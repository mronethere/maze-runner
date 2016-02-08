package mazerunner.common

import play.api.libs.json.JsResult
import play.api.mvc.{Result, Controller}

import scala.concurrent.Future

trait ControllerUtils {
  self: Controller =>

  def transformAsFuture[T](json: JsResult[T]): Future[T] =
    if (json.isSuccess) Future.successful(json.get)
    else Future.failed(new Exception("error parsing json"))

  val withRecover: PartialFunction[Throwable, Result] = {
    case e: Exception => BadRequest(s"bad request: ${e.getMessage}")
  }
}
