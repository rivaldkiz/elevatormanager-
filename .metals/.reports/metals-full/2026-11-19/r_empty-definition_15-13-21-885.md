error id: file:///C:/Users/franc/Desktop/elevatormanager/app/services/AuthService.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/services/AuthService.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject/utilisateur.
	 -utilisateur.
	 -scala/Predef.utilisateur.
offset: 3484
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/services/AuthService.scala
text:
```scala
package services

import javax.inject._
import repositories.UtilisateurRepository
import models.Utilisateur
import models.Enums

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthService @Inject() (
    utilisateurRepository: UtilisateurRepository
)(implicit ec: ExecutionContext) {

  // =====================================================
  // 1. RECHERCHER UN UTILISATEUR PAR EMAIL
  // =====================================================

  def findByEmail(email: String): Future[Option[Utilisateur]] = {
    utilisateurRepository.findByEmail(email.trim.toLowerCase)
  }

  // =====================================================
  // 2. RECHERCHER UN UTILISATEUR PAR ID
  // =====================================================

  def findById(id: Long): Future[Option[Utilisateur]] = {
    utilisateurRepository.findById(id)
  }

  // =====================================================
  // 3. INSCRIPTION
  // =====================================================

  def inscrire(
      nom: String,
      email: String,
      motDePasse: String,
      telephone: Option[String]
  ): Future[Either[String, Int]] = {

    val nomNettoye = nom.trim
    val emailNettoye = email.trim.toLowerCase
    val telephoneNettoye = telephone.map(_.trim).filter(_.nonEmpty)

    if (nomNettoye.isEmpty) {
      Future.successful(Left("Le nom est obligatoire."))

    } else if (emailNettoye.isEmpty) {
      Future.successful(Left("L'adresse email est obligatoire."))

    } else if (!emailNettoye.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      Future.successful(Left("L'adresse email est invalide."))

    } else if (motDePasse == null || motDePasse.length < 8) {
      Future.successful(
        Left("Le mot de passe doit contenir au moins 8 caractères.")
      )

    } else {

      utilisateurRepository.emailExiste(emailNettoye).flatMap {

        case true =>
          Future.successful(
            Left("Cette adresse email est déjà utilisée.")
          )

        case false =>
          utilisateurRepository.findIdRoleByCode("RESIDENT").flatMap {

            case None =>
              Future.successful(
                Left("Le rôle RESIDENT est introuvable.")
              )

            case Some(idRole) =>

              val utilisateur = Utilisateur(
                idUser = None,
                idRole = idRole,
                idSimulation = None,
                nom = nomNettoye,
                email = emailNettoye,
                motDePasse = motDePasse,
                telephone = telephoneNettoye,
                statut = Enums.StatutUtilisateur.Actif
              )

              utilisateurRepository.create(utilisateur).map {

                case 1 =>
                  Right(1)

                case _ =>
                  Left("L'inscription n'a pas pu être effectuée.")
              }
          }
      }
    }
  }

  // =====================================================
  // 4. AUTHENTIFICATION
  // =====================================================

  def authentifier(
      email: String,
      motDePasse: String
  ): Future[Option[Utilisateur]] = {

    utilisateurRepository
      .findByEmail(
        email.trim.toLowerCase
      )
      .map {

        case Some(utilisateur)
            if utilisateur.statut == Enums.StatutUtilisateur.Actif &&
              utilis@@ateur.motDePasse == motDePasse =>
          Some(utilisateur)

        case _ =>
          None
      }
  }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 