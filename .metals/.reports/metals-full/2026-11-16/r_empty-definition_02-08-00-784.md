error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/Utilisateur.scala:scala/Predef.String#
file:///C:/Users/franc/Desktop/elevatormanager/app/models/Utilisateur.scala
empty definition using pc, found symbol in pc: scala/Predef.String#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -String#
	 -scala/Predef.String#
offset: 328
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/Utilisateur.scala
text:
```scala
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
    statut: Str@@ing = Enums.StatutUtilisateur.Actif,
    dateCreation: LocalDateTime = LocalDateTime.now()
)

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Predef.String#