package org.virtuslab.config

import play.api.db.slick.Config.driver.simple._

/**
 * Class for easy configuration management.
 *
 * @param name name of property
 */
case class ConfigurationParam[A: ConfigurationSerializer](name: String) {

  private val serializer = implicitly[ConfigurationSerializer[A]]

  private val repo = new ConfigurationRepository

  /**
   * @param session implicit session
   * @return option with value for this key
   */
  def value()(implicit session: Session): Option[A] = repo.byKey(name).map(serializer.read)

  /**
   * @param session implicit session
   * @return value for this key
   * @throws ConfigurationNotFound if no configuration entry was found
   */
  def get()(implicit session: Session): A = value().getOrElse(
    throw new NoSuchElementException(s"Configuration value not found for key: $name"))

  /**
   * Saves this key with given value
   *
   * @param value value to save for this key
   * @param session implicit session
   */
  def saveValue(value: A)(implicit session: Session): Unit = {
    repo.saveOrUpdate(ConfigurationEntry(name, serializer.write(value)))
  }

  /** Removes element from configuration. */
  def remove()(implicit session: Session): Unit = {
    repo.remove(name)
  }
}