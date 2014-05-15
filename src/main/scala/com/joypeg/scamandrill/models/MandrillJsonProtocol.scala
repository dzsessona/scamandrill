package com.joypeg.scamandrill.models

import spray.json._
import scala.util.Try

object MandrillJsonProtocol extends DefaultJsonProtocol {
  implicit val MPingJson              = jsonFormat1(MPing)
  implicit val MPingResponseJson      = jsonFormat1(MPingResponse)
  implicit val MSenderDataResponseJson= jsonFormat12(MSenderDataResponse)
  implicit val MStatJson              = jsonFormat10(MStat)
  implicit val MStatsJson             = jsonFormat6(MStats)
  implicit val MInfoResponseJson      = jsonFormat7(MInfoResponse)
  implicit val MAttachmetOrImageJson  = jsonFormat3(MAttachmetOrImage)
  implicit val MVarsJson              = jsonFormat2(MVars)
  implicit val MMergeVarsJson         = jsonFormat2(MMergeVars)
  implicit val MToJson                = jsonFormat3(MTo)
  implicit val MSendResponseJson      = jsonFormat4(MSendResponse)
  implicit object MSendMsgJsonFormat extends RootJsonFormat[MSendMsg]{
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
        "subaccount" -> (msg.subaccount match {
          case Some(value) => JsString(value)
          case _ => JsNull
        }),
        "google_analytics_domains" -> JsArray(msg.google_analytics_domains.map(JsString(_))),
        "google_analytics_campaign" -> (msg.google_analytics_campaign match {
          case Some(value) => JsString(value)
          case _ => JsNull
        }),
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
          a(23).convertTo[Option[String]],a(24).convertTo[List[String]],a(25).convertTo[Option[String]],
          a(26).convertTo[List[MAttachmetOrImage]],a(27).convertTo[List[MAttachmetOrImage]]
        )
        message
      }.recover{ case e: Exception => deserializationError("MSendMessage expected")}.get
    }
  }
  implicit val MSendMessageJson           = jsonFormat5(MSendMessage)
  implicit val MSendTemplateMessageJson   = jsonFormat7(MSendTemplateMessage)
  implicit val MSearchJson                = jsonFormat8(MSearch)
  implicit val MClickDetailsJson          = jsonFormat5(MClickDetails)
  implicit val MOpenDetailJson            = jsonFormat4(MOpenDetail)
  implicit val MSearchResponseJson        = jsonFormat12(MSearchResponse)
  implicit val MSearchTimeSeriesJson      = jsonFormat6(MSearchTimeSeries)
  implicit val MSearchTimeSeriesResponseJ = jsonFormat11(MTimeSeriesResponse)
  implicit val MMessageInfoJson           = jsonFormat2(MMessageInfo)
  implicit val MSmtpEventJson             = jsonFormat3(MSmtpEvent)
  implicit val MMessageInfoResponseJson   = jsonFormat13(MMessageInfoResponse)
  implicit val MToResponseJson            = jsonFormat2(MToResponse)
  implicit val MContentResponseJson       = jsonFormat10(MContentResponse)
  implicit val MParseJson                 = jsonFormat2(MParse)
  implicit val MParseResponseJson         = jsonFormat8(MParseResponse)
  implicit val MSendRawJson               = jsonFormat9(MSendRaw)
  implicit val MScheduleResponseJson      = jsonFormat6(MScheduleResponse)
  implicit val MListScheduleJson          = jsonFormat2(MListSchedule)
  implicit val MCancelScheduleJson        = jsonFormat2(MCancelSchedule)
  implicit val MReScheduleJson            = jsonFormat3(MReSchedule)
  implicit val MTagJson                   = jsonFormat1(MTag)
  implicit val MTagRequestJson            = jsonFormat2(MTagRequest)
  implicit val MTagResponseJson           = jsonFormat12(MTagResponse)
  implicit val MTagInfoResponseJson       = jsonFormat10(MTagInfoResponse)
  implicit val MRejectAddJson             = jsonFormat4(MRejectAdd)
  implicit val MRejectListJson            = jsonFormat4(MRejectList)
  implicit val MRejectDeleteJson          = jsonFormat3(MRejectDelete)
  implicit val MRejectAddResponseJson     = jsonFormat2(MRejectAddResponse)
  implicit val MRejectDeleteResponseJson  = jsonFormat3(MRejectDeleteResponse)
  implicit val MRejectListResponseJson    = jsonFormat9(MRejectListResponse)
  implicit val MWhitelistJson             = jsonFormat2(MWhitelist)
  implicit val MWhitelistAddResponseJson  = jsonFormat2(MWhitelistAddResponse)
  implicit val MWhitelistListResponseJson = jsonFormat3(MWhitelistListResponse)
  implicit val MWhitelistDeleteResponseJ  = jsonFormat2(MWhitelistDeleteResponse)
  implicit val MSendersjson               = jsonFormat1(MSenders)
  implicit val MSendersDomj               = jsonFormat3(MSendersDom)
  implicit val MSenderDomainjson          = jsonFormat2(MSenderDomain)
  implicit val MSenderVerifyDomainjson    = jsonFormat3(MSenderVerifyDomain)
  implicit val MSenderAddressjson         = jsonFormat2(MSenderAddress)
  implicit val MSendersStatsj             = jsonFormat5(MSendersStats)
  implicit val MSendersDomainResponsesj   = jsonFormat7(MSendersDomainResponses)
  implicit val MSendersVerifyDomRespj     = jsonFormat3(MSendersVerifyDomResp)
  implicit val MSendersInfoRespj          = jsonFormat11(MSendersInfoResp)
  implicit val MSenderTSResponsej         = jsonFormat11(MSenderTSResponse)
  implicit val MSendersListRespj          = jsonFormat12(MSendersListResp)
}

trait MandrillResponse

trait MandrillRequest