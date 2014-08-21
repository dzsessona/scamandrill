package com.joypeg.scamandrill.models

import org.scalatest.{Matchers, FlatSpec}
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.concurrent.duration._

class DefaultConfigTest extends FlatSpec with Matchers with SimpleLogger {

  "DefaultConfig" should "read the defaut key from the configuration" in {
    DefaultConfig.defaultKeyFromConfig.getClass shouldBe classOf[String]
  }

  it should "read the defaut timeout duration from the configuration" in {
    DefaultConfig.defaultTimeout.getClass shouldBe classOf[FiniteDuration]
    DefaultConfig.defaultTimeout shouldBe 10.seconds
  }
}
