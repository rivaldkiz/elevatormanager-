package models

import models.Enums
import java.time.LocalDateTime

case class EvaluationRepartition(
    idEvaluation: Option[Long],
    idAppel: Long,
    idAscenseur: Long,
    scoreTotal: BigDecimal = 0,
    scoreDistance: BigDecimal = 0,
    penaliteDirection: BigDecimal = 0,
    penaliteCharge: BigDecimal = 0,
    penaliteArrets: BigDecimal = 0,
    retenu: Boolean = false,
    dateEvaluation: LocalDateTime = LocalDateTime.now()
)
