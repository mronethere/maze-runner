package com.mazerunner.db.table

import com.mazerunner.common.models.User
import slick.driver.JdbcProfile

trait UserTable {
  protected val driver: JdbcProfile
  import driver.api._

  class Users(tag: Tag) extends Table[User](tag, "USER") {
    def username = column[String]("USERNAME", O.PrimaryKey)
    def password = column[String]("PASSWORD")

    def * = (username, password) <> (User.tupled, User.unapply)
  }
}
