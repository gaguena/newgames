package com.gaguena.core.model

import java.time.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import com.gaguena.core.persistence.Repository
import com.gaguena.core.persistence.tables.FraseTable

import slick.driver.MySQLDriver.api._

case class Frase(id: Option[Long], texto: String, autorId: Option[Long], registerDH: LocalDateTime = LocalDateTime.now)

object Frases extends Repository[Frase] {
  lazy val all = TableQuery[FraseTable]

  def salvar(frase: Frase) =
    transactionally((all returning all.map(_.id)) insertOrUpdate frase map (_.map(id => frase.copy(id = id)).getOrElse(frase)))

  def findAll: Future[List[Frase]] = db.run(all.result.map(_.toList))

  def create = db.run((all.schema).create)
}