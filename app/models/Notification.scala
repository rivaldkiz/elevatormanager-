package models

import java.time.LocalDateTime

case class Notification(
    idNotification: Option[Long],
    idUser: Long,
    titre: String,
    message: Option[String],
    `type`: String = Enums.TypeNotification.Systeme,
    lu: Boolean = false,
    dateCreation: LocalDateTime = LocalDateTime.now()
)
