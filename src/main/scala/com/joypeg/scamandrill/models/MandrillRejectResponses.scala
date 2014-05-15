package com.joypeg.scamandrill.models

case class MRejectAddResponse(email: String, added: Boolean) extends MandrillResponse

case class MRejectDeleteResponse(email: String, deleted: Boolean, subaccount: String) extends MandrillResponse

case class MRejectListResponse(email: String,
                               reason: String,
                               detail: String,
                               created_at: String,
                               last_event_at: String,
                               expires_at: String,
                               expired: Boolean,
                               subaccount: String,
                               sender: MSenderDataResponse) extends MandrillResponse
