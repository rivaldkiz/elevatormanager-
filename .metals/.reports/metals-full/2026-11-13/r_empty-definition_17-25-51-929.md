error id: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UserController.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UserController.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -javax/inject/views/index.
	 -javax/inject/views/index#
	 -javax/inject/views/index().
	 -play/api/mvc/views/index.
	 -play/api/mvc/views/index#
	 -play/api/mvc/views/index().
	 -views/index.
	 -views/index#
	 -views/index().
	 -scala/Predef.views.index.
	 -scala/Predef.views.index#
	 -scala/Predef.views.index().
offset: 303
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/controllers/UserController.scala
text:
```scala
package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class UserController @Inject() (
    val controllerComponents: ControllerComponents
) extends BaseController {

  def index() = Action { implicit request =>
    // Ok("Bienvenu sur mon application")

    Ok(views.@@html.index(nom: "Bonjour"))
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 