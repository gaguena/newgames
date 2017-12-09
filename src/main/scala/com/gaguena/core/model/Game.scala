package com.gaguena.core.model

import java.time.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.gaguena.core.persistence.Repository
import com.gaguena.core.persistence.tables.GameTable

import slick.driver.MySQLDriver.api._
import com.gaguena.core.persistence.tables.GenreTable

case class Game(id: Option[Long], name: String, description: String,
  registerDH: LocalDateTime = LocalDateTime.now, genreID: Option[Long]) {

  def genre() = this.genreID match {
    case Some(genre) => Genres.find(genre)
    case _           => None
  }
}

case class GameGenre(id: Option[Long], name: String, description: String,
    registerDH: LocalDateTime = LocalDateTime.now, genre: Genre){
  def this(game: Game, genre: Genre) = this(game.id, game.name, game.description, game.registerDH, genre)
}

object Games extends Repository[Game] {

  lazy val all = TableQuery[GameTable]
  lazy val genre = TableQuery[GenreTable]

  def salvar(game: Game) =
    transactionally((all returning all.map(_.id)) insertOrUpdate game map (_.map(id => game.copy(id = id)).getOrElse(game)))

  def findAll: Future[List[Game]]= db.run(all.result.map(_.toList))

  def find(id: Long): Future[Option[Game]] =
    db.run(all.filter(_.id === Option(id)).result.map(_.headOption))

  def findByGenreID(id: Long) = db.run(all.join(genre).on(_.genreID === _.id)
    .filter(_._2.id === Option(id)).result.map(_.toList))

  def create = db.run((all.schema).create)
}