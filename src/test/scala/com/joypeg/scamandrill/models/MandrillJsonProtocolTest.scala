package com.joypeg.scamandrill.models

import org.scalatest.{Matchers, FlatSpec}
import com.joypeg.scamandrill.utils.SimpleLogger

class MandrillJsonProtocolTest extends FlatSpec with Matchers with SimpleLogger {

  import com.joypeg.scamandrill.MandrillTestUtils._

  "MSendMessageJsonFormat" should "marshall and unmarshall a MSendMessage" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val jsonized = MSendMsgJsonFormat.write(validMessage)
    MSendMsgJsonFormat.read(jsonized) shouldBe validMessage
  }

}

