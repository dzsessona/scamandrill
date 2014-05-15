package com.joypeg.scamandrill.models

case class MSendMessage(key: String = DefaultConfig.defaultKeyFromConfig,
                        message: MSendMsg,
                        async: Boolean = false,
                        ip_pool: Option[String] = None,
                        send_at: Option[String] = None) extends MandrillRequest

case class MSendTemplateMessage(key: String = DefaultConfig.defaultKeyFromConfig,
                                template_name: String,
                                template_content: List[MVars],
                                message: MSendMsg,
                                async: Boolean = false,
                                ip_pool: Option[String] = None,
                                send_at: Option[String] = None) extends MandrillRequest

//TODO: metadata and headers and recipient_metadata
class MSendMsg( val html: String,
                val text: String,
                val subject: String,
                val from_email: String,
                val from_name: String,
                val to: List[MTo],
                val important: Boolean = false,
                val track_opens: Boolean = false,
                val track_clicks: Boolean = false,
                val auto_text: Boolean = false,
                val auto_html: Boolean = false,
                val inline_css: Boolean = false,
                val url_strip_qs: Boolean = false,
                val preserve_recipients: Boolean = false,
                val view_content_link: Boolean = false,
                val bcc_address: String,
                val tracking_domain: String,
                val signing_domain: String,
                val return_path_domain: String,
                val merge: Boolean = false,
                val global_merge_vars: List[MVars] = List.empty,
                val merge_vars: List[MMergeVars] = List.empty,
                val tags: List[String] = List.empty,
                val subaccount: Option[String] = None,
                val google_analytics_domains: List[String] = List.empty,
                val google_analytics_campaign: Option[String] = None,
                val attachments: List[MAttachmetOrImage] = List.empty,
                val images: List[MAttachmetOrImage] = List.empty){

  override def equals(other: Any) = other match {
    case o: MSendMsg =>
      o.html == this.html &&
      o.html == this.html &&
      o.text == this.text &&
      o.subject == this.subject &&
      o.from_email == this.from_email &&
      o.from_name == this.from_name &&
      o.to == this.to &&
      o.important == this.important &&
      o.track_opens == this.track_opens &&
      o.track_clicks == this.track_clicks &&
      o.auto_text == this.auto_text &&
      o.inline_css == this.inline_css &&
      o.url_strip_qs == this.url_strip_qs &&
      o.preserve_recipients == this.preserve_recipients &&
      o.view_content_link == this.view_content_link &&
      o.bcc_address == this.bcc_address &&
      o.tracking_domain == this.tracking_domain &&
      o.signing_domain == this.signing_domain &&
      o.return_path_domain == this.return_path_domain &&
      o.merge == this.merge &&
      o.global_merge_vars == this.global_merge_vars &&
      o.merge_vars == this.merge_vars &&
      o.tags == this.tags &&
      o.subaccount == this.subaccount &&
      o.google_analytics_domains == this.google_analytics_domains &&
      o.google_analytics_campaign == this.google_analytics_campaign &&
      o.attachments == this.attachments &&
      o.images == this.images
    case _ => false
  }
}

case class MAttachmetOrImage(`type`: String, name: String, content: String)

case class MMergeVars(rcpt: String, vars: List[MVars])

case class MVars(name: String, content: String)

case class MTo(email: String, name: Option[String] = None, `type`: String = "to")

case class MSearch(key: String = DefaultConfig.defaultKeyFromConfig,
                   query: String,
                   date_from: String,
                   date_to: String,
                   tags: List[String] = List.empty,
                   senders: List[String] = List.empty,
                   api_keys: List[String] = List.empty,
                   limit: Int = 100) extends MandrillRequest

case class MSearchTimeSeries(key: String = DefaultConfig.defaultKeyFromConfig,
                             query: String,
                             date_from: String,
                             date_to: String,
                             tags: List[String] = List.empty,
                             senders: List[String] = List.empty) extends MandrillRequest

case class MMessageInfo(key: String = DefaultConfig.defaultKeyFromConfig, id: String) extends MandrillRequest

case class MParse(key: String = DefaultConfig.defaultKeyFromConfig, raw_message: String) extends MandrillRequest

case class MSendRaw(key: String = DefaultConfig.defaultKeyFromConfig,
                    raw_message: String,
                    from_email: Option[String] = None,
                    from_name: Option[String] = None,
                    to: Option[List[String]] = None,
                    async: Boolean = false,
                    ip_pool: Option[String] = None,
                    send_at: Option[String] = None,
                    return_path_domain: Option[String] = None)

case class MListSchedule(key: String = DefaultConfig.defaultKeyFromConfig, to: String) extends MandrillRequest
case class MCancelSchedule(key: String = DefaultConfig.defaultKeyFromConfig, id: String) extends MandrillRequest
case class MReSchedule(key: String = DefaultConfig.defaultKeyFromConfig, id: String,  send_at: String) extends MandrillRequest


