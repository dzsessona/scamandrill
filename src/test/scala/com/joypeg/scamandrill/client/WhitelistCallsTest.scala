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

class WhitelistCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "WhitelistAdd" should "work getting a valid MWhitelistAddResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.whitelistAdd(MWhitelist(email = "whitelist@example.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MWhitelistAddResponse]
    res shouldBe MWhitelistAddResponse("whitelist@example.com",true)
  }
  it should "work getting a valid List[MTagResponse] (blocking client)" in {
    MandrillBlockingClient.whitelistAdd(MWhitelist(email = "whitelist2@example.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MWhitelistAddResponse]
        res shouldBe MWhitelistAddResponse("whitelist2@example.com",true)
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.whitelistAdd(MWhitelist(email = "reject2@example.com", key="invalid")))
  }

  "WhitelistList" should "work getting a valid List[MWhitelistDeleteResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.whitelistList(MWhitelist(email = "whitelist@example.com")), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MWhitelistListResponse]
    res.head.email shouldBe "whitelist@example.com"
  }
  it should "work getting a valid List[MWhitelistDeleteResponse] (blocking client)" in {
    MandrillBlockingClient.whitelistList(MWhitelist(email = "whitelist2@example.com")) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MWhitelistListResponse]
        res.head.email shouldBe "whitelist2@example.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.whitelistList(MWhitelist(email = "reject2@example.com", key="invalid")))
  }

  "WhitelistDelete" should "work getting a valid MWhitelistDeleteResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.whitelistDelete(MWhitelist(email = "whitelist@example.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MWhitelistDeleteResponse]
    res shouldBe MWhitelistDeleteResponse("whitelist@example.com",true)
  }
  it should "work getting a valid MWhitelistDeleteResponse (blocking client)" in {
    MandrillBlockingClient.whitelistDelete(MWhitelist(email = "whitelist2@example.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MWhitelistDeleteResponse]
        res shouldBe MWhitelistDeleteResponse("whitelist2@example.com",true)
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.whitelistDelete(MWhitelist(email = "reject2@example.com", key="invalid")))
  }
}
