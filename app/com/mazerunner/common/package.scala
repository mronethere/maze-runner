package com.mazerunner

import play.api.libs.json.Json
import play.api.mvc.{AnyContent, Request}

package object common {
  case class Credentials(username: String, password: String)
}
