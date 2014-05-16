package com.joypeg.scamandrill.models

case class MWebhooksResponse(id: Int,
                             url: String,
                             description: String,
                             auth_key: List[String],
                             created_at: String,
                             last_sent_at: String,
                             batches_sent: Int,
                             events_sent: Int,
                             last_error: String) extends MandrillResponse

