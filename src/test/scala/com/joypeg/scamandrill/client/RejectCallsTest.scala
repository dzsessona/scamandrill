package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.models.MKey
import scala.util.Failure
import com.joypeg.scamandrill.models.MRejectAdd
import scala.util.Success
import com.joypeg.scamandrill.models.MRejectAddResponse

class RejectCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "RejectAdd" should "work getting a valid MRejectAdd (async client)" in {
    val res = Await.result(MandrillAsyncClient.rejectAdd(MRejectAdd(email = "reject@example.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MRejectAddResponse]
    res shouldBe MRejectAddResponse("reject@example.com",true)
  }
  it should "work getting a valid MRejectAdd (blocking client)" in {
    MandrillBlockingClient.rejectAdd(MRejectAdd(email = "reject2@example.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MRejectAddResponse]
        res shouldBe MRejectAddResponse("reject2@example.com",true)
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.rejectAdd(MRejectAdd(email = "reject2@example.com", key="invalid")))
  }
  it should "fail if the subaccount passed is invalid, with an 'Unknown_Subaccount' code" in {
    MandrillBlockingClient.rejectAdd(MRejectAdd(email = "reject@example.com", subaccount = Some("nonexisting"))) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 12, "Unknown_Subaccount", "No subaccount exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "RejectList" should "work getting a valid MRejectListResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.rejectList(MRejectList(email = "reject@example.com")), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MRejectListResponse]
    res.head.email shouldBe "reject@example.com"
  }
  it should "work getting a valid List[MRejectListResponse] (blocking client)" in {
    MandrillBlockingClient.rejectList(MRejectList(email = "reject2@example.com")) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MRejectListResponse]
        res.head.email shouldBe "reject2@example.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.rejectList(MRejectList(email = "reject2@example.com", key="invalid")))
  }
  it should "fail if the subaccount passed is invalid, with an 'Unknown_Subaccount' code" in {
    MandrillBlockingClient.rejectList(MRejectList(email = "reject@example.com", subaccount = Some("nonexisting"))) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 12, "Unknown_Subaccount", "No subaccount exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

  "RejecDelete" should "work getting a valid MRejectDeleteResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.rejectDelete(MRejectDelete(email = "reject@example.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MRejectDeleteResponse]
    res shouldBe MRejectDeleteResponse("reject@example.com", true, None)
  }
  it should "work getting a valid MRejectDeleteResponse (blocking client)" in {
    MandrillBlockingClient.rejectDelete(MRejectDelete(email = "reject2@example.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MRejectDeleteResponse]
        res shouldBe MRejectDeleteResponse("reject2@example.com",true, None)
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.rejectDelete(MRejectDelete(email = "reject2@example.com", key="invalid")))
  }
  it should "fail if the subaccount passed is invalid, with an 'Unknown_Subaccount' code" in {
    MandrillBlockingClient.rejectDelete(MRejectDelete(email = "reject@example.com", subaccount = Some("nonexisting"))) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 12, "Unknown_Subaccount", "No subaccount exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }

}
