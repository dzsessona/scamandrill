package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success

class WebhookCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "WebhookList" should "work getting a valid List[MWebhooksResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.webhookList(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MWebhooksResponse] (blocking client)" in {
    MandrillBlockingClient.webhookList(MKey()) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.webhookList(MKey(key="invalid")))
  }

  "WebhookAdd" should "fail if the key is not valid, with an 'ValidationError' code" in {
    MandrillBlockingClient.webhookAdd(validWebhook) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -2, "ValidationError", "Validation error: {\"key\":\"That is not a valid URL\"}")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "WebhookInfo" should "fail if the key specified with the id does not exists" in {
    MandrillBlockingClient.webhookInfo(MWebhookInfo(id = 4)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 3, "Unknown_Webhook", "Unknown hook ID 4")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "WebhookDelete" should "fail if the key specified with the id does not exists" in {
    MandrillBlockingClient.webhookDelete(MWebhookInfo(id = 4)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 3, "Unknown_Webhook", "Unknown hook ID 4")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "WebhookUpdate" should "fail if the key specified with the id does not exists" in {
    MandrillBlockingClient.webhookUpdate(validWebhookUpdate) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -2, "ValidationError", "Validation error: {\"key\":\"That is not a valid URL\"}")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
}
