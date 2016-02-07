package mazerunner.db.table

import mazerunner.common.model.User
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
