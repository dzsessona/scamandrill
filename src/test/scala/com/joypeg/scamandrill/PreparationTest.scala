package com.joypeg.scamandrill

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfterAll
import com.joypeg.scamandrill.models._
import scala.util.{Failure, Success}
import com.joypeg.scamandrill.utils._
import com.joypeg.scamandrill.client.MandrillBlockingClient


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

}