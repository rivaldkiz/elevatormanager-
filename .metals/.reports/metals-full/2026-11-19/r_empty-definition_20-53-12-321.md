error id: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala:ControllerComponents#
file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject/ControllerComponents#
	 -play/api/ControllerComponents#
	 -play/api/mvc/ControllerComponents#
	 -ControllerComponents#
	 -scala/Predef.ControllerComponents#
offset: 347
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala
text:
```scala
package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import services.AuthService

/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class UtilisateurControllerController @Inject(
  authService AuthService
) (val controllerComponents: Co@@ntrollerComponents)
    extends BaseController {

  def dashboard = Action.async { implicit request =>
    request.session.get("userId") match {

      case None =>
        Future.successful(Redirect("/login"))

      case Some(idUser) =>

        authService.findById(idUser.toLong).flatMap {

          case Some(utilisateur) =>

            authService.findRoleById(utilisateur.idRole).map {

              case Some("ADMIN") =>
                Ok(
                  views.html.dashboard.admin(
                    utilisateur = utilisateur
                  )
                )

              case Some("TECHNICIEN") =>
                Ok(
                  views.html.dashboard.technicien(
                    utilisateur = utilisateur
                  )
                )

              case Some("RESIDENT") =>
                Ok(
                  views.html.dashboard.resident(
                    utilisateur = utilisateur
                  )
                )

              case _ =>
                Redirect("/login").withNewSession
            }

          case None =>
            Future.successful(Redirect("/login").withNewSession)
        }
    }
  }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 