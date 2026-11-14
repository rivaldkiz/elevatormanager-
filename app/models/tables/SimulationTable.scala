package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Simulation
import models.Enums

class SimulationTable(tag: Tag) extends Table[Simulation](tag, "SIMULATION") {
  def idSimulation = column[Long]("ID_SIMULATION", O.PrimaryKey, O.AutoInc)
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutSimulation.Active)
  )
  def dateDebut = column[LocalDateTime]("DATE_DEBUT")
  def dateFin = column[Option[LocalDateTime]]("DATE_FIN")
  def * = (idSimulation.?, statut, dateDebut, dateFin) <> (
    (Simulation.apply _).tupled,
    Simulation.unapply
  )

}

object SimulationCompagnon {
  val simulations = TableQuery[SimulationTable]
}
