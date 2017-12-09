package com.gaguena.core.rest

import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

import org.json4s.jackson.Serialization.write
import org.scalatra.{ AsyncResult, BadRequest, NotFound }

import com.gaguena.core.exceptions.AppException
import com.gaguena.core.model.Game
import com.gaguena.core.scalatra.{ ResponseMessage, ScalatraSupport }
import com.gaguena.core.service.GameServiceComponent

object GameRest {
  val GameMediaType = "application/vnd.gaguena.game.v1+json"
}

class GameRest extends ScalatraSupport with GameServiceComponent {

  override val acceptmediaTypes = List(GameRest.GameMediaType)

  get("/") {
    new AsyncResult {
      val is = gameService.findAll map(_ match {
        case list if !list.isEmpty => list
        case _ => notFound("Games não encontrados")
      })
    }
  }

  get("/:id") {
    new AsyncResult {
      val is = gameService.findGameAndGenreBy(params("id").toLong)
        .recover { case ex: AppException => notFound(ex.getMessage) }
    }
  }

  post("/") {
    new AsyncResult {
      val is = {
        Try(parse(request.body).extract[Game]) match {
          case Success(game) => gameService.save(game)
          case Failure(_) => Future.successful(badRequest("Corpo darequisição(parametros) invalidos"))
        }
      }
    }
  }

}
