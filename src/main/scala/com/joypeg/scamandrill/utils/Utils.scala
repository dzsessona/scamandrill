package com.joypeg.scamandrill.utils

import org.slf4j.LoggerFactory
import com.typesafe.config.ConfigFactory
import spray.httpx.UnsuccessfulResponseException
import com.joypeg.scamandrill.client.MandrillResponseException

object `package` {
  lazy val config = {
    ConfigFactory.load("application.conf")
  }
}

trait SimpleLogger{
  val logger = LoggerFactory.getLogger(getClass)
}
