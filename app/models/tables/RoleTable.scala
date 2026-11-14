package tables

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.OracleProfile.api._

import models.Role
import models.Enums

class RoleTable(tag: Tag) extends Table[Role](tag, "ROLE") {
  def idRole = column[Long]("ID_ROLE", O.PrimaryKey, O.AutoInc)
  def nomRole = column[String]("NOM_ROLE", O.Length(20))
  def description = column[Option[String]]("DESCRIPTION", O.Length(255))
  def * =
    (idRole.?, nomRole, description) <> ((Role.apply _).tupled, Role.unapply)
  def nomUnique = index("UQ_ROLE_NOM", nomRole, unique = true)

}

object RoleCompagnon {
  val roles = TableQuery[RoleTable]

}
