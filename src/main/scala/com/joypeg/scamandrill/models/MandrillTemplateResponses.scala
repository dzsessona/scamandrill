package com.joypeg.scamandrill.models

/**
 * The rendered template
 * @param html - the rendered HTML as a string
 */
case class MTemplateRenderResponse(html: Option[String]) extends MandrillResponse

/**
 * Information about the template
 * @param slug - the immutable unique code name of the template
 * @param name - the name of the template
 * @param labels - the list of labels applied to the template
 * @param code - the full HTML code of the template, with mc:edit attributes marking the editable elements - draft version
 * @param subject - the subject line of the template, if provided - draft version
 * @param from_email - the default sender address for the template, if provided - draft version
 * @param from_name - the default sender from name for the template, if provided - draft version
 * @param text - the default text part of messages sent with the template, if provided - draft version
 * @param publish_name - the same as the template name - kept as a separate field for backwards compatibility
 * @param publish_code - the full HTML code of the template, with mc:edit attributes marking the editable elements that are available as published, if it has been published
 * @param publish_subject - the subject line of the template, if provided
 * @param publish_from_email - the default sender address for the template, if provided
 * @param publish_from_name - the default sender from name for the template, if provided
 * @param publish_text - the default text part of messages sent with the template, if provided
 * @param published_at - the date and time the template was last published as a UTC string in YYYY-MM-DD HH:MM:SS format, or null if it has not been published
 * @param created_at - the date and time the template was first created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param updated_at - the date and time the template was last modified as a UTC string in YYYY-MM-DD HH:MM:SS format
 */
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



