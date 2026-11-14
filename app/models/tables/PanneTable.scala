package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Panne
import tables.AscenseurCompagnon.ascenseurs
import tables.UtilisateurCompagnon.utilisateurs
import models.Enums

class PanneTable(tag: Tag) extends Table[Panne](tag, "PANNE") {
  def idPanne = column[Long]("ID_PANNE", O.PrimaryKey, O.AutoInc)
  def idAscenseur = column[Long]("ID_ASCENSEUR")
  def idUser = column[Long]("ID_USER") // utilisateur qui signale
  def description = column[Option[String]]("DESCRIPTION", O.Length(500))
  def categorie = column[String](
    "CATEGORIE",
    O.Length(15),
    O.Default(Enums.CategoriePanne.Autre)
  )
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutPanne.Signalee)
  )
  def datePanne = column[LocalDateTime]("DATE_PANNE")
  def * = (
    idPanne.?,
    idAscenseur,
    idUser,
    description,
    categorie,
    statut,
    datePanne
  ) <>
    ((Panne.apply _).tupled, Panne.unapply)

  def ascenseurFk =
    foreignKey("FK_PANNE_ASCENSEUR", idAscenseur, ascenseurs)(_.idAscenseur)
  def userFk = foreignKey("FK_PANNE_USER", idUser, utilisateurs)(_.idUser)

}

object PanneCompagnon {
  val pannes = TableQuery[PanneTable]

}
