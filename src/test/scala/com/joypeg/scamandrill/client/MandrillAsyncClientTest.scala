package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import scala.concurrent.Await
import scala.concurrent.duration._
import com.joypeg.scamandrill.models._


class MandrillAsyncClientTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  val timeout = 6.seconds
  import com.joypeg.scamandrill.MandrillTestUtils._

  "Send" should "work getting a valid List[MSendResponse]" in {
    val msg = MSendMessage(message = validMessage)
    val res1 = Await.result(MandrillAsyncClient.send(msg), timeout)
     println("res1: " + res1)
  }

  it should "work when telling that the operation is async" in {
    val msg = MSendMessage(message = validMessage, async = true)
    val res1 = Await.result(MandrillAsyncClient.send(msg), timeout)
    println("res1: " + res1)
  }

//  "Info" should "work getting a valid MInfoResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.info(), timeout)
//    println("res1: " + res1)
//  }
//
//  "Ping" should "work getting a valid MPingResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.ping(), timeout)
//    res1 shouldBe MPingResponse("\"PONG!\"")
//  }
//
//  "Ping (version 2)" should "work getting a valid MPingResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.ping2(), timeout)
//    res1 shouldBe MPingResponse("PONG!")
//  }
//
//  "Sender" should "work getting a valid List[MSenderDataResponse]" in {
//    val res1 = Await.result(MandrillAsyncClient.senders(), timeout)
//    println("res1: " + res1)
//  }



  override def afterAll() {
    MandrillAsyncClient.shutdownSystem()
  }
}
