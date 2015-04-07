Configuration table for Slick and Play
======================================

[![Build Status](https://travis-ci.org/VirtusLab/play-slick-configuration.svg?branch=master)](https://travis-ci.org/VirtusLab/play-slick-configuration)

It's a small helper library based on Slick and play-slick plugin from Play! framework. It provides nice type-safe way to serialize properties of custom types to database and then read them back.

Contributors
------------
Authors:
* [Jerzy Müller](https://github.com/Kwestor)
* [Krzysztof Romanowski](https://github.com/romanowski)

Feel free to use it, test it and to contribute!

Getting play-slick-configuration
--------------------------------

Library is available in sonatype and Maven central since version 1.1.0.

```scala
libraryDependencies += "org.virtuslab" %% "play-slick-configuration" % "1.2.0"
```

This branch is built against Slick 2.1.x, for Slick 1.x use [slick-1.0](https://github.com/Kwestor/play-slick-configuration/tree/slick-1.0) branch.

Examples
========

Setup
-----

To use this library you need to create a `configuration` table in database with a `key` and `value` text fields.

Easiest way to do this is just by running:

```scala
import org.virtuslab.config.ConfigurationEntries
import play.api.db.slick.Config.driver.simple._

ConfigurationEntries.ddl.create
```

Or adding following SQL (it's from PostgreSQL, syntax may vary) to your setup or evolution scripts:

```sql
CREATE TABLE configuration
(
  key character varying(254) NOT NULL,
  value character varying(254) NOT NULL,
  CONSTRAINT configuration_pkey PRIMARY KEY (key)
);
```

Usage
-----

`TODO`

Extensions
----------

Serialization and de-serialization of properties are based on a type-class called `ConfigurationSerializer`:

```scala
trait ConfigurationSerializer[A] {
  def write(a: A): String

  def read(s: String): A
}
```

This library comes with instances of this type-class for several types, but you can easily create your own instances of it by placing an `implicit val` (or `def` or `object`) in scope.