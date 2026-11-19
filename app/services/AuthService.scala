package services

import javax.inject._
import repositories.UtilisateurRepository
import models.Utilisateur
import models.Enums

import org.mindrot.jbcrypt.BCrypt

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

    // Nettoyage des données
    val nomNettoye = Option(nom).getOrElse("").trim
    val emailNettoye = Option(email).getOrElse("").trim.toLowerCase
    val telephoneNettoye =
      telephone.map(_.trim).filter(_.nonEmpty)

    // Validation du nom
    if (nomNettoye.isEmpty) {
      Future.successful(
        Left("Le nom est obligatoire.")
      )

      // Validation de l'email
    } else if (emailNettoye.isEmpty) {
      Future.successful(
        Left("L'adresse email est obligatoire.")
      )

    } else if (
      !emailNettoye.matches(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
      )
    ) {
      Future.successful(
        Left("L'adresse email est invalide.")
      )

      // Validation du mot de passe
    } else if (
      motDePasse == null ||
      motDePasse.length < 8 ||
      motDePasse.length > 72
    ) {
      Future.successful(
        Left("Le mot de passe doit contenir entre 8 et 72 caractères.")
      )

    } else {

      // Vérification de l'existence de l'email
      utilisateurRepository.emailExiste(emailNettoye).flatMap {

        case true =>
          Future.successful(
            Left("Cette adresse email est déjà utilisée.")
          )

        case false =>

          // Récupération du rôle RESIDENT
          utilisateurRepository.findIdRoleByCode("RESIDENT").flatMap {

            case None =>
              Future.successful(
                Left("Le rôle RESIDENT est introuvable.")
              )

            case Some(idRole) =>

              // Hachage BCrypt du mot de passe
              val motDePasseHash = BCrypt.hashpw(
                motDePasse,
                BCrypt.gensalt(12)
              )

              // Création du nouvel utilisateur
              val utilisateur = Utilisateur(
                idUser = None,
                idRole = idRole,
                idSimulation = None,
                nom = nomNettoye,
                email = emailNettoye,
                motDePasse = motDePasseHash,
                telephone = telephoneNettoye,
                statut = Enums.StatutUtilisateur.Actif
              )

              // Enregistrement dans Oracle
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

    val emailNettoye =
      Option(email).getOrElse("").trim.toLowerCase

    if (emailNettoye.isEmpty || motDePasse == null || motDePasse.isEmpty) {
      Future.successful(None)

    } else {

      utilisateurRepository
        .findByEmail(emailNettoye)
        .map {

          case Some(utilisateur)
              if utilisateur.statut == Enums.StatutUtilisateur.Actif &&
                BCrypt.checkpw(
                  motDePasse,
                  utilisateur.motDePasse
                ) =>
            Some(utilisateur)

          case _ =>
            None
        }
    }
  }

  def findRoleById(idRole: Long): Future[Option[String]] = {
    utilisateurRepository.findRoleById(idRole)
  }

  // def verifierRole(idUser: Long, roleAttendu: String): Future[Boolean] = {

  //   findById(idUser).flatMap {

  //     case Some(utilisateur) =>
  //       findRoleById(utilisateur.idRole).map {

  //         case Some(role) =>
  //           role == roleAttendu

  //         case None =>
  //           false
  //       }

  //     case None =>
  //       Future.successful(false)
  //   }
  // }

}
