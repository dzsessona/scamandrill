package com.joypeg.scamandrill.models

case class MTemplateRenderResponse(html: Option[String]) extends MandrillResponse

case class MTemplateAddResponses(slug: String,
                                 name: String,
                                 labels: List[String],
                                 code: String,
                                 subject: String,
                                 from_email: Option[String],
                                 from_name: Option[String],
                                 text: String,
                                 publish_name: String,
                                 publish_code: Option[String],
                                 publish_subject: Option[String],
                                 publish_from_email: Option[String],
                                 publish_from_name: Option[String],
                                 publish_text: Option[String],
                                 published_at: Option[String],
                                 created_at: String,
                                 updated_at: String) extends MandrillResponse



