package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Appel
import models.Enums
import tables.UtilisateurCompagnon.utilisateurs
import tables.EtageCompagnon.etages
import tables.SimulationCompagnon.simulations

class AppelTable(tag: Tag) extends Table[Appel](tag, "APPEL") {
  def idAppel = column[Long]("ID_APPEL", O.PrimaryKey, O.AutoInc)
  def idUser = column[Long]("ID_USER")
  def depart = column[Long]("DEPART") // FK -> ETAGE
  def destination = column[Long]("DESTINATION") // FK -> ETAGE
  def idSimulation = column[Option[Long]]("ID_SIMULATION") // nullable
  def direction = column[String]("DIRECTION", O.Length(10))
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutAppel.EnAttente)
  )
  def avecBagages = column[Boolean]("AVEC_BAGAGES", O.Default(false))
  def dateAppel = column[LocalDateTime]("DATE_APPEL")
  def scoreAttribution = column[Option[BigDecimal]]("SCORE_ATTRIBUTION")

  def * = (
    idAppel.?,
    idUser,
    depart,
    destination,
    idSimulation,
    direction,
    statut,
    avecBagages,
    dateAppel,
    scoreAttribution
  ) <> (Appel.tupled, Appel.unapply)

  def userFk = foreignKey("FK_APPEL_USER", idUser, utilisateurs)(_.idUser)
  def departFk = foreignKey("FK_APPEL_DEPART", depart, etages)(_.idEtage)
  def destinationFk =
    foreignKey("FK_APPEL_DESTINATION", destination, etages)(_.idEtage)
  def simulationFk =
    foreignKey("FK_APPEL_SIMULATION", idSimulation, simulations)(
      _.idSimulation.?
    )
}

// L'objet compagnon doit être DÉFINI EN DEHORS de la classe AppelTable
object AppelCompagnon {
  val appels = TableQuery[AppelTable]
}
