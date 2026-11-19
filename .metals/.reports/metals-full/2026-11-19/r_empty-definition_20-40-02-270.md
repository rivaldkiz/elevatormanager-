error id: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Action.
	 -scala/Predef.Action.
offset: 16
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UtilisateurController.scala
text:
```scala
def dashboard = @@Action.async { request =>
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
                  nom = utilisateur.nom,
                  email = utilisateur.email
                )
              )

            case Some("TECHNICIEN") =>
              Ok(
                views.html.dashboard.technicien(
                  nom = utilisateur.nom,
                  email = utilisateur.email
                )
              )

            case Some("RESIDENT") =>
              Ok(
                views.html.dashboard.resident(
                  nom = utilisateur.nom,
                  email = utilisateur.email
                )
              )

            case _ =>
              Redirect("/login").withNewSession
          }

        case None =>
          Future.successful(Redirect("/dashbord").withNewSession)
      }
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 