package models

import java.time.LocalDateTime

case class Historique(
    idHistorique: Option[Long],
    idUser: Long,
    typeAction: String,
    description: Option[String],
    dateAction: LocalDateTime = LocalDateTime.now()
)
