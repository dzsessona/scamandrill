package com.joypeg.scamandrill.models

import spray.json._
import com.joypeg.scamandrill.models._
import scala.collection.mutable.ArrayBuffer
import scala.util.Try

object MandrillJsonProtocol extends DefaultJsonProtocol {
  implicit val MPingJson              = jsonFormat1(MPing)
  implicit val MPingResponseJson      = jsonFormat1(MPingResponse)
  implicit val MSenderDataResponseJson= jsonFormat12(MSenderDataResponse)
  implicit val MStatJson              = jsonFormat10(MStat)
  implicit val MStatsJson             = jsonFormat6(MStats)
  implicit val MInfoResponseJson      = jsonFormat7(MInfoResponse)
  //implicit val MSendMessageJson       = jsonFormat5(MSendMessage)
  implicit val MAttachmetOrImageJson  = jsonFormat3(MAttachmetOrImage)
  implicit val MVarsJson              = jsonFormat2(MVars)
  implicit val MMergeVarsJson         = jsonFormat2(MMergeVars)
  implicit val MToJson                = jsonFormat3(MTo)
  implicit val MSendResponseJson      = jsonFormat4(MSendResponse)
  implicit object MSendMsgJsonFormat extends RootJsonFormat[MSendMsg] {
    def write(msg: MSendMsg) = JsObject(
        "html" -> JsString(msg.html),
        "text" -> JsString(msg.text),
        "subject" -> JsString(msg.subject),
        "from_email" -> JsString(msg.from_email),
        "from_name" -> JsString(msg.from_name),
        "to" -> JsArray(msg.to.map(_.toJson)),
        "important" -> JsBoolean(msg.important),
        "track_opens" -> JsBoolean(msg.track_opens),
        "track_clicks" -> JsBoolean(msg.track_clicks),
        "auto_text" -> JsBoolean(msg.auto_text),
        "auto_html" -> JsBoolean(msg.auto_html),
        "inline_css" -> JsBoolean(msg.inline_css),
        "url_strip_qs" -> JsBoolean(msg.url_strip_qs),
        "preserve_recipients" -> JsBoolean(msg.preserve_recipients),
        "view_content_link" -> JsBoolean(msg.view_content_link),
        "bcc_address" -> JsString(msg.bcc_address),
        "tracking_domain" -> JsString(msg.tracking_domain),
        "signing_domain" -> JsString(msg.signing_domain),
        "return_path_domain" -> JsString(msg.return_path_domain),
        "merge" -> JsBoolean(msg.merge),
        "global_merge_vars" -> JsArray(msg.global_merge_vars.map(_.toJson)),
        "merge_vars" -> JsArray(msg.merge_vars.map(_.toJson)),
        "tags" -> JsArray(msg.tags.map(JsString(_))),
        "subaccount" -> JsString(msg.subaccount),
        "google_analytics_domains" -> JsArray(msg.google_analytics_domains.map(JsString(_))),
        "google_analytics_campaign" -> JsString(msg.google_analytics_campaign),
        "attachments" -> JsArray(msg.attachments.map(_.toJson)),
        "images" -> JsArray(msg.images.map(_.toJson))
      )

    def read(value: JsValue) = value.asJsObject.getFields("html",
      "text", "subject", "from_email", "from_name","to", "important", "track_opens", "track_clicks", "auto_text",
      "auto_html", "inline_css", "url_strip_qs", "preserve_recipients", "view_content_link", "bcc_address",
      "tracking_domain", "signing_domain", "return_path_domain","merge", "global_merge_vars", "merge_vars",
      "tags", "subaccount", "google_analytics_domains", "google_analytics_campaign", "attachments", "images") match {
      case a => Try {
        a.toList
        val message = new MSendMsg(
          a(0).convertTo[String],a(1).convertTo[String],a(2).convertTo[String],a(3).convertTo[String],a(4).convertTo[String],
          a(5).convertTo[List[MTo]],
          a(6).convertTo[Boolean],a(7).convertTo[Boolean],a(8).convertTo[Boolean],a(9).convertTo[Boolean],
          a(10).convertTo[Boolean],a(11).convertTo[Boolean],a(12).convertTo[Boolean],a(13).convertTo[Boolean],
          a(14).convertTo[Boolean],
          a(15).convertTo[String],a(16).convertTo[String],a(17).convertTo[String],a(18).convertTo[String],a(19).convertTo[Boolean],
          a(20).convertTo[List[MVars]],a(21).convertTo[List[MMergeVars]],a(22).convertTo[List[String]],
          a(23).convertTo[String],a(24).convertTo[List[String]],a(25).convertTo[String],
          a(26).convertTo[List[MAttachmetOrImage]],a(27).convertTo[List[MAttachmetOrImage]]
        )
        message
      }.recover{ case e: Exception => deserializationError("MSendMessage expected")}.get
    }
  }
}

trait MandrillResponse

trait MandrillRequest