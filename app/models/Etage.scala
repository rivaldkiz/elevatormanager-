package models

import models.Enums

case class Etage(
    idEtage: Option[Long],
    idBatiment: Long,
    numero: Int,
    nom: Option[String]
)
