package com.joypeg.scamandrill.models

//TODO: metadata
case class MSearchResponse(ts: Int,
                           _id: String,
                           sender: String,
                           template: Option[String],
                           subject: String,
                           email:String,
                           tags: List[String],
                           opens: Int,
                           opens_detail: List[MOpenDetail],
                           clicks: Int,
                           clicks_detail: List[MClickDetails],
                           state: String)

case class MMessageInfoResponse(ts: Int,
                               _id: String,
                               sender: String,
                               template: Option[String],
                               subject: String,
                               email:String,
                               tags: List[String],
                               opens: Int,
                               opens_detail: List[MOpenDetail],
                               clicks: Int,
                               clicks_detail: List[MClickDetails],
                               state: String,
                               smtp_events: List[MSmtpEvent])

case class MSmtpEvent(ts: Int, `type`: String, diag: String)

case class MTimeSeriesResponse(time: String,
                               sent: Int,
                               hard_bounces: Int,
                               soft_bounces: Int,
                               rejects: Int,
                               complaints: Int,
                               unsubs: Int,
                               opens: Int,
                               unique_opens: Int,
                               clicks: Int,
                               unique_clicks: Int)

case class MOpenDetail(ts: Int, ip: String, location: String, ua: String)

case class MClickDetails(ts: Int, ip: String, location: String, ua: String, url: String)

case class MSendResponse(_id: String,
                        email: String,
                        status: String,
                        reject_reason: Option[String]) extends MandrillResponse

//TODO: headers
case class MContentResponse(ts: Int,
                            _id: Int,
                             from_email: String,
                             from_name: String,
                             subject: String,
                             to: MToResponse,
                             tags: List[String],
                             text: String,
                             html: String,
                             attachemnt: List[MAttachmetOrImage])

case class MToResponse(email: String, name: String)

//TODO: headers
case class MParseResponse(subject: Option[String],
                          from_email: Option[String],
                          from_name: Option[String],
                          to: Option[List[MToResponse]],
                          text: Option[String],
                          html: Option[String],
                          attachments: Option[List[MAttachmetOrImage]],
                          images: Option[List[MAttachmetOrImage]])

case class MScheduleResponse(_id: String,
                             created_at: String,
                             send_at: String,
                             from_email: String,
                             to: String,
                             subject: String)



