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
    val res = Await.result(mandrillAsyncClient.inboundAddDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    mandrillBlockingClient.inboundAddDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundAddDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }


  "InboundDomains" should "work getting a valid List[MInboundDomainResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.inboundDomains(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MInboundDomainResponse]
  }
  it should "work getting a valid  List[MInboundDomainResponse] (blocking client)" in {
    mandrillBlockingClient.inboundDomains(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MInboundDomainResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundDomains(MKey(key="invalid")))
  }


  "InboundCheckDomain" should "work getting a valid MInboundDomainResponse (async client)" in {
    val res = Await.result(mandrillAsyncClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    mandrillBlockingClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundCheckDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }


  "InboundDeleteDomains" should "work getting a valid MInboundDomainResponse (async client)" in {
    val res = Await.result(mandrillAsyncClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain")), DefaultConfig.defaultTimeout)
    res.domain shouldBe "testingdomain"
    res.valid_mx shouldBe false
  }
  it should "work getting a valid  MInboundDomainResponse (blocking client)" in {
    mandrillBlockingClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain2")) match {
      case Success(res) =>
        res.domain shouldBe "testingdomain2"
        res.valid_mx shouldBe false
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundDeleteDomain(MInboundDomain(domain = "testingdomain", key="invalid")))
  }

  "InboundAddRoute" should "fail if the key is not valid, with an , with an 'Unknown_InboundDomain' code" in {
    mandrillBlockingClient.inboundAddRoute(validRoute) match {
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
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundAddRoute(validRoute.copy(key = "invalid")))
  }

  "InboundDeleteRoute" should "fail if the key is not valid, with an , with an 'Unknown_InboundRoute' code" in {
    mandrillBlockingClient.inboundDeleteRoute(MInboundDelRoute(id = "nonexisting")) match {
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
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundDeleteRoute(MInboundDelRoute(id = "nonexisting",key = "invalid")))
  }

  "InboundUpdateRoute" should "fail if the key is not valid, with an , with an 'Unknown_InboundRoute' code" in {
    mandrillBlockingClient.inboundUpdateRoute(MInboundUpdateRoute(id ="nonexisting", pattern="", url = "example")) match {
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
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundUpdateRoute(MInboundUpdateRoute(id ="nonexisting", pattern="", url = "example",key = "invalid")))
  }

  "InboundRoutes" should "fail if the key is not valid, with an , with an 'Unknown_InboundDomain' code" in {
    mandrillBlockingClient.inboundRoutes(MInboundDomain(domain = "testingdomain")) match {
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
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.inboundRoutes(MInboundDomain(domain = "testingdomain", key = "invalid")))
  }
}

