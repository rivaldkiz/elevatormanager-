error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/EvaluationRepartitionTable.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/models/EvaluationRepartitionTable.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -play.
	 -scala/Predef.play.
offset: 125
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/EvaluationRepartitionTable.scala
text:
```scala
package models

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import @@play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

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
val evaluations = TableQuery[EvaluationRepartitionTable]

```


#### Short summary: 

empty definition using pc, found symbol in pc: 