package mazerunner.db

import mazerunner.db.table.UserTable
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration


class Repository[Profile <: JdbcProfile](protected val dbConfig: DatabaseConfig[Profile])
    extends UserTable {

  lazy val driver: Profile = dbConfig.driver
  def db: Profile#Backend#Database = dbConfig.db

  import driver.api._

  val users = TableQuery[Users]
  val schema = users.schema // concatenate with '++' other tables

  def init(): Unit = try {
    Await.ready(db.run(schema.create), Duration.Inf)
  } catch {
    case e: Exception => e.printStackTrace()
  } finally db.close()
}
