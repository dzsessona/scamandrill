package com.joypeg.scamandrill.client


import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success


class InboundCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "InboundAddDomains" should "work getting a valid MInboundDomainResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.inboundAddDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    MandrillBlockingClient.inboundAddDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundAddDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }


  "InboundDomains" should "work getting a valid List[MInboundDomainResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.inboundDomains(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MInboundDomainResponse]
  }
  it should "work getting a valid  List[MInboundDomainResponse] (blocking client)" in {
    MandrillBlockingClient.inboundDomains(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MInboundDomainResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundDomains(MKey(key="invalid")))
  }


  "InboundCheckDomain" should "work getting a valid MInboundDomainResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    MandrillBlockingClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }


  "InboundDeleteDomains" should "work getting a valid MInboundDomainResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    MandrillBlockingClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }

  "InboundAddRoute" should "fail if the url is not valid, with an , with an 'Unknown_InboundDomain' code" in {
    MandrillBlockingClient.inboundAddRoute(validRoute) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 8, "Unknown_InboundDomain", "Unknown Inbound Domain: example.com")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundAddRoute(validRoute.copy(key = "invalid")))
  }

  "InboundDeleteRoute" should "fail if the url is not valid, with an , with an 'Unknown_InboundRoute' code" in {
    MandrillBlockingClient.inboundDeleteRoute(MInboundDelRoute(id = "nonexisting")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 22, "Unknown_InboundRoute", "No route exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundDeleteRoute(MInboundDelRoute(id = "nonexisting",key = "invalid")))
  }

  "InboundUpdateRoute" should "fail if the url is not valid, with an , with an 'Unknown_InboundRoute' code" in {
    MandrillBlockingClient.inboundUpdateRoute(MInboundUpdateRoute(id ="nonexisting", pattern="", url = "example")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 22, "Unknown_InboundRoute", "No route exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundUpdateRoute(MInboundUpdateRoute(id ="nonexisting", pattern="", url = "example",key = "invalid")))
  }

  "InboundRoutes" should "fail if the url is not valid, with an , with an 'Unknown_InboundDomain' code" in {
    MandrillBlockingClient.inboundRoutes(MInboundDomain(domain = "testingdomain")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 8, "Unknown_InboundDomain", "Unknown Inbound Domain: testingdomain")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.inboundRoutes(MInboundDomain(domain = "testingdomain", key = "invalid")))
  }
}

