package org.virtuslab.config

import play.api.db.slick.Config.driver.simple._

/**
 * Some base queries for configuration.
 */
private[config] trait ConfigurationQueries {

  protected val configurationEntries: TableQuery[ConfigurationEntries] = TableQuery[ConfigurationEntries]

  protected def byKeyQuery(key: String) = for {
    conf <- configurationEntries
    if conf.key === key
  } yield conf.value

  protected def updateQuery(key: String) = for {
    conf <- configurationEntries if conf.key === key
  } yield conf

}

/**
 * Repository for configuration.
 */
private[config] class ConfigurationRepository extends ConfigurationQueries {

  /**
   * @return Some(param_value) or None if key is undefined
   */
  def byKey(key: String)(implicit session: Session): Option[String] = byKeyQuery(key).firstOption

  /**
   * Update if it exist or create new one otherwise.
   * @param entry to save
   */
  def saveOrUpdate(entry: ConfigurationEntry)(implicit session: Session) {
    if (updateQuery(entry.key).update(entry) < 1) configurationEntries.insert(entry)
  }

  /**
   * Removes element from configuration.
   * @return number of deleted elements
   */
  def remove(key: String)(implicit session: Session): Int = {
    updateQuery(key).delete
  }
}


