package com.gaguena.core.model

import scala.concurrent.ExecutionContext.Implicits.global

import com.gaguena.core.persistence.Repository
import com.gaguena.core.persistence.tables.GenreTable
import slick.driver.MySQLDriver.api._
import scala.concurrent.Future

case class Genre(id: Option[Long], name: String)

object Genres extends Repository[Genre] {

  lazy val all = TableQuery[GenreTable](new GenreTable(_))

  def find(id: Long): Future[Option[Genre]] =
    db.run(all.filter(_.id === Option(id)).result.map(_.headOption))

  def create = db.run((all.schema).create)
}
