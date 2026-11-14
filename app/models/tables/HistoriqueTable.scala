package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Historique
import models.Enums
import tables.UtilisateurCompagnon.utilisateurs

class HistoriqueTable(tag: Tag) extends Table[Historique](tag, "HISTORIQUE") {
  def idHistorique = column[Long]("ID_HISTORIQUE", O.PrimaryKey, O.AutoInc)
  def idUser = column[Long]("ID_USER")
  def typeAction = column[String]("TYPE_ACTION", O.Length(25))
  def description = column[Option[String]]("DESCRIPTION", O.Length(500))
  def dateAction = column[LocalDateTime]("DATE_ACTION")
  def * = (idHistorique.?, idUser, typeAction, description, dateAction) <>
    ((Historique.apply _).tupled, Historique.unapply)

  def userFk = foreignKey("FK_HISTORIQUE_USER", idUser, utilisateurs)(
    _.idUser,
    onDelete = ForeignKeyAction.Cascade
  )

}

object HistoriqueCompagnon {
  val historiques = TableQuery[HistoriqueTable]
}
