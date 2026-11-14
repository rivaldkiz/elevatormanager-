package models
import models.Enums

case class Ascenseur(
    idAscenseur: Option[Long],
    idBatiment: Long,
    idSimulation: Option[Long],
    nom: String,
    capacitePersonnes: Int,
    chargeMax: BigDecimal,
    position: Int = 0,
    direction: String = Enums.DirectionAscenseur.Arret,
    etat: String = Enums.EtatAscenseur.Arret,
    isVirtual: Boolean = false
)
