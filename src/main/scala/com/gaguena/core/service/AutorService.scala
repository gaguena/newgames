package com.gaguena.core.service

import scala.concurrent.ExecutionContext.Implicits.global

import com.gaguena.core.model.{ Autor, Autores }
import scala.concurrent.Future

class AutorService extends ServiceSupport[Autor] {
  def salvar(autor: Autor): Future[Autor] = {
    Autores.salvar(autor)
  }
}

trait AutorServiceComponent {
  val autorService = new AutorService
}
