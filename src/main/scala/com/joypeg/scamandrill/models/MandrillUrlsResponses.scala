package com.joypeg.scamandrill.models


case class MUrlCname(valid: Boolean,
                     valid_after: Option[String],
                     error: Option[String])

case class MUrlResponse(url: String,
                        sent: Int,
                        clicks: Int,
                        unique_clicks: Int) extends MandrillResponse

case class MUrlTimeResponse(time: String,
                            sent: Int,
                            clicks: Int,
                            unique_clicks: Int) extends MandrillResponse

case class MUrlDomainResponse(domain: String,
                              created_at: String,
                              last_tested_at: String,
                              valid_tracking: Boolean,
                              cname: MUrlCname) extends MandrillResponse



