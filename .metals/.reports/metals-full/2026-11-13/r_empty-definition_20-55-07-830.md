error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/Ascenseur.scala:scala/Boolean#
file:///C:/Users/franc/Desktop/elevatormanager/app/models/Ascenseur.scala
empty definition using pc, found symbol in pc: scala/Boolean#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Boolean#
	 -scala/Predef.Boolean#
offset: 274
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/Ascenseur.scala
text:
```scala
package models

// Table Ascenseur
case class Ascenseur(
    id: Int,
    id_batiment: Int,
    nom: String,
    capacite: Int,
    charge_max: Int,
    position: String,
    direction: String,
    etat: String,
    is_virtuel: Boolean,
    id_simulation: Boole@@an
)

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Boolean#