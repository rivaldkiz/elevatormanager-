package models

import models.Enums

import java.time.LocalDateTime
case class Panne(
    idPanne: Option[Long],
    idAscenseur: Long,
    idUser: Long,
    description: Option[String],
    categorie: String = Enums.CategoriePanne.Autre,
    statut: String = Enums.StatutPanne.Signalee,
    datePanne: LocalDateTime = LocalDateTime.now()
)
