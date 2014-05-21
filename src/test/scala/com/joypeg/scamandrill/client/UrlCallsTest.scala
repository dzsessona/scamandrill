package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.Success
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.MandrillTestUtils._
import scala.util.Failure
import com.joypeg.scamandrill.models.MRejectAdd
import scala.util.Success
import com.joypeg.scamandrill.models.MRejectAddResponse

class UrlCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "UrlsList" should "work getting a valid List[MUrlResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.urlsList(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MUrlResponse] (blocking client)" in {
    MandrillBlockingClient.urlsList(MKey()) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsList(MKey( key="invalid")))
  }

  "UrlsSearch" should "work getting a valid List[MUrlResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.urlsSearch(MUrlSearch(q = "http://example.com/example")), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MUrlResponse] (blocking client)" in {
    MandrillBlockingClient.urlsSearch(MUrlSearch(q = "http://example.com/example")) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsSearch(MUrlSearch(q = "http://example.com/example", key="invalid")))
  }

//  "UrlsTimeSeries" should "work getting a valid List[MUrlTimeResponse] (async client)" in {
//    val res = Await.result(MandrillAsyncClient.urlsTimeSeries(MUrlTimeSeries(key = "http://example.com/example")), DefaultConfig.defaultTimeout)
//    res shouldBe Nil
//  }
//  it should "work getting a valid List[MUrlTimeResponse] (blocking client)" in {
//    MandrillBlockingClient.urlsTimeSeries(MUrlTimeSeries(key = "http://example.com/example")) match {
//      case Success(res) =>
//        res shouldBe Nil
//      case Failure(ex) => fail(ex)
//    }
//  }
  "UrlsTimeSeries" should "fail if the operation is not allowed for the account, with an 'Unknown_Url' code" in {
    MandrillBlockingClient.urlsTimeSeries(MUrlTimeSeries(url = "http://example.com/example")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 7, "Unknown_Url", "Unknown URL")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsTimeSeries(MUrlTimeSeries(url = "http://example.com/example", key="invalid")))
  }


  "UrlsAddTrackingDomain" should "work getting a valid MUrlDomainResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.urlsAddTrackingDomain(MUrlDomain(domain = "test.com")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "test.com"
  }
  it should "work getting a valid MUrlDomainResponse (blocking client)" in {
    MandrillBlockingClient.urlsAddTrackingDomain(MUrlDomain(domain = "test.com")) match {
      case Success(res) =>
        res.domain shouldBe "test.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsAddTrackingDomain(MUrlDomain(domain = "test.com", key="invalid")))
  }

  "UrlsTrackingDomain" should "work getting a valid List[MUrlDomainResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.urlsTrackingDomain(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MUrlDomainResponse]
    res.head.domain shouldBe "test.com"
  }
  it should "work getting a valid List[MUrlDomainResponse] (blocking client)" in {
    MandrillBlockingClient.urlsTrackingDomain(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MUrlDomainResponse]
        res.head.domain shouldBe "test.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsTrackingDomain(MKey( key="invalid")))
  }

  "UrlsCheckTrackingDomain" should "work getting a valid List[MUrlDomainResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.urlsCheckTrackingDomain(MUrlDomain(domain= "test.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MUrlDomainResponse]
    res.domain shouldBe "test.com"
  }
  it should "work getting a valid MUrlDomainResponse (blocking client)" in {
    MandrillBlockingClient.urlsCheckTrackingDomain(MUrlDomain(domain= "test.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MUrlDomainResponse]
        res.domain shouldBe "test.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the operation is not allowed for the account, with an 'Unknown_TrackingDomain' code" in {
    MandrillBlockingClient.urlsCheckTrackingDomain(MUrlDomain(domain= "track.example.com")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 21, "Unknown_TrackingDomain", "No tracking domain exists with the name 'track.example.com'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.urlsCheckTrackingDomain(MUrlDomain(domain= "track.example.com", key="invalid")))
  }



}
