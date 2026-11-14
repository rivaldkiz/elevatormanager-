package models
import models.Enums

import java.sql.Date
import java.time.LocalDateTime

// model de table
case class Utilisateur(
    idUser: Option[Long],
    idRole: Long,
    idSimulation: Option[Long],
    nom: String,
    email: String,
    motDePasse: String,
    telephone: Option[String],
    statut: String = Enums.StatutUtilisateur.Actif,
    dateCreation: LocalDateTime = LocalDateTime.now()
)
