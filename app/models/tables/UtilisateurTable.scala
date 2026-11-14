package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Utilisateur
import models.Enums
import tables.EtageCompagnon.etages
import tables.RoleCompagnon.roles
import tables.SimulationCompagnon.simulations

class UtilisateurTable(tag: Tag)
    extends Table[Utilisateur](tag, "UTILISATEUR") {
  def idUser = column[Long]("ID_USER", O.PrimaryKey, O.AutoInc)
  def idRole = column[Long]("ID_ROLE")
  def idSimulation = column[Option[Long]]("ID_SIMULATION") // nullable
  def nom = column[String]("NOM", O.Length(100))
  def email = column[String]("EMAIL", O.Length(150))
  def motDePasse =
    column[String]("MOT_DE_PASSE", O.Length(255)) // stocker un hash (BCrypt)
  def telephone = column[Option[String]]("TELEPHONE", O.Length(20))
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutUtilisateur.Actif)
  )
  def dateCreation = column[LocalDateTime]("DATE_CREATION")
  def * = (
    idUser.?,
    idRole,
    idSimulation,
    nom,
    email,
    motDePasse,
    telephone,
    statut,
    dateCreation
  ) <>
    ((Utilisateur.apply _).tupled, Utilisateur.unapply)

  def roleFk = foreignKey("FK_UTILISATEUR_ROLE", idRole, roles)(_.idRole)
  def simulationFk =
    foreignKey("FK_UTILISATEUR_SIMULATION", idSimulation, simulations)(
      _.idSimulation.?
    )
  def emailUnique = index("UQ_UTILISATEUR_EMAIL", email, unique = true)

}

object UtilisateurCompagnon {
  val utilisateurs = TableQuery[UtilisateurTable]
}
