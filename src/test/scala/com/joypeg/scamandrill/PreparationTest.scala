package com.joypeg.scamandrill

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.client.MandrillBlockingClient
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.models.MSendResponse
import com.joypeg.scamandrill.models.MTemplateAddResponses
import com.joypeg.scamandrill.models.MTemplate
import scala.util.Success
import com.joypeg.scamandrill.models.MTo
import scala.util.Failure
import com.joypeg.scamandrill.models.MSendMessage


class PreparationTest extends FlatSpec with Matchers with BeforeAndAfterAll with SimpleLogger {

  ignore should "create the testing template" in {
    MandrillBlockingClient.templateAdd(MTemplate(
      name = "testtemplate",
      from_email = "from_email@example.com",
      from_name = "Example Name",
      subject = "example subject",
      code = "<div>example code</div>",
      text = "Example text content",
      publish = false,
      labels = List("test")
    ))
    match {
      case Success(res) =>
        res.getClass shouldBe classOf[MTemplateAddResponses]
        println(res)
      case Failure(ex) => fail(ex)
    }
  }

  ignore should "send a test email with a not test key (a real message)" in {
    val msg = new MSendMsg(
      html = "<h1>test</h1>",
      text = "test",
      subject = "subject test",
      from_email = "scamandrill@test.com",
      from_name = "Scamandrill",
      to = List(MTo("test@example.com")),
      bcc_address = "",
      tracking_domain = "fromname",
      signing_domain = "fromname",
      return_path_domain = "fromname"
    )
    MandrillBlockingClient.send(MSendMessage(message = msg, key = "REPLACE this with your valid (non testing) KEY")) match {
      case Success(res) =>
        println("your id of the mail just sent is the following: " + res.head._id +
          " you should set the value of MandrillTestUtils.idOfMailForInfoTest to this value")
        res.head.getClass shouldBe classOf[MSendResponse]
      case Failure(ex) => fail(ex)
    }
  }

}