package com.joypeg.scamandrill.models

import org.scalatest.{Matchers, FlatSpec}

object MandrillJsonProtocolTest {
  val msg = new MSendMsg(
    html = "<h1>test</h1>",
    text = "test",
    subject = "subj",
    from_email = "frommail",
    from_name = "fromname",
    to = List(MTo("test", "test", "test")),
    important = false,
    track_opens = false,
    track_clicks = false,
    auto_text = false,
    auto_html = false,
    inline_css = false,
    url_strip_qs = false,
    preserve_recipients = false,
    view_content_link = false,
    bcc_address = "fromname",
    tracking_domain = "fromname",
    signing_domain = "fromname",
    return_path_domain = "fromname",
    merge = false,
    global_merge_vars =  List(MVars("test", "test")),
    merge_vars = List(MMergeVars("test", List(MVars("test", "test")))),
    tags = List("onetag", "twotag"),
    subaccount = "test",
    google_analytics_domains = List("test"),
    google_analytics_campaign = "test",
    attachments = List.empty,
    images = List.empty
  )
}

class MandrillJsonProtocolTest extends FlatSpec with Matchers {

  import MandrillJsonProtocolTest._

  "MSendMessageJsonFormat" should "marshall and unmarshall a MSendMessage" in {
    import MandrillJsonProtocol._
    import spray.httpx.SprayJsonSupport._
    val jsonized = MSendMsgJsonFormat.write(msg)
    MSendMsgJsonFormat.read(jsonized) shouldBe msg
  }

}

