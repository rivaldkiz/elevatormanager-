package models

import models.Enums

import java.time.LocalDateTime

case class Intervention(
    idIntervention: Option[Long],
    idPanne: Long,
    idTechnicien: Long,
    description: Option[String],
    `type`: String = Enums.TypeIntervention.Corrective,
    statut: String = Enums.StatutIntervention.Planifiee,
    dateDebut: LocalDateTime = LocalDateTime.now(),
    dateFin: Option[LocalDateTime] = None
)
