package controllers

import javax.inject._
import play.api.mvc._
import play.api.mvc.Flash
import java.lang.ProcessBuilder.Redirect

@Singleton
class UserController @Inject() (
    val controllerComponents: ControllerComponents
) extends BaseController {

  def index() = Action { implicit request =>
    // Ok("Bienvenu sur mon application")

    Ok(views.html.index("Bernard"))

    // /ascenseurs?ville=Yaounde

    // val ville = request.getQueryString("ville") Retourne un Option[String]
    // Ok(s"Ville : ${ville.getOrElse("Inconnu")}")
  }

// pour recuperer les parametres d'un formulaire
  def create() = Action { implicit request =>
    // val nom =
    //   request.body.asFormUrlEncoded.flatMap(_.get("nom").flatMap(_.headOption))

    // Ok(s"Nom : ${nom.getOrElse("Nom renseigne")}")

    // request.method, request.headers, request.cookies, request.session, request.path

    Redirect(routes.UserController.index())
      .flashing("success" -> "Ascenseur cree avec")
  }

  // def update() = Action.async {

  //   Future {
  //     Ok("Reponse asynchrone")
  //   }

  // }

// pour definir le type de l action
  def delete(): Action[AnyContent] = Action {
    Ok("Bienvenue")
  }
}
