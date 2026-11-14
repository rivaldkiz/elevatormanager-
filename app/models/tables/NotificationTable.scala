package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Notification
import tables.UtilisateurCompagnon.utilisateurs
import models.Enums

class NotificationTable(tag: Tag)
    extends Table[Notification](tag, "NOTIFICATION") {
  def idNotification = column[Long]("ID_NOTIFICATION", O.PrimaryKey, O.AutoInc)
  def idUser = column[Long]("ID_USER")
  def titre = column[String]("TITRE", O.Length(150))
  def message = column[Option[String]]("MESSAGE", O.Length(1000))
  def typ = column[String](
    "TYPE",
    O.Length(12),
    O.Default(Enums.TypeNotification.Systeme)
  )
  def lu = column[Boolean]("LU", O.Default(false))
  def dateCreation = column[LocalDateTime]("DATE_CREATION")
  def * = (idNotification.?, idUser, titre, message, typ, lu, dateCreation) <>
    ((Notification.apply _).tupled, Notification.unapply)

  def userFk = foreignKey("FK_NOTIFICATION_USER", idUser, utilisateurs)(
    _.idUser,
    onDelete = ForeignKeyAction.Cascade
  )

}

object NotificationCompagnon {
  val notifications = TableQuery[NotificationTable]

}
