package com.mazerunner.db

import slick.backend.DatabaseConfig
import slick.driver.MySQLDriver

object DataBaseInit {
  def main(args: Array[String]): Unit = {
    val repo = new Repository[MySQLDriver](DatabaseConfig.forConfig("slick.dbs.default"))
    repo.init()
  }
}
