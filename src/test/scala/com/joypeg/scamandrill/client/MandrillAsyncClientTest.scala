package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import scala.concurrent.Await
import scala.concurrent.duration._
import com.joypeg.scamandrill.models._


class MandrillAsyncClientTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  val timeout = 6.seconds

  "Ping" should "work getting a valid MPingResponse" in {
    val res1 = Await.result(MandrillAsyncClient.ping(), timeout)
    res1 shouldBe MPingResponse("\"PONG!\"")
  }

  "Ping (version 2)" should "work getting a valid MPingResponse" in {
    val res1 = Await.result(MandrillAsyncClient.ping2(), timeout)
    res1 shouldBe MPingResponse("PONG!")
  }

  override def afterAll() {
    MandrillAsyncClient.shutdownSystem()
  }
}
