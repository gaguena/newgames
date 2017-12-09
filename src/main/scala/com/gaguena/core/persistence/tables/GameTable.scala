package com.gaguena.core.persistence.tables

import com.gaguena.core.model.Game
import com.gaguena.core.persistence.tables.DataBaseConversions._

import slick.driver.MySQLDriver.api._
import java.time.LocalDateTime
import com.gaguena.core.model.Genres

class GameTable(tag: Tag) extends Table[Game](tag, "game") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("game_name")
  def description = column[String]("description")
  def registerDH = column[java.time.LocalDateTime]("register_dh")
  def genreID = column[Option[Long]]("genre_id")
  def * = (id, name, description, registerDH, genreID) <> (Game.tupled, Game.unapply)

  val genre = TableQuery[GenreTable]

  def genreFk = foreignKey("genre_fk", genreID, genre)(_.id)

}