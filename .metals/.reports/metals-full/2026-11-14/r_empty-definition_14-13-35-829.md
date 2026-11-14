error id: file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/InterventionTable.scala:
file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/InterventionTable.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -slick/jdbc/OracleProfile.api.slick.
	 -slick.
	 -scala/Predef.slick.
offset: 203
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/models/tables/InterventionTable.scala
text:
```scala
package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import @@slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Intervention
import tables.PanneCompagnon.pannes
import tables.UtilisateurCompagnon.utilisateurs
import models.Enums

class InterventionTable(tag: Tag)
    extends Table[Intervention](tag, "INTERVENTION") {
  def idIntervention = column[Long]("ID_INTERVENTION", O.PrimaryKey, O.AutoInc)
  def idPanne = column[Long]("ID_PANNE")
  def idTechnicien = column[Long]("ID_TECHNICIEN") // -> UTILISATEUR
  def description = column[Option[String]]("DESCRIPTION", O.Length(500))
  def typ = column[String](
    "TYPE",
    O.Length(12),
    O.Default(Enums.TypeIntervention.Corrective)
  )
  def statut = column[String](
    "STATUT",
    O.Length(10),
    O.Default(Enums.StatutIntervention.Planifiee)
  )
  def dateDebut = column[LocalDateTime]("DATE_DEBUT")
  def dateFin = column[Option[LocalDateTime]]("DATE_FIN")
  def * = (
    idIntervention.?,
    idPanne,
    idTechnicien,
    description,
    typ,
    statut,
    dateDebut,
    dateFin
  ) <>
    ((Intervention.apply _).tupled, Intervention.unapply)

  def panneFk = foreignKey("FK_INTERVENTION_PANNE", idPanne, pannes)(_.idPanne)
  def technicienFk =
    foreignKey("FK_INTERVENTION_TECHNICIEN", idTechnicien, utilisateurs)(
      _.idUser
    )

}

object InterventionCompagnon {
  val interventions = TableQuery[InterventionTable]
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 