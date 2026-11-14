error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/UserTable.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/models/UserTable.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -slick/jdbc/OracleProfile.api.slick.
	 -slick.
	 -scala/Predef.slick.
offset: 25
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/UserTable.scala
text:
```scala
package models

import @@slick.jdbc.OracleProfile.api._

//creation de la table

//tag: Tag (identifiant interne du mapping slick)
class UserTable(tag: Tag) extends Table[User](tag, "UTILISATEUR") {

//Declaration des colonnes de la table

  def id = column[Int](
    "ID",
    // definir la cle primaire
    O.PrimaryKey,
    O.AutoInc
  )

  def nom = column[String]("NOM")
  def email = column[String]("EMAIL", O.Unique)
  def telephone = column[Option[String]]("TELEPHONE", O.Unique, O.Default(None))

// indique à Slick comment convertir les colonnes sélectionnées en objet Scala et inversement.
  def * = (
    id,
    nom,
    email,
    telephone
  ) <> (User.tupled, User.unapply)
}

// les types de données utilisés dans la table sont les suivants : Int, Long, String, Option[type], BigDecimal, java.sql.Date, java.sql.Timestamp, Array[Byte].
// 1. Index unique sur une seule colonne avec un nom personnalisé
//   def idxEmail = index("uk_utilisateur_email", email, unique = true)

// 2. Exemple de contrainte unique composite (le couple (societeId, nom) doit être unique)
//   def idxSocieteNom = index("uk_societe_nom", (societeId, nom), unique = true)

```


#### Short summary: 

empty definition using pc, found symbol in pc: 