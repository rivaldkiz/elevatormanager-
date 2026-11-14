package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Etage
import models.Enums
import tables.BatimentCompagnon.batiments
import tables.EtageCompagnon.etages

class EtageTable(tag: Tag) extends Table[Etage](tag, "ETAGE") {
  def idEtage = column[Long]("ID_ETAGE", O.PrimaryKey, O.AutoInc)
  def idBatiment = column[Long]("ID_BATIMENT")
  def numero = column[Int]("NUMERO")
  def nom = column[Option[String]]("NOM", O.Length(50))
  def * = (idEtage.?, idBatiment, numero, nom) <> (
    (Etage.apply _).tupled,
    Etage.unapply
  )

  def batimentFk =
    foreignKey("FK_ETAGE_BATIMENT", idBatiment, batiments)(_.idBatiment)
  def uniqueNum =
    index("UQ_ETAGE_BATIMENT_NUMERO", (idBatiment, numero), unique = true)

}

object EtageCompagnon {
  val etages = TableQuery[EtageTable]

}
