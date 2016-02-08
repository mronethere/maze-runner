package mazerunner.common

import play.api.libs.json.JsResult

import scala.concurrent.Future

object JsonTransformer {
  def transformAsFuture[T](json: JsResult[T]): Future[T] =
    if (json.isSuccess) Future.successful(json.get)
    else Future.failed(new Exception("error parsing json"))
}
