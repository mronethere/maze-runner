package mazerunner.common

import play.api.Logger

trait Logged {
  val log = Logger(this.getClass)
}
