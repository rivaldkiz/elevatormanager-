package models
import models.Enums

import java.time.LocalDateTime

case class Simulation(
    idSimulation: Option[Long],
    statut: String = Enums.StatutSimulation.Active,
    dateDebut: LocalDateTime = LocalDateTime.now(),
    dateFin: Option[LocalDateTime] = None
)
