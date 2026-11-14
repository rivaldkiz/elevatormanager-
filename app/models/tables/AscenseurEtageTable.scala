package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.AscenseurEtage
import models.Enums
import tables.AscenseurCompagnon.ascenseurs
import tables.EtageCompagnon.etages

class AscenseurEtageTable(tag: Tag)
    extends Table[AscenseurEtage](tag, "ASCENSEUR_ETAGE") {
  def idAscenseur = column[Long]("ID_ASCENSEUR")
  def idEtage = column[Long]("ID_ETAGE")
  def * = (idAscenseur, idEtage) <> (
    (AscenseurEtage.apply _).tupled,
    AscenseurEtage.unapply
  )

  def pk = primaryKey(
    "PK_ASCENSEUR_ETAGE",
    (idAscenseur, idEtage)
  ) // clé primaire composée
  def ascenseurFk = foreignKey("FK_AE_ASCENSEUR", idAscenseur, ascenseurs)(
    _.idAscenseur,
    onDelete = ForeignKeyAction.Cascade
  )
  def etageFk = foreignKey("FK_AE_ETAGE", idEtage, etages)(
    _.idEtage,
    onDelete = ForeignKeyAction.Cascade
  )

}

object AscenseurEtageCompagnon {
  val ascenseurEtages = TableQuery[AscenseurEtageTable]
}
