package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.MandrillTestUtils._
import scala.util.Failure
import scala.util.Success

class TemplateCallsTest extends FlatSpec with Matchers with SimpleLogger {

  "TemplateAdd" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateAdd(validNonPublidhedTemplate2), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
    res.publish_code shouldBe None
    res.slug shouldBe "templatetest2"
    res.publish_name shouldBe  "templatetest2"
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateAdd(validNonPublidhedTemplate) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        res.publish_code shouldBe None
        res.slug shouldBe "templatetest"
        res.publish_name shouldBe  "templatetest"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the operation is the template already exists, with an 'Invalid_Template' code" in {
    MandrillBlockingClient.templateAdd(validNonPublidhedTemplate) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 6, "Invalid_Template", "A template with name \"templatetest\" already exists")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateAdd(validNonPublidhedTemplate.copy( key="invalid")))
  }

  "TemplatePublish" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templatePublish(MTemplateInfo(name = validNonPublidhedTemplate.name)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
    res.publish_code shouldBe Some("<div>example code</div>")
    res.slug shouldBe "templatetest"
    res.publish_name shouldBe  "templatetest"
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templatePublish(MTemplateInfo(name = validNonPublidhedTemplate2.name)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        res.publish_code shouldBe Some("<div>example code</div>")
        res.slug shouldBe "templatetest2"
        res.publish_name shouldBe  "templatetest2"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the template does not exists, with an 'Unknown_Template' code" in {
    MandrillBlockingClient.templatePublish(MTemplateInfo(name = "nonexisting")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 5, "Unknown_Template", "No such template \"nonexisting\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templatePublish(MTemplateInfo(name = validNonPublidhedTemplate.name, key="invalid")))
  }


  "TemplateInfo" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateInfo(MTemplateInfo(name = validNonPublidhedTemplate.name)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
    res.publish_code shouldBe Some("<div>example code</div>")
    res.slug shouldBe "templatetest"
    res.publish_name shouldBe  "templatetest"
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateInfo(MTemplateInfo(name = validNonPublidhedTemplate2.name)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        res.publish_code shouldBe Some("<div>example code</div>")
        res.slug shouldBe "templatetest2"
        res.publish_name shouldBe  "templatetest2"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the the template does not exists, with an 'Unknown_Template' code" in {
    MandrillBlockingClient.templateInfo(MTemplateInfo(name = "nonexisting")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 5, "Unknown_Template", "No such template \"nonexisting\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateInfo(MTemplateInfo(name = validNonPublidhedTemplate.name, key="invalid")))
  }

  "TemplateList" should "work getting a valid List[MTemplateAddResponses] (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateList(MTemplateList(label = validNonPublidhedTemplate.labels.head)), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MTemplateAddResponses]
    res.head.publish_code shouldBe Some("<div>example code</div>")
    res.head.slug shouldBe "templatetest"
    res.head.publish_name shouldBe  "templatetest"
  }
  it should "work getting a valid List[MTemplateAddResponses] (blocking client)" in {
    MandrillBlockingClient.templateList(MTemplateList(label = validNonPublidhedTemplate2.labels.head)) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MTemplateAddResponses]
        res.head.publish_code shouldBe Some("<div>example code</div>")
        res.head.slug shouldBe "templatetest2"
        res.head.publish_name shouldBe  "templatetest2"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateList(MTemplateList(label = validNonPublidhedTemplate.labels.head, key="invalid")))
  }


  "TemplateUpdate" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateUpdate(validNonPublidhedTemplate2), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
    res.publish_code shouldBe Some("<div>example code</div>")
    res.slug shouldBe "templatetest2"
    res.publish_name shouldBe  "templatetest2"
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateUpdate(validNonPublidhedTemplate) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        res.publish_code shouldBe Some("<div>example code</div>")
        res.slug shouldBe "templatetest"
        res.publish_name shouldBe  "templatetest"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateAdd(validNonPublidhedTemplate.copy( key="invalid")))
  }

//  "templateTimeSeries" should "work getting a valid List[MTimeSeriesResponse] (async client)" in {
//    val res = Await.result(MandrillAsyncClient.templateTimeSeries(MTemplateInfo(name = validNonPublidhedTemplate2.name)), DefaultConfig.defaultTimeout)
//    res.head.getClass shouldBe classOf[MTimeSeriesResponse]
//  }
//  it should "work getting a valid List[MTimeSeriesResponse] (blocking client)" in {
//    MandrillBlockingClient.templateTimeSeries(MTemplateInfo(name = validNonPublidhedTemplate.name)) match {
//      case Success(res) =>
//        res.head.getClass shouldBe classOf[MTimeSeriesResponse]
//      case Failure(ex) => fail(ex)
//    }
//  }
  "TemplateTimeSeries" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateTimeSeries(MTemplateInfo(name = validNonPublidhedTemplate2.name, key="invalid")))
  }

  "TemplateRender" should "work getting a valid MTemplateRenderResponse (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateRender(validTemplateRender), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateRenderResponse]
    res.html shouldBe Some("<div>example code</div>")
    println(res)
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateRender(validTemplateRender) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateRenderResponse]
        res.html shouldBe Some("<div>example code</div>")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the template does not exists, with an 'Unknown_Template' code" in {
    MandrillBlockingClient.templateRender(validTemplateRender.copy(template_name = "nonexsting")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: spray.httpx.UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 5, "Unknown_Template", "No such template \"nonexsting\"")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateRender(validTemplateRender.copy(key="invalid")))
  }


  "TemplateDelete" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate2.name)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate.name)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate2.name, key="invalid")))
  }


}
