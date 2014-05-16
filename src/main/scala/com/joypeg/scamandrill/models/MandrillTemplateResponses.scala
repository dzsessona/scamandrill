package com.joypeg.scamandrill.models

case class MTemplateRenderResponse(html: String) extends MandrillResponse

case class MTemplateAddResponses(slug: String,
                                 name: String,
                                 labels: List[String],
                                 code: String,
                                 subject: String,
                                 from_email: String,
                                 from_name: String,
                                 text: String,
                                 publish_name: String,
                                 publish_code: String,
                                 publish_subject: String,
                                 publish_from_email: String,
                                 publish_from_name: String,
                                 publish_text: String,
                                 published_at: String,
                                 created_at: String,
                                 updated_at: String) extends MandrillResponse

