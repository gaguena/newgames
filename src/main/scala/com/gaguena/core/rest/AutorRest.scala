package com.gaguena.core.rest

import scala.concurrent.Future
import scala.util.{ Failure, Success, Try }

import org.scalatra.AsyncResult

import com.gaguena.core.model.Autor
import com.gaguena.core.scalatra.ScalatraSupport
import com.gaguena.core.service.AutorServiceComponent
import java.time.LocalDateTime

object AutorRest {
  val AutorRestType = "application/vnd.gaguena.autor.v1+json"
}

class AutorRest extends ScalatraSupport with AutorServiceComponent {
  override val acceptmediaTypes = List(AutorRest.AutorRestType)

  post("/") {
    new AsyncResult {
      val is = {
        val body = request.body
        Try(parse(body).extract[Autor]) match {
          case Success(autor) => {
            println(autor)
            autorService.salvar(autor)
          }
          case Failure(_) => Future.successful(badRequest("Corpo da requisição(parametros) invalidos"))
        }
      }
    }
  }
  get("/") {
    val is = Future.successful(Autor(Some(1), "teste", LocalDateTime.now))
  }
}