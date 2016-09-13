package com.joypeg.scamandrill

import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.client.{MandrillBlockingClient, MandrillAsyncClient, MandrillError, MandrillResponseException, UnsuccessfulResponseException}
import org.scalatest.Matchers
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import com.joypeg.scamandrill.models.MSearch
import scala.util.Success
import com.joypeg.scamandrill.models.MSearchTimeSeries
import com.joypeg.scamandrill.models.MTo
import scala.util.Failure
import scala.concurrent.duration._

object MandrillTestUtils extends Matchers {

  val mandrillAsyncClient = new MandrillAsyncClient()
  val mandrillBlockingClient = new MandrillBlockingClient(mandrillAsyncClient.system)
  implicit val mat = mandrillAsyncClient.materializer
  implicit val ec = mandrillAsyncClient.executionContext


  /**
   * A simple fuction to check equality of the errors from mandrill, but it also
   * display the reason of failure on screen, thing that I would loose using equality
   * on the errors themselves.
   * @param expected - the expected error
   * @param response - the error return from Mandrill
   */
  def checkError(expected: MandrillResponseException, responseF: Future[MandrillResponseException]): Unit = {
    val response = Await.result(responseF, 10 seconds)
    expected.httpCode shouldBe response.httpCode
    expected.httpReason shouldBe response.httpReason
    expected.mandrillError.code shouldBe response.mandrillError.code
    expected.mandrillError.message shouldBe response.mandrillError.message
    expected.mandrillError.name shouldBe response.mandrillError.name
    expected.mandrillError.status shouldBe response.mandrillError.status
  }

  /**
   * Utility method to check the failure because of an invalid key
   * @param response - the response from mandrill api
   */
  def checkFailedBecauseOfInvalidKey(response: Try[Any]): Unit = response match {
    case Success(res) =>
      fail("This operation should be unsuccessful")
    case Failure(ex: UnsuccessfulResponseException) =>
      val inernalError = MandrillError("error", -1, "Invalid_Key", "Invalid API key")
      val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
      checkError(expected, MandrillResponseException(ex))
    case Failure(ex) =>
      println(s"Failure is ${ex}")
      println(s"of class ${ex.getClass}")
      fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
  }

  val validRoute = MInboundRoute(
    domain= "example.com",
    pattern= "mailbox-*",
    url= "http://example.com"
  )

  val validSubaccount = MSubaccount(
    id = "testingsubaccount",
    name = "testingsubaccount",
    notes = "subaccount test",
    custom_quota = 250
  )

  val validSubaccount2 = MSubaccount(
    id = "testingsubaccount2",
    name = "testingsubaccount2",
    notes = "subaccount test",
    custom_quota = 250
  )

  val validWebhook = MWebhook(
    url = "http://example/key-key",
    description = "My Example Webhook",
    events = List("send", "open", "click")
  )

  val validWebhookUpdate = MWebhookUpdate(
    url = "http://example/key-key",
    description = "My Example Webhook",
    id = 4,
    events = List("send", "open", "click")
  )


  val idOfMailForInfoTest = "3edaed120eb144debb843893f4d92179"

  val validRawMessage = MSendRaw(raw_message =  """From: sender@example.com""")

  val validNonPublidhedTemplate = MTemplate(
    name = "templatetest",
    from_email = "from_email@example.com",
    from_name = "Example Name",
    subject = "example subject",
    code = "<div>example code</div>",
    text = "Example text content",
    publish = false,
    labels = List("templatetest")
  )

  val validTemplateRender = MTemplateRender(
    template_name = "templatetest",
    template_content = List(MTemplateCnt(name = "editable" , content = "<div>content to inject *|MERGE1|*</div>")),
    merge_vars = List(MTemplateCnt(name = "merge1" , content = "merge1 content"))
  )


  val validNonPublidhedTemplate2 = MTemplate(
    name = "templatetest2",
    from_email = "from_email@example.com",
    from_name = "Example Name",
    subject = "example subject",
    code = "<div>example code</div>",
    text = "Example text content",
    publish = false,
    labels = List("templatetest2")
  )

  val validMessage = new MSendMsg(
    html = "<h1>test</h1>",
    text = "test",
    subject = "subject test",
    from_email = "scamandrill@test.com",
    from_name = "Scamandrill",
    to = List(MTo("test@recipient.com")),
    bcc_address = Some("somebcc@address.com"),
    tracking_domain = Some("fromname"),
    signing_domain = Some("fromname"),
    return_path_domain = Some("fromname")
  )

  val validSearch = MSearch(
    query = "email:gmail.com",
    date_from = "2013-01-01",
    date_to = "2018-01-01")

  val validSearchTimeSeries = MSearchTimeSeries(
    query = "email:gmail.com",
    date_from = "2013-01-01",
    date_to = "2018-01-01")
}
