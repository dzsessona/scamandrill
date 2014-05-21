package com.joypeg.scamandrill.models

import org.scalatest.{Matchers, FlatSpec}
import com.joypeg.scamandrill.utils.SimpleLogger

class MandrillJsonProtocolTest extends FlatSpec with Matchers with SimpleLogger {

  import com.joypeg.scamandrill.MandrillTestUtils._

  "MSendMessageJsonFormat" should "marshall and unmarshall a MSendMessage" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val jsonized = MSendMsgJsonFormat.write(validMessage.copy(headers = Some(List(MHeader("the", "value")))))
    println("JSONIZED: " + jsonized)
    //MSendMsgJsonFormat.read(jsonized) shouldBe validMessage
  }

  "MHeader" should "marshall correctly" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val header = MHeader("Reply-To","message.reply@example.com")
    val jsonized = MHeaderJsonFormat.write(header)
    println("JSONIZED: " + jsonized)
  }

  "MMetadata" should "marshall correctly" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val header = MMetadata("Reply-To","message.reply@example.com")
    val jsonized = MMetadataJsonFormat.write(header)
    println("JSONIZED: " + jsonized)
  }

}

