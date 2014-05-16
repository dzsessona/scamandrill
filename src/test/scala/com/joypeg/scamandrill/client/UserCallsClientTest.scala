package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._

class UserCallsClientTest extends FlatSpec with Matchers with BeforeAndAfterAll with SimpleLogger{

  "Ping" should "work getting a valid MPingResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.ping(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe MPingResponse("\"PONG!\"")
  }
  it should "work getting a valid MPingResponse (blocking client)" in {
    MandrillBlockingClient.ping(MKey()) match {
      case Success(res) => res shouldBe MPingResponse("\"PONG!\"")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    MandrillBlockingClient.ping(MKey("invalidkeytest")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -1, "Invalid_Key", "Invalid API key")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        MandrillResponseException(ex) shouldBe expected
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "Ping (version 2)" should "work getting a valid MPingResponse" in {
    val res1 = Await.result(MandrillAsyncClient.ping2(MKey()), DefaultConfig.defaultTimeout)
    res1 shouldBe MPingResponse("PONG!")
  }
  it should "work getting a valid MPingResponse (blocking client)" in {
    MandrillBlockingClient.ping2(MKey()) match {
      case Success(res) => res shouldBe MPingResponse("PONG!")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    MandrillBlockingClient.ping2(MKey("invalidkeytest")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -1, "Invalid_Key", "Invalid API key")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        MandrillResponseException(ex) shouldBe expected
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "Sender" should "work getting a valid List[MSenderDataResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.senders(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSenderDataResponse]
    res.exists(_.address == "scamandrill@test.com") shouldBe true
  }
  it should "work getting a valid List[MSenderDataResponse] (blocking client)" in {
    MandrillBlockingClient.senders(MKey()) match {
      case Success(res) => res.head.getClass shouldBe classOf[MSenderDataResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    MandrillBlockingClient.senders(MKey("invalidkeytest")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -1, "Invalid_Key", "Invalid API key")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        MandrillResponseException(ex) shouldBe expected
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "Info" should "work getting a valid MInfoResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.info(MKey()), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MInfoResponse]
    res.username shouldBe "joypegtech@gmail.com"
    res.created_at shouldBe "2014-05-13 12:59:13.93756"
    res.hourly_quota shouldBe 250
    res.public_id shouldBe "L-NrI-gTRL774TxZ_GS9oA"
  }
  it should "work getting a valid MInfoResponse (blocking client)" in {
    MandrillBlockingClient.info(MKey()) match {
      case Success(res: MInfoResponse) =>
        res.getClass shouldBe classOf[MInfoResponse]
        res.username shouldBe "joypegtech@gmail.com"
        res.created_at shouldBe "2014-05-13 12:59:13.93756"
        res.hourly_quota shouldBe 250
        res.public_id shouldBe "L-NrI-gTRL774TxZ_GS9oA"
      case Success(res) => fail("Excpecting a MInfoResponse object")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    MandrillBlockingClient.info(MKey("invalidkeytest")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -1, "Invalid_Key", "Invalid API key")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        MandrillResponseException(ex) shouldBe expected
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  override def afterAll() {
    MandrillAsyncClient.shutdownSystem()
  }
}
