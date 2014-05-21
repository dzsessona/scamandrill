package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success

class ExportCallsTest extends FlatSpec with Matchers with SimpleLogger {

//  "ExportList" should "work getting a valid MExportResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.exportList(MKey()), DefaultConfig.defaultTimeout)
//    res shouldBe Nil
//  }
//  it should "work getting a valid  MExportResponse (blocking client)" in {
//    MandrillBlockingClient.exportList(MKey()) match {
//      case Success(res) =>
//        res shouldBe Nil
//      case Failure(ex) => fail(ex)
//    }
//  }
  "ExportList" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.exportList(MKey(key="invalid")))
  }


  "ExportInfo" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.exportInfo(MExportInfo(id = "exmaple", key="invalid")))
  }


//  "ExportWhitelist" should "work getting a valid MExportResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.exportWhitelist(MExportNotify(notify_email = "example@example.com")), DefaultConfig.defaultTimeout)
//    res.`type` shouldBe "whitelist"
//  }
//  it should "work getting a valid  MExportResponse (blocking client)" in {
//    MandrillBlockingClient.exportWhitelist(MExportNotify(notify_email = "example@example.com")) match {
//      case Success(res) =>
//        res.`type` shouldBe "whitelist"
//      case Failure(ex) => fail(ex)
//    }
//  }
  "ExportWhitelist" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.exportWhitelist(MExportNotify(notify_email = "example@example.com", key="invalid")))
  }

//  "ExportReject" should "work getting a valid MExportResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.exportReject(MExportNotify(notify_email = "examplereject@example.com")), DefaultConfig.defaultTimeout)
//    res.`type` shouldBe "add"
//  }
//  it should "work getting a valid  MExportResponse (blocking client)" in {
//    MandrillBlockingClient.exportReject(MExportNotify(notify_email = "examplereject@example.com")) match {
//      case Success(res) =>
//        res.`type` shouldBe "add"
//      case Failure(ex) => fail(ex)
//    }
//  }
  "ExportReject" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.exportReject(MExportNotify(notify_email = "examplereject@example.com", key="invalid")))
  }
}
