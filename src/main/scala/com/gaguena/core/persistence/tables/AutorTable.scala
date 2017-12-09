package com.gaguena.core.persistence.tables

import com.gaguena.core.model.Autor
import com.gaguena.core.persistence.tables.DataBaseConversions._

import slick.driver.MySQLDriver.api._

class AutorTable(tag: Tag) extends Table[Autor](tag, "autor") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def nome = column[String]("nome")
  def registerDH = column[java.time.LocalDateTime]("register_dh")
  def * = (id, nome, registerDH) <> (Autor.tupled, Autor.unapply)
}