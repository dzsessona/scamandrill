package com.joypeg.scamandrill.models

import akka.actor.ActorSystem
import com.joypeg.scamandrill.utils.SimpleLogger
import com.typesafe.config.{ConfigFactory, ConfigValueFactory}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class DefaultConfigTest extends FlatSpec with Matchers with SimpleLogger {

  "DefaultConfig" should "read the defaut key from the configuration" in {
    DefaultConfig.defaultKeyFromConfig.getClass shouldBe classOf[String]
  }

  it should "read the defaut timeout duration from the configuration" in {
    DefaultConfig.defaultTimeout.getClass shouldBe classOf[FiniteDuration]
    DefaultConfig.defaultTimeout shouldBe 10.seconds
  }

  it should "use defined dispatcher if configuration defined" in {
    val config =
      ConfigFactory.load()
      .withFallback(ConfigFactory.parseString(
        """
          |Mandrill.defaultDispatcher {
          |  type = Dispatcher
          |  executor = "fork-join-executor"
          |  fork-join-executor {
          |    parallelism-min = 1
          |    parallelism-max = 1
          |  }
          |}
        """.stripMargin))
    config.hasPath("Mandrill.defaultDispatcher") should equal(true)
    val system = ActorSystem("test", config)

    DefaultConfig.defaultExecutionContext(system) shouldNot equal(system.dispatcher)
    Await.result(system.terminate(), 10 seconds)
  }

  it should "use system.dispatcher if configuration not defined" in {
    val config =
      ConfigFactory.load()

    config.hasPath("Mandrill.defaultDispatcher") should equal(false)
    val system = ActorSystem("test", config)

    DefaultConfig.defaultExecutionContext(system) should equal(system.dispatcher)
    Await.result(system.terminate(), 10 seconds)
  }
}
