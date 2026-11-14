package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Batiment
import models.Enums

class BatimentTable(tag: Tag) extends Table[Batiment](tag, "BATIMENT") {
  def idBatiment = column[Long]("ID_BATIMENT", O.PrimaryKey, O.AutoInc)
  def nom = column[String]("NOM", O.Length(100))
  def adresse = column[Option[String]]("ADRESSE", O.Length(255))
  def description = column[Option[String]]("DESCRIPTION", O.Length(255))
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutBatiment.Actif)
  )
  def * = (idBatiment.?, nom, adresse, description, statut) <> (
    (Batiment.apply _).tupled,
    Batiment.unapply
  )

}

object BatimentCompagnon {
  val batiments = TableQuery[BatimentTable]

}
