package controllers

import javax.inject._
import play.api.mvc._
import services.AuthService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UtilisateurController @Inject() (
    cc: ControllerComponents,
    authService: AuthService
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  // Tes autres fonctions

  def dashbord = Action.async { implicit request =>
    request.session.get("userId") match {

      case None =>
        Future.successful(Redirect("/login"))

      case Some(idUser) =>

        authService.findById(idUser.toLong).flatMap {

          case Some(utilisateur) =>

            authService.findRoleById(utilisateur.idRole).map {

              case Some("ADMIN") =>
                Ok(
                  views.html.dashbord.admin(
                    user = utilisateur
                  )
                )

              case Some("TECHNICIEN") =>
                Ok(
                  views.html.dashbord.technicien(
                    user = utilisateur
                  )
                )

              case Some("RESIDENT") =>
                Ok(
                  views.html.dashbord.resident(
                    user = utilisateur
                  )
                )

              case _ =>
                Redirect("/login").withNewSession.flashing(
                  "error" -> "impossible de vous connecter!"
                )
            }

          case None =>
            Future.successful(
              Redirect("/login").withNewSession
                .flashing("error" -> "impossible de vous connecter!")
            )
        }
    }
  }
}
