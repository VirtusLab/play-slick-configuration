package org.virtuslab.config

import java.util.concurrent.TimeUnit
import org.joda.time.DateTime
import org.joda.time.Duration
import scala.concurrent.duration.FiniteDuration

class ConfigurationServiceTest extends AppTest {

  behavior of "Configuration API"

  it should "manage configuration correctly via Repository" in rollback { implicit session =>
    val service = new ConfigurationRepository

    val k1 = "k1"
    val k2 = "k2"

    val v1 = "v1"
    val v2 = "v2"
    val v3 = "v3"
    val conf1 = ConfigurationEntry(k1, v1)
    val conf2 = ConfigurationEntry(k2, v2)
    val conf3 = ConfigurationEntry(k1, v3)

    service.saveOrUpdate(conf1)
    service.saveOrUpdate(conf2)

    //when query for conf
    val Some(testConf1) = service.byKey(k1)
    //then valid conf is returned
    testConf1 shouldEqual v1

    //when update conf
    service.saveOrUpdate(conf1.copy(value = v3))

    //then conf should be updated
    service.byKey(k1) shouldEqual Some(v3)
  }

  it should "work for Int keys" in rollback { implicit session =>
    // SETUP define int key
    val ParamInt = ConfigurationParam[Int]("intKey")

    // WHEN save it
    ParamInt.saveValue(1)

    // THEN it should be set
    ParamInt.get() shouldEqual 1
  }

  it should "work for String keys" in rollback { implicit session =>
    // SETUP define String key
    val ParamString = ConfigurationParam[String]("stringKey")

    // WHEN save it
    ParamString.saveValue("ala")

    // THEN it should be set
    ParamString.get() shouldEqual "ala"
  }

  it should "work for DateTime keys" in rollback { implicit session =>
    // SETUP define DateTime key
    val ParamDateTime = ConfigurationParam[DateTime]("dateTimeKey")

    // WHEN save it
    val dateTime = DateTime.now()
    ParamDateTime.saveValue(dateTime)

    // THEN it should be set
    ParamDateTime.get() shouldEqual dateTime
  }

  it should "work for FiniteDuration keys" in rollback { implicit session =>
    // SETUP define FiniteDuration key
    val ParamDuration = ConfigurationParam[FiniteDuration]("finiteDurationKey")

    // WHEN save it
    val duration = FiniteDuration(12, TimeUnit.DAYS)
    ParamDuration.saveValue(duration)

    // THEN it should be set
    ParamDuration.get() shouldEqual duration
  }

  it should "work for Duration keys" in rollback { implicit session =>
    // SETUP define FiniteDuration key
    val ParamDuration = ConfigurationParam[Duration]("finiteDurationKey")

    // WHEN save it
    val duration = Duration.standardDays(12)
    ParamDuration.saveValue(duration)

    // THEN it should be set
    ParamDuration.get() shouldEqual duration
  }

  it should "work for Boolean keys" in rollback { implicit session =>
  // SETUP define FiniteDuration key
    val booleanParam = ConfigurationParam[Boolean]("booleanKey")

    // WHEN save it
    val boolean = true
    booleanParam.saveValue(boolean)

    // THEN it should be set
    booleanParam.get() shouldEqual boolean
  }

  it should "update value by key" in rollback { implicit session =>
    // SETUP define key
    val ParamInt = ConfigurationParam[Int]("intKey")
    // WHEN save it
    ParamInt.saveValue(1)
    // THEN it should be set
    ParamInt.get() shouldEqual 1
    // WHEN override itREADME.md
    ParamInt.saveValue(2)
    // THEN value changes
    ParamInt.get() shouldEqual 2
  }

  it should "be empty when param is not set" in rollback { implicit session =>
    // WHEN define some keys
    val ParamInt = ConfigurationParam[Int]("intKey")

    // THEN check them
    ParamInt.value() shouldEqual None
    intercept[NoSuchElementException] {
      ParamInt.get()
    }.getMessage shouldEqual "Configuration value not found for key: intKey"
  }
}
