package com.joypeg.scamandrill.models

import scala.concurrent.duration._
import com.joypeg.scamandrill.utils._

object DefaultConfig{

  lazy val defaultKeyFromConfig: String = config.getString("Mandrill.key")
  lazy val defaultTimeout: FiniteDuration = config.getInt("Mandrill.timoutInSeconds").seconds

}
