package com.joypeg.scamandrill.models

case class MRejectAddResponse(email: String, added: Boolean) extends MandrillResponse

case class MRejectDeleteResponse(email: String, deleted: Boolean, subaccount: Option[String]) extends MandrillResponse

//expires at = false???
case class MRejectListResponse(email: String,
                               reason: String,
                               detail: Option[String],
                               created_at: String,
                               last_event_at: String,
                               expires_at: Boolean,
                               expired: Boolean,
                               subaccount: Option[String],
                               sender: Option[MSenderDataResponse]) extends MandrillResponse


//{"reason":"custom",
//  "detail":null,
//  "last_event_at":"2014-05-17 14:46:21",
//  "email":"reject@example.com",
//  "created_at":"2014-05-17 14:46:21",
//  "expires_at":false,
//  "expired":false,
//  "subaccount":null,
//  "sender":null}
