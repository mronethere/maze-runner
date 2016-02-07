package mazerunner.common.model

import play.api.libs.json.Json


case class Credentials(username: String, password: String)
object Credentials {
  implicit val credentialsFormat = Json.format[Credentials]
}

case class User(username: String, password: String)
