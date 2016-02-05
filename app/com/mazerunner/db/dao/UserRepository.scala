package com.mazerunner.db.dao

import javax.inject.Inject

import akka.actor.ActorSystem
import com.mazerunner.common.models.User
import com.mazerunner.db.table.UserTable
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton()
class UserRepository @Inject()(protected val system: ActorSystem,
                               protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[JdbcProfile] with UserTable {

  import driver.api._
  import system.dispatcher

  protected val users = TableQuery[Users]

  def all(): Future[Seq[User]] = db.run(users.result)

  def insert(user: User): Future[Unit] = db.run(users += user).map(_ => ())

  def findByUsernameAndPassword(username: String, password: String): Future[User] = db.run {
    users.filter(u => u.username === username && u.password === password).result.head
  }
}
