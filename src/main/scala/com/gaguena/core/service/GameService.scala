package com.gaguena.core.service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.gaguena.core.exceptions.AppException
import com.gaguena.core.model.{ Game, GameGenre, Games, Genre, Genres }

class GameService {

  private def failed[T](message: String): Future[T] = Future.failed(throw new AppException(message))

  def findBy(id: Option[Long]) {
    ???
  }

  def save(game: Game): Future[Game] = {
    Games.salvar(game)
  }

  def findBy(id: Long): Future[Game] = Games.find(id).map(_.getOrElse(throw new AppException("Game não encontrado")))

  def findGameAndGenreBy(id: Long) = {
    def createGameGenre(gameOp: Option[Game], genreOp: Option[Genre]) = {
      def success(game: Game, genre: Genre) = Future.successful(new GameGenre(game, genre))
      gameOp.map(game => genreOp.map(genre => success(game, genre)).getOrElse(failed("Genre não Encontrado")))
        .getOrElse(failed("Game não Encontrado"))
    }
    for {
      gameOp <- Games.find(id)
      genreOp <- Genres.find(id)
      gameGenre <- createGameGenre(gameOp, genreOp)
    } yield gameGenre
  }

  def findAll: Future[List[Game]] = Games.findAll
}

trait GameServiceComponent {
  val gameService = new GameService
}