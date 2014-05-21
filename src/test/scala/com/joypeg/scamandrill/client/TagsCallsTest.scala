package com.joypeg.scamandrill.client

import org.scalatest.{Matchers, FlatSpec}
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.MandrillTestUtils._
import scala.util.Failure
import scala.util.Success
import com.joypeg.scamandrill.models.MKey
import scala.util.Failure
import com.joypeg.scamandrill.models.MTagResponse
import scala.util.Success

class TagsCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "TagList" should "work getting a valid List[MTagResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.tagList(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MTagResponse]
    res.head.tag shouldBe "onetag"
  }
  it should "work getting a valid List[MTagResponse] (blocking client)" in {
    MandrillBlockingClient.tagList(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MTagResponse]
        res.head.tag shouldBe "onetag"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.tagList(MKey("invalidkeytest")))
  }

//  "TagDelete" should "work getting a valid MTagResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.tagDelete(MTagRequest(key = "twotag")), DefaultConfig.defaultTimeout)
//    res.getClass shouldBe classOf[MTagResponse]
//    res.key shouldBe "twotag"
//  }
//  it should "work getting a valid MTagResponse (blocking client)" in {
//    MandrillBlockingClient.tagDelete(MTagRequest(key = "twotag")) match {
//      case Success(res) =>
//        res.key shouldBe "twotag"
//      case Failure(ex) => fail(ex)
//    }
//  }
  "TagDelete" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.tagDelete(MTagRequest(tag = "twotag", key="invalid")))
  }
  it should "fail if the key does not exists, with an 'Invalid_Tag_Name' code" in {
    MandrillBlockingClient.tagDelete(MTagRequest(tag = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 1, "Invalid_Tag_Name", "no such key \"invalid\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "TagInfo" should "work getting a valid MTagInfoResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.tagInfo(MTagRequest(tag = "onetag")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTagInfoResponse]
    res.tag shouldBe "onetag"
  }
  it should "work getting a valid MTagInfoResponse (blocking client)" in {
    MandrillBlockingClient.tagInfo(MTagRequest(tag = "onetag")) match {
      case Success(res) =>
        res.tag shouldBe "onetag"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.tagInfo(MTagRequest(tag = "twotag", key="invalid")))
  }
  it should "fail if the key does not exists, with an 'Invalid_Tag_Name' code" in {
    MandrillBlockingClient.tagInfo(MTagRequest(tag = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 1, "Invalid_Tag_Name", "no such key \"invalid\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "TagTimeSeries" should "work getting a valid List[MTimeSeriesResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.tagTimeSeries(MTagRequest(tag = "onetag")), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MTimeSeriesResponse] (blocking client)" in {
    MandrillBlockingClient.tagTimeSeries(MTagRequest(tag = "onetag")) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.tagTimeSeries(MTagRequest(tag = "twotag", key="invalid")))
  }
  it should "fail if the key does not exists, with an 'Invalid_Tag_Name' code" in {
    MandrillBlockingClient.tagTimeSeries(MTagRequest(tag = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 1, "Invalid_Tag_Name", "no such key \"invalid\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "TagAllTimeSeries" should "work getting a valid List[MTimeSeriesResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.tagAllTimeSeries(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MTimeSeriesResponse] (blocking client)" in {
    MandrillBlockingClient.tagAllTimeSeries(MKey()) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.tagAllTimeSeries(MKey(key="invalid")))
  }




}