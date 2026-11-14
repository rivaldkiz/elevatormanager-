error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/AscenseurTable.scala:simulations.
file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/AscenseurTable.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -slick/jdbc/OracleProfile.api.simulations.
	 -slick/jdbc/OracleProfile.api.simulations#
	 -slick/jdbc/OracleProfile.api.simulations().
	 -tables/SimulationCompagnon.simulations.
	 -tables/SimulationCompagnon.simulations#
	 -tables/SimulationCompagnon.simulations().
	 -simulations.
	 -simulations#
	 -simulations().
	 -scala/Predef.simulations.
	 -scala/Predef.simulations#
	 -scala/Predef.simulations().
offset: 443
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/AscenseurTable.scala
text:
```scala
package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Ascenseurs
import models.Enums
import tables.UtilisateurCompagnon.utilisateurs
import tables.EtageCompagnon.etages
import tables.SimulationCompagnon.simulati@@ons

class AscenseurTable(tag: Tag) extends Table[Ascenseur](tag, "ASCENSEUR") {
  def idAscenseur = column[Long]("ID_ASCENSEUR", O.PrimaryKey, O.AutoInc)
  def idBatiment = column[Long]("ID_BATIMENT")
  def idSimulation = column[Option[Long]]("ID_SIMULATION") // nullable
  def nom = column[String]("NOM", O.Length(100))
  def capacitePersonnes = column[Int]("CAPACITE_PERSONNES")
  def chargeMax = column[BigDecimal]("CHARGE_MAX")
  def position = column[Int]("POSITION", O.Default(0))
  def direction = column[String](
    "DIRECTION",
    O.Length(10),
    O.Default(Enums.DirectionAscenseur.Arret)
  )
  def etat =
    column[String]("ETAT", O.Length(15), O.Default(Enums.EtatAscenseur.Arret))
  def isVirtual = column[Boolean]("IS_VIRTUAL", O.Default(false))
  def * = (
    idAscenseur.?,
    idBatiment,
    idSimulation,
    nom,
    capacitePersonnes,
    chargeMax,
    position,
    direction,
    etat,
    isVirtual
  ) <> ((Ascenseur.apply _).tupled, Ascenseur.unapply)

  def batimentFk =
    foreignKey("FK_ASCENSEUR_BATIMENT", idBatiment, batiments)(_.idBatiment)
  def simulationFk =
    foreignKey("FK_ASCENSEUR_SIMULATION", idSimulation, simulations)(
      _.idSimulation.?
    )

}
object AscenseurCompagnon {
  val ascenseurs = TableQuery[AscenseurTable]

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 