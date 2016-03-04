package com.joypeg.scamandrill.utils

import org.slf4j.LoggerFactory
import com.typesafe.config.ConfigFactory

object `package` {
  lazy val config = {
    ConfigFactory.load()
  }
}

trait SimpleLogger{
  val logger = LoggerFactory.getLogger(getClass)
}
