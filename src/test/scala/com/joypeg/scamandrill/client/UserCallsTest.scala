package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.models.MSenderDataResponse
import com.joypeg.scamandrill.models.MInfoResponse
import scala.util.Success
import com.joypeg.scamandrill.models.MKey
import com.joypeg.scamandrill.models.MPingResponse
import scala.util.Failure

class UserCallsTest extends FlatSpec with Matchers with SimpleLogger{

  "Ping" should "work getting a valid MPingResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.usersPing(MKey()), DefaultConfig.defaultTimeout)
    res shouldBe MPingResponse("\"PONG!\"")
  }
  it should "work getting a valid MPingResponse (blocking client)" in {
    MandrillBlockingClient.usersPing(MKey()) match {
      case Success(res) => res shouldBe MPingResponse("\"PONG!\"")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.usersPing(MKey("invalidkeytest")))
  }

  "Ping (version 2)" should "work getting a valid MPingResponse" in {
    val res1 = Await.result(MandrillAsyncClient.usersPing2(MKey()), DefaultConfig.defaultTimeout)
    res1 shouldBe MPingResponse("PONG!")
  }
  it should "work getting a valid MPingResponse (blocking client)" in {
    MandrillBlockingClient.usersPing2(MKey()) match {
      case Success(res) => res shouldBe MPingResponse("PONG!")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.usersPing2(MKey("invalidkeytest")))
  }

  "Sender" should "work getting a valid List[MSenderDataResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.usersSenders(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSenderDataResponse]
    res.exists(_.address == "scamandrill@test.com") shouldBe true
  }
  it should "work getting a valid List[MSenderDataResponse] (blocking client)" in {
    MandrillBlockingClient.usersSenders(MKey()) match {
      case Success(res) => res.head.getClass shouldBe classOf[MSenderDataResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.usersSenders(MKey("invalidkeytest")))
  }

  "Info" should "work getting a valid MInfoResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.usersInfo(MKey()), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MInfoResponse]
    res.username shouldBe "joypegtech@gmail.com"
    res.created_at shouldBe "2014-05-13 12:59:13.93756"
    res.hourly_quota shouldBe 250
    res.public_id shouldBe "L-NrI-gTRL774TxZ_GS9oA"
  }
  it should "work getting a valid MInfoResponse (blocking client)" in {
    MandrillBlockingClient.usersInfo(MKey()) match {
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
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.usersInfo(MKey("invalidkeytest")))
  }
}
