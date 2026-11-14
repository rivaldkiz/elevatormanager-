package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.EvaluationRepartition
import models.Enums
import tables.AscenseurCompagnon.ascenseurs
import tables.EtageCompagnon.etages
import tables.AppelCompagnon.appels

class EvaluationRepartitionTable(tag: Tag)
    extends Table[EvaluationRepartition](tag, "EVALUATION_REPARTITION") {
  def idEvaluation = column[Long]("ID_EVALUATION", O.PrimaryKey, O.AutoInc)
  def idAppel = column[Long]("ID_APPEL")
  def idAscenseur = column[Long]("ID_ASCENSEUR")
  def scoreTotal = column[BigDecimal]("SCORE_TOTAL", O.Default(BigDecimal(0)))
  def scoreDistance =
    column[BigDecimal]("SCORE_DISTANCE", O.Default(BigDecimal(0)))
  def penaliteDirection =
    column[BigDecimal]("PENALITE_DIRECTION", O.Default(BigDecimal(0)))
  def penaliteCharge =
    column[BigDecimal]("PENALITE_CHARGE", O.Default(BigDecimal(0)))
  def penaliteArrets =
    column[BigDecimal]("PENALITE_ARRETS", O.Default(BigDecimal(0)))
  def retenu = column[Boolean]("RETENU", O.Default(false))
  def dateEvaluation = column[LocalDateTime]("DATE_EVALUATION")
  def * = (
    idEvaluation.?,
    idAppel,
    idAscenseur,
    scoreTotal,
    scoreDistance,
    penaliteDirection,
    penaliteCharge,
    penaliteArrets,
    retenu,
    dateEvaluation
  ) <>
    ((EvaluationRepartition.apply _).tupled, EvaluationRepartition.unapply)

  def appelFk = foreignKey("FK_EVAL_APPEL", idAppel, appels)(
    _.idAppel,
    onDelete = ForeignKeyAction.Cascade
  )
  def ascenseurFk =
    foreignKey("FK_EVAL_ASCENSEUR", idAscenseur, ascenseurs)(_.idAscenseur)
  def uniqueEval =
    index("UQ_EVAL_APPEL_ASCENSEUR", (idAppel, idAscenseur), unique = true)

}

object EvaluationRepartitionCompagnon {
  val evaluations = TableQuery[EvaluationRepartitionTable]
}
