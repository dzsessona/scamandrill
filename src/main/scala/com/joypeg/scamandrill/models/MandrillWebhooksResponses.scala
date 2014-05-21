package com.joypeg.scamandrill.models

/**
 * Information about the webhook
 * @param id - a unique integer indentifier for the webhook
 * @param url - The URL that the event data will be posted to
 * @param description - a description of the webhook
 * @param auth_key - the key used to requests for this webhook
 * @param created_at - the date and time that the webhook was created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param last_sent_at - the date and time that the webhook last successfully received events as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param batches_sent - the number of event batches that have ever been sent to this webhook
 * @param events_sent - the total number of events that have ever been sent to this webhook
 * @param last_error - if we've ever gotten an error trying to post to this webhook, the last error that we've seen
 */
case class MWebhooksResponse(id: Int,
                             url: String,
                             description: String,
                             auth_key: List[String],
                             created_at: String,
                             last_sent_at: String,
                             batches_sent: Int,
                             events_sent: Int,
                             last_error: String) extends MandrillResponse

