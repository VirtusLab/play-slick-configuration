package org.virtuslab.config.util

import org.virtuslab.config.{ ConfigurationEntriesCake, ConfigurationParamCake, ConfigurationRepositoryCake, ConfigurationSerializersCake }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

object PlaySlickConfigurationDbDriver extends PlaySlickConfigurationDbDriver {
  override lazy val profile: JdbcProfile = DatabaseConfigProvider.get(play.api.Play.routesCompilerMaybeApplication.get).profile
}

trait PlaySlickConfigurationDbDriver extends HasJdbcDriver
  with ConfigurationRepositoryCake
  with ConfigurationSerializersCake
  with ConfigurationParamCake
  with ConfigurationEntriesCake {
  val profile: JdbcProfile
}
