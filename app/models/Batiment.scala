package models

import models.Enums

case class Batiment(
    idBatiment: Option[Long],
    nom: String,
    adresse: Option[String],
    description: Option[String],
    statut: String = Enums.StatutBatiment.Actif
)
