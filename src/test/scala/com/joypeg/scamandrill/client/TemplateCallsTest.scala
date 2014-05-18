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

  "TemplateDelete" should "work getting a valid MTemplateAddResponses (async client)" in {
    val res = Await.result(MandrillAsyncClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate2.name)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MTemplateAddResponses]
    res.publish_code shouldBe None
    res.slug shouldBe "templatetest2"
    res.publish_name shouldBe  "templatetest2"
  }
  it should "work getting a valid MTemplateAddResponses (blocking client)" in {
    MandrillBlockingClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate.name)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        println(res)
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.templateDelete(MTemplateInfo(name = validNonPublidhedTemplate2.name, key="invalid")))
  }

}


//override def templateInfo(template: MTemplateInfo): Future[MTemplateAddResponses] = {
//executeQuery[MTemplateAddResponses](Endpoints.tmpinfo.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
//}
//
//override def templateUpdate(template: MTemplate): Future[MTemplateAddResponses] = {
//executeQuery[MTemplateAddResponses](Endpoints.tmpupdate.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
//}
//
//override def templatePublish(template: MTemplateInfo): Future[MTemplateAddResponses] = {
//executeQuery[MTemplateAddResponses](Endpoints.tmppublish.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
//}
//
//override def templateList(template: MTemplateList): Future[List[MTemplateAddResponses]] = {
//executeQuery[List[MTemplateAddResponses]](Endpoints.tmplist.endpoint, marshal(template))(unmarshal[List[MTemplateAddResponses]])
//}
//
//override def templateTimeSeries(template: MTemplateInfo): Future[List[MTimeSeriesResponse]] = {
//executeQuery[List[MTimeSeriesResponse]](Endpoints.tmpts.endpoint, marshal(template))(unmarshal[List[MTimeSeriesResponse]])
//}
//
//override def templateRender(template: MTemplateRender): Future[MTemplateRenderResponse] = {
//executeQuery[MTemplateRenderResponse](Endpoints.tmprender.endpoint, marshal(template))(unmarshal[MTemplateRenderResponse])
//}