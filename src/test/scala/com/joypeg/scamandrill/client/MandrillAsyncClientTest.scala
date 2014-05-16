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

//    "SendRaw" should "work finding some results" in {
//      val q = MSendRaw(raw_message = """From: sender@example.com\nTo: recipient.email@example.com\nSubject: Some Subject\n\nSome content.""")
//      val res = Await.result(MandrillAsyncClient.sendRaw(q), timeout)
//      println("res1: " + res)
//    }

//  "Parse" should "getting" in {
//    //  "MessageInfo" should "work finding some results" in {
//        val raw_message = MParse(raw_message = """"From: sender@example.com\nTo: recipient.email@example.com\nSubject: Some Subject\n\nSome content."""")
//        val res = Await.result(MandrillAsyncClient.parse(raw_message), timeout)
//        println("res1: " + res)
//    //  }
//  }

//  "MessageInfo" should "work finding some results" in {
//    val q = MMessageInfo(key = "Su9Twr4SZKU6aoWRQy4DIA", id = "66fb42789e47461d80eb140862f564d5")
//    val res = Await.result(MandrillAsyncClient.messageInfo(q), timeout)
//    println("res1: " + res)
//  }

//    "Content" should "work finding some results" in {
//      val q = MMessageInfo(key = "Su9Twr4SZKU6aoWRQy4DIA", id = "66fb42789e47461d80eb140862f564d5")
//      val res = Await.result(MandrillAsyncClient.content(q), timeout)
//      println("res1: " + res)
//    }



//    "SearchTimeSeries" should "work finding some results" in {
//      val q = MSearchTimeSeries(key = "Su9Twr4SZKU6aoWRQy4DIA", query = "email:gmail.com", date_from = "2013-01-01", date_to = "2018-01-01")
//      val res = Await.result(MandrillAsyncClient.searchTimeSeries(q), timeout)
//      println("res1: " + res)
//    }

//  "Search" should "work finding some results" in {
//    val q = MSearch(key = "Su9Twr4SZKU6aoWRQy4DIA", query = "email:gmail.com", date_from = "2013-01-01", date_to = "2018-01-01")
//    val res = Await.result(MandrillAsyncClient.search(q), timeout)
//    println("res1: " + res)
//  }

//  "Send" should "work getting a valid List[MSendResponse]" in {
//    val msg = MSendMessage(message = validMessage)
//    val res1 = Await.result(MandrillAsyncClient.send(msg), timeout)
//     println("res1: " + res1.head._id)
//  }

  //this is is 66fb42789e47461d80eb140862f564d5
//  "Send real" should "work getting a valid List[MSendResponse]" in {
//    val msg = MSendMessage(key = "Su9Twr4SZKU6aoWRQy4DIA", message = diegoMessage)
//    val res1 = Await.result(MandrillAsyncClient.send(msg), timeout)
//    println("res1: " + res1.head._id)
//  }

//
//  it should "work when telling that the operation is async" in {
//    val msg = MSendMessage(message = validMessage, async = true)
//    val res1 = Await.result(MandrillAsyncClient.send(msg), timeout)
//    println("res1: " + res1)
//  }

//  "Info" should "work getting a valid MInfoResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.info(MPing()), timeout)
//    println("res1: " + res1)
//  }
//
//  "Ping" should "work getting a valid MPingResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.ping(MPing()), timeout)
//    res1 shouldBe MPingResponse("\"PONG!\"")
//  }
//
//  "Ping (version 2)" should "work getting a valid MPingResponse" in {
//    val res1 = Await.result(MandrillAsyncClient.ping2(MPing()), timeout)
//    res1 shouldBe MPingResponse("PONG!")
//  }
//
//  "Sender" should "work getting a valid List[MSenderDataResponse]" in {
//    val res1 = Await.result(MandrillAsyncClient.senders(MPing()), timeout)
//    println("res1: " + res1)
//  }



  override def afterAll() {
    MandrillAsyncClient.shutdownSystem()
  }
}
