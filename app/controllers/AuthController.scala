package controllers

import javax.inject._
import play.api._
import play.api.mvc.Flash
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import services.AuthService
import scala.concurrent.ExecutionContext

// import services.AuthService

/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

@Singleton
class AuthController @Inject() (
    val controllerComponents: ControllerComponents,
    authService: AuthService
)(implicit ec: ExecutionContext)
    extends BaseController {

  def to_register() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.auth.register())
  }

  def to_login() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.auth.login())
  }

  // INSCRIPTION
  def register = Action.async(parse.formUrlEncoded) { implicit request =>
    val nom = request.body.get("nom").flatMap(_.headOption).getOrElse("")
    val email = request.body.get("email").flatMap(_.headOption).getOrElse("")
    val telephone =
      request.body.get("telephone").flatMap(_.headOption).getOrElse("")
    val motDePasse =
      request.body.get("motDePasse").flatMap(_.headOption).getOrElse("")
    val confirmation = request.body
      .get("confirmationMotDePasse")
      .flatMap(_.headOption)
      .getOrElse("")

    // Vérifications des champs
    val nomError =
      if (nom.trim.isEmpty) Some("Le nom est obligatoire.") else None

    val emailError =
      if (email.trim.isEmpty) Some("L'adresse email est obligatoire.") else None

    val passwordError =
      if (motDePasse.isEmpty) Some("Le mot de passe est obligatoire.")
      else if (motDePasse.length < 8) Some("Minimum 8 caractères.")
      else None

    val confirmationError =
      if (confirmation.isEmpty) Some("Confirmez votre mot de passe.")
      else if (motDePasse != confirmation)
        Some("Les mots de passe ne correspondent pas.")
      else None

    if (
      nomError.isDefined || emailError.isDefined ||
      passwordError.isDefined || confirmationError.isDefined
    ) {

      Future.successful(
        BadRequest(
          views.html.auth.register(
            nom,
            email,
            telephone,
            nomError,
            emailError,
            passwordError,
            confirmationError
          )
        )
      )

    } else {

      authService.inscrire(nom, email, motDePasse, Option(telephone)).map {

        case Right(_) =>
          Redirect("/login")
            .flashing("success" -> "Inscription réussie.")

        case Left(erreur) =>
          Redirect("/register")
            .flashing("error" -> erreur)
      }
    }
  }

  def login = Action { implicit request =>
    Ok("Bonjour")
    // val email = request.body.get("email").flatMap(_.headOption).getOrElse("")
    // val motDePasse =
    //   request.body.get("motDePasse").flatMap(_.headOption).getOrElse("")

    // authService.connexion(email, motDePasse).map {
    //   case Right(utilisateur) =>
    //     Redirect("/dashboard")
    //       .withSession("userId" -> utilisateur.idUser.get.toString)
    //       .flashing("success" -> "Connexion réussie.")

    //   case Left(erreur) =>
    //     Redirect("/login")
    //       .flashing("error" -> erreur)
    // }
  }
}
