package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success


class SubaccountCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "SubaccountAdd" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountAdd(validSubaccount), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsResponse]
    res.id shouldBe validSubaccount.id
    res.name shouldBe validSubaccount.name
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountAdd(validSubaccount2) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsResponse]
        res.id shouldBe validSubaccount2.id
        res.name shouldBe validSubaccount2.name
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if a subaccount wioth the same id already exists, with a code of ValidationError" in {
    MandrillBlockingClient.subaccountAdd(validSubaccount) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -2, "ValidationError", "Validation error: {\"id\":\"A subaccount with id testingsubaccount already exists\"}")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountAdd(validSubaccount.copy(key="invalid")))
  }


  "SubaccountPause" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountPause(MSubaccountInfo(id = validSubaccount.id)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsResponse]
    res.id shouldBe validSubaccount.id
    res.name shouldBe validSubaccount.name
    res.status shouldBe "paused"
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountPause(MSubaccountInfo(id = validSubaccount2.id)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsResponse]
        res.id shouldBe validSubaccount2.id
        res.name shouldBe validSubaccount2.name
        res.status shouldBe "paused"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountPause(MSubaccountInfo(id = validSubaccount.id,key="invalid")))
  }


  "SubaccountResume" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountResume(MSubaccountInfo(id = validSubaccount.id)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsResponse]
    res.id shouldBe validSubaccount.id
    res.name shouldBe validSubaccount.name
    res.status shouldBe "active"
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountResume(MSubaccountInfo(id = validSubaccount2.id)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsResponse]
        res.id shouldBe validSubaccount2.id
        res.name shouldBe validSubaccount2.name
        res.status shouldBe "active"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountResume(MSubaccountInfo(id = validSubaccount.id,key="invalid")))
  }


  "SubaccountUpdate" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountUpdate(validSubaccount.copy(notes = "updated")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsResponse]
    res.id shouldBe validSubaccount.id
    res.name shouldBe validSubaccount.name
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountUpdate(validSubaccount2.copy(notes = "updated")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsResponse]
        res.id shouldBe validSubaccount2.id
        res.name shouldBe validSubaccount2.name
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountUpdate(validSubaccount.copy(key="invalid")))
  }


  "SubaccountInfo" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountInfo(MSubaccountInfo(id = validSubaccount.id)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsInfoResponse]
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountInfo(MSubaccountInfo(id = validSubaccount2.id)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsInfoResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountInfo(MSubaccountInfo(id = validSubaccount.id,key="invalid")))
  }


  "SubaccountList" should "work getting a valid List[MSubaccountsResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountList(MSubaccountList(q = "test")), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSubaccountsResponse]
  }
  it should "work getting a valid List[MSubaccountsResponse] (blocking client)" in {
    MandrillBlockingClient.subaccountList(MSubaccountList(q = "test")) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSubaccountsResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountList(MSubaccountList(q = "test",key="invalid")))
  }


  "SubaccountDelete" should "work getting a valid MSubaccountsResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.subaccountDelete(MSubaccountInfo(id = validSubaccount.id)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSubaccountsResponse]
    res.id shouldBe validSubaccount.id
    res.name shouldBe validSubaccount.name
  }
  it should "work getting a valid MSubaccountsResponse (blocking client)" in {
    MandrillBlockingClient.subaccountDelete(MSubaccountInfo(id = validSubaccount2.id)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSubaccountsResponse]
        res.id shouldBe validSubaccount2.id
        res.name shouldBe validSubaccount2.name
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.subaccountDelete(MSubaccountInfo(id = validSubaccount.id,key="invalid")))
  }

}
