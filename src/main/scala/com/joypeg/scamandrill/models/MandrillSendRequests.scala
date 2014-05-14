package com.joypeg.scamandrill.models

case class MSendMessage(key: String,
                        message: MSendMsg,
                        async: Boolean,
                        ip_pool: String,
                        send_at: String) extends MandrillRequest

//metadata and headers and recipient_metadata
class MSendMsg( val html: String,
                val text: String,
                val subject: String,
                val from_email: String,
                val from_name: String,
                val to: List[MTo],
                val important: Boolean,
                val track_opens: Boolean,
                val track_clicks: Boolean,
                val auto_text: Boolean,
                val auto_html: Boolean,
                val inline_css: Boolean,
                val url_strip_qs: Boolean,
                val preserve_recipients: Boolean,
                val view_content_link: Boolean,
                val bcc_address: String,
                val tracking_domain: String,
                val signing_domain: String,
                val return_path_domain: String,
                val merge: Boolean,
                val global_merge_vars: List[MVars],
                val merge_vars: List[MMergeVars],
                val tags: List[String],
                val subaccount: String,
                val google_analytics_domains: List[String],
                val google_analytics_campaign: String,
                val attachments: List[MAttachmetOrImage],
                val images: List[MAttachmetOrImage]){

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

case class MTo(email: String, name: String, `type`: String)