package com.joypeg.scamandrill.models

import org.scalatest.{Matchers, FlatSpec}

class MandrillJsonProtocolTest extends FlatSpec with Matchers {

  import com.joypeg.scamandrill.MandrillTestUtils._

  "MSendMessageJsonFormat" should "marshall and unmarshall a MSendMessage" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val jsonized = MSendMsgJsonFormat.write(validMessage)
    MSendMsgJsonFormat.read(jsonized) shouldBe validMessage
  }

}

