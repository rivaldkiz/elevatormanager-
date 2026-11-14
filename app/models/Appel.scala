package models

import models.Enums

import java.time.LocalDateTime

case class Appel(
    idAppel: Option[Long],
    idUser: Long,
    depart: Long,
    destination: Long,
    idSimulation: Option[Long],
    direction: String,
    statut: String = Enums.StatutAppel.EnAttente,
    avecBagages: Boolean = false,
    dateAppel: LocalDateTime = LocalDateTime.now(),
    scoreAttribution: Option[BigDecimal] = None
)
