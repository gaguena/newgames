package com.gaguena.core.persistence.tables

import com.gaguena.core.model.Frase
import com.gaguena.core.persistence.tables.DataBaseConversions._

import slick.driver.MySQLDriver.api._

class FraseTable(tag: Tag) extends Table[Frase](tag, "frase") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def texto = column[String]("texto")
  def autorId = column[Option[Long]]("autorId")
  def registerDH = column[java.time.LocalDateTime]("register_dh")
  def * = (id, texto, autorId, registerDH) <> (Frase.tupled, Frase.unapply)

  val autor = TableQuery[AutorTable]

  def autorFK = foreignKey("autor_fk", autorId, autor)(_.id)

}