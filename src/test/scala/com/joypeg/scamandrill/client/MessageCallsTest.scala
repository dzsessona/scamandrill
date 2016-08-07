package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._

class MessageCallsTest extends FlatSpec with Matchers with SimpleLogger {

  import com.joypeg.scamandrill.MandrillTestUtils._


  "Send" should "work getting a valid List[MSendResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesSend(MSendMessage(message = validMessage)), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSendResponse]
    res.size shouldBe 1
    res.head.status shouldBe "sent"
    res.head.email shouldBe "test@recipient.com"
    res.head.reject_reason shouldBe None
  }
  it should "return as many MSendResponse as the recipients list size" in {
    mandrillBlockingClient.messagesSend(MSendMessage(message =
      validMessage.copy(to = List(MTo("test@recipient.com"),MTo("test1@recipient.2"),MTo("tes3@recipient.3"))))) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendResponse]
        res.size shouldBe 3
      case Failure(ex) => fail(ex)
    }
  }
  it should "work getting a valid List[MSendResponse] (blocking client)" in {
    mandrillBlockingClient.messagesSend(MSendMessage(message = validMessage)) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendResponse]
        res.size shouldBe 1
        res.head.status shouldBe "sent"
        res.head.email shouldBe "test@recipient.com"
        res.head.reject_reason shouldBe None
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesSend(MSendMessage(key = "invalid", message = validMessage)))
  }
  it should "fail if the subaccount passed is invalid, with an 'Unknown_Subaccount' code" in {
    val invalidMessage = validMessage.copy(subaccount = Some("nonexisting"))
    mandrillBlockingClient.messagesSend(MSendMessage(message = invalidMessage)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 12, "Unknown_Subaccount", "No subaccount exists with the id 'nonexisting'")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the operation is not allowed for the account, with an 'PaymentRequired' code" in {
    mandrillBlockingClient.messagesSend(MSendMessage(send_at=Some("3000-01-01 00:00:00"), message = validMessage)) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error",
          10, "PaymentRequired", "Email scheduling is only available for accounts with a positive balance.")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "return a message as 'queued' in case async=true" in {
    mandrillBlockingClient.messagesSend(MSendMessage(async = true, message = validMessage)) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendResponse]
        res.head.status shouldBe "sent"
      case Failure(ex) => fail(ex)
    }
  }

  "SendTemplate" should "work getting a valid List[MSendResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesSendTemplate(MSendTemplateMessage(
      template_name = "testtemplate",
      template_content = List(MVars("first", "example")),
      message = validMessage)), DefaultConfig.defaultTimeout)
    res.head.getClass shouldBe classOf[MSendResponse]
    res.size shouldBe 1
    res.head.status shouldBe "sent"
    res.head.email shouldBe "test@recipient.com"
    res.head.reject_reason shouldBe None
  }
  it should "return as many MSendResponse as the recipients list size" in {
    mandrillBlockingClient.messagesSendTemplate(MSendTemplateMessage(
      template_name = "testtemplate",
      template_content = List(MVars("first", "example")),
      message = validMessage)) match {
      case Success(res) =>
        res.head.getClass shouldBe classOf[MSendResponse]
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the template does not exists, with an 'Unknown_Template' code" in {
    mandrillBlockingClient.messagesSendTemplate(MSendTemplateMessage(
      template_name = "invalid",
      template_content = List(MVars("first", "invalid")),
      message = validMessage))match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error",
          5, "Unknown_Template", """No such template "invalid"""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesSendTemplate(MSendTemplateMessage(
      key = "invalid", template_name = "invalid",
      template_content = List(MVars("first", "invalid")),
      message = validMessage)))
  }

  "Search" should "work getting a valid List[MSearchResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesSearch(validSearch), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MSearchResponse] (blocking client)" in {
    mandrillBlockingClient.messagesSearch(validSearch) match {
      case Success(res) =>res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesSearch(validSearch.copy(key = "invalid")))
  }

  "SearchTimeSeries" should "work getting a valid List[MSearchResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesSearchTimeSeries(validSearchTimeSeries), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MSearchResponse] (blocking client)" in {
    mandrillBlockingClient.messagesSearchTimeSeries(validSearchTimeSeries) match {
      case Success(res) =>res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesSearch(validSearch.copy(key = "invalid")))
  }

  "MessageInfo" should "work getting a valid MMessageInfoResponse (async client)" ignore {
    val res = Await.result(mandrillAsyncClient.messagesInfo(MMessageInfo(id = idOfMailForInfoTest)), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MMessageInfoResponse]
    res._id shouldBe idOfMailForInfoTest
    res.subject shouldBe "subject test"
    res.email shouldBe "test@example.com"
  }
  ignore should "work getting a valid MMessageInfoResponse (blocking client)" in {
    mandrillBlockingClient.messagesInfo(MMessageInfo(id = idOfMailForInfoTest)) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MMessageInfoResponse]
        res._id shouldBe idOfMailForInfoTest
        res.subject shouldBe "subject test"
        res.email shouldBe "test@example.com"
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the id does not exists, with an 'Unknown_Message' code" in {
    mandrillBlockingClient.messagesInfo(MMessageInfo(id = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 11, "Unknown_Message", """No message exists with the id 'invalid'""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesInfo(MMessageInfo(key = "invalid", id = idOfMailForInfoTest)))
  }

//  This call doesn't seem to work in the api
//  "MessageInfo" should "work getting a valid MContentResponse (async client)" in {
//    val res = Await.result(mandrillAsyncClient.content(MMessageInfo(id = idOfMailForInfoTest)), DefaultConfig.defaultTimeout)
//    res.getClass shouldBe classOf[MMessageInfoResponse]
//    res._id shouldBe idOfMailForInfoTest
//    res.subject shouldBe "subject test"
//    println(res)
//    //res.email shouldBe "test@example.com"
//  }
//  it should "work getting a valid MContentResponse (blocking client)" in {
//    mandrillBlockingClient.content(MMessageInfo(id = idOfMailForInfoTest)) match {
//      case Success(res) =>
//        res.getClass shouldBe classOf[MMessageInfoResponse]
//        res._id shouldBe idOfMailForInfoTest
//        res.subject shouldBe "subject test"
//        //res.email shouldBe "test@example.com"
//      case Failure(ex) => fail(ex)
//    }
//  }
  "Content" should "fail if the id does not exists, with an 'Unknown_Message' code" in {
    mandrillBlockingClient.messagesContent(MMessageInfo(id = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 11, "Unknown_Message", """No message exists with the id 'invalid'""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesContent(MMessageInfo(key = "invalid", id = idOfMailForInfoTest)))
  }

  "Parse" should "work getting a valid MParseResponse (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesParse(MParse(raw_message = """From: sender@example.com""")), DefaultConfig.defaultTimeout)
    res.getClass shouldBe classOf[MParseResponse]
    res.from_email shouldBe Some("sender@example.com")
  }
  it should "work getting a valid MParseResponse (blocking client)" in {
    mandrillBlockingClient.messagesParse(MParse(raw_message = """From: sender@example.com""")) match {
      case Success(res) =>
        res.getClass shouldBe classOf[MParseResponse]
        res.from_email shouldBe Some("sender@example.com")
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesParse(MParse(key="invalid",raw_message = """From: sender@example.com""")))
  }

  "SendRaw" should "work getting a valid MParseResponse (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesSendRaw(validRawMessage), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid MParseResponse (blocking client)" in {
    mandrillBlockingClient.messagesSendRaw(validRawMessage) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesSendRaw(validRawMessage.copy(key = "invalid")))
  }

  "ListSchedule" should "work getting a valid List[MScheduleResponse] (async client)" in {
    val res = Await.result(mandrillAsyncClient.messagesListSchedule(MListSchedule(to = "test@recipient.com")), DefaultConfig.defaultTimeout)
    res shouldBe Nil
  }
  it should "work getting a valid List[MScheduleResponse] (blocking client)" in {
    mandrillBlockingClient.messagesListSchedule(MListSchedule(to = "test@recipient.com")) match {
      case Success(res) =>
        res shouldBe Nil
      case Failure(ex) => fail(ex)
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesListSchedule(MListSchedule(to = "test@recipient.com", key = "invalid")))
  }

  "CancelSchedule" should "fail if the id does not exists, with an 'Unknown_Message' code" in {
    mandrillBlockingClient.messagesCancelSchedule(MCancelSchedule(id = "invalid")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 11, "Unknown_Message", """No message exists with the id 'invalid'""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesCancelSchedule(MCancelSchedule(id = "test@recipient.com", key = "invalid")))
  }

  "Reschedule" should "fail if the date is not valid, with an 'ValidationError' code" in {
    mandrillBlockingClient.messagesReschedule(MReSchedule(id = "invalid", send_at = "20120-06-01 08:15:01")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", -2, "ValidationError", """Validation error: {"send_at":"Please enter a valid date\/time"}""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the id of the message does not exist, with an 'Unknown_Message' code" in {
    mandrillBlockingClient.messagesReschedule(MReSchedule(id = "invalid", send_at = "2012-06-01 08:15:01")) match {
      case Success(res) =>
        fail("This operation should be unsuccessful")
      case Failure(ex: UnsuccessfulResponseException) =>
        val inernalError = MandrillError("error", 11, "Unknown_Message", """No message exists with the id 'invalid'""")
        val expected = new MandrillResponseException(500, "Internal Server Error", inernalError)
        checkError(expected, MandrillResponseException(ex))
      case Failure(ex) =>
        fail("should return an UnsuccessfulResponseException that can be parsed as MandrillResponseException")
    }
  }
  it should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(mandrillBlockingClient.messagesReschedule(MReSchedule(key = "invalid" ,id = "invalid", send_at = "2012-06-01 08:15:01")))
  }

}