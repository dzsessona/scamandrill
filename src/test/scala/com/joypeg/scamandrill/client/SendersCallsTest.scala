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

class SendersCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "SendersList" should "work getting a valid List[MSendersListResp] (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersList(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSendersListResp]
    res.head.address shouldBe "joypegtech@gmail.com"
  }
  it should "work getting a valid List[MSendersListResp] (blocking client)" in {
    MandrillBlockingClient.sendersList(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendersListResp]
        res.head.address shouldBe "joypegtech@gmail.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersList(MKey(key="invalid")))
  }

  "SendersDomains" should "work getting a valid List[MSendersDomainResponses] (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersDomains(MKey()), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSendersDomainResponses]
    res.head.domain shouldBe "gmail.com"
  }
  it should "work getting a validList[MSendersDomainResponses] (blocking client)" in {
    MandrillBlockingClient.sendersDomains(MKey()) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendersDomainResponses]
        res.head.domain shouldBe "gmail.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersDomains(MKey(key="invalid")))
  }

  "SendersAddDomain" should "work getting a valid MSendersDomainResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersAddDomain(MSenderDomain(domain ="test.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSendersDomainResponses]
    res.domain shouldBe "test.com"
  }
  it should "work getting a valid MSendersDomainResponses (blocking client)" in {
    MandrillBlockingClient.sendersAddDomain(MSenderDomain(domain ="test.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSendersDomainResponses]
        res.domain shouldBe "test.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersAddDomain(MSenderDomain(domain ="test.com",key="invalid")))
  }

  "SendersCheckDomain" should "work getting a valid MSendersDomainResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersCheckDomain(MSenderDomain(domain ="test.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSendersDomainResponses]
    res.domain shouldBe "test.com"
  }
  it should "work getting a valid MSendersDomainResponses (blocking client)" in {
    MandrillBlockingClient.sendersCheckDomain(MSenderDomain(domain ="test.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSendersDomainResponses]
        res.domain shouldBe "test.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersCheckDomain(MSenderDomain(domain ="test.com",key="invalid")))
  }

  "SendersVerifyDomain" should "work getting a valid MSendersVerifyDomResp (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersVerifyDomain(MSenderVerifyDomain(mailbox="joypeg.tech",domain ="gmail.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSendersVerifyDomResp]
    res.domain shouldBe "gmail.com"
  }
  it should "work getting a valid MSendersDomainResponses (blocking client)" in {
    MandrillBlockingClient.sendersVerifyDomain(MSenderVerifyDomain(mailbox="joypeg.tech",domain ="gmail.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSendersVerifyDomResp]
        res.domain shouldBe "gmail.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersVerifyDomain(MSenderVerifyDomain(mailbox="joypeg.tech",domain ="test.com",key="invalid")))
  }

  "SendersInfo" should "work getting a valid MSendersInfoResp (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersInfo(MSenderAddress(address ="joypegtech@gmail.com")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MSendersInfoResp]
    res.address shouldBe "joypegtech@gmail.com"
  }
  it should "work getting a valid MSendersInfoResp (blocking client)" in {
    MandrillBlockingClient.sendersInfo(MSenderAddress(address ="joypegtech@gmail.com")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MSendersInfoResp]
        res.address shouldBe "joypegtech@gmail.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersInfo(MSenderAddress(address ="joypegtech@gmail.com",key="invalid")))
  }

  "SendersTimeSeries" should "work getting a valid List[MSenderTSResponse] (async client)" in {
    val res = Await.result(MandrillAsyncClient.sendersTimeSeries(MSenderAddress(address ="joypegtech@gmail.com")), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSenderTSResponse]
    res.head.clicks shouldBe 0
  }
  it should "work getting a valid List[MSenderTSResponse] (blocking client)" in {
    MandrillBlockingClient.sendersTimeSeries(MSenderAddress(address ="joypegtech@gmail.com")) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSenderTSResponse]
        res.head.clicks shouldBe 0
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.sendersTimeSeries(MSenderAddress(address ="joypegtech@gmail.com",key="invalid")))
  }

}
