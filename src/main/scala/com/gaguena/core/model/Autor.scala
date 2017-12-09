package com.gaguena.core.model

import java.time.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.gaguena.core.persistence.Repository
import com.gaguena.core.persistence.tables.AutorTable

import slick.driver.MySQLDriver.api._

case class Autor(id: Option[Long], nome: String, registerDH: LocalDateTime = LocalDateTime.now)

object Autores extends Repository[Autor] {
  lazy val all = TableQuery[AutorTable]

  def salvar(autor: Autor) =
    transactionally((all returning all.map(_.id)) insertOrUpdate autor map (_.map(id => autor.copy(id = id)).getOrElse(autor)))

  def findAll: Future[List[Autor]] = db.run(all.result.map(_.toList))

  def create = db.run((all.schema).create)
}