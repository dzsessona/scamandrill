package com.joypeg.scamandrill.models

case class MSendersListResp(address: String,
                            created_at: String,
                            sent: Int,
                            hard_bounces: Int,
                            soft_bounces: Int,
                            rejects: Int,
                            complaints: Int,
                            unsubs: Int,
                            opens: Int,
                            clicks: Int,
                            unique_opens: Int,
                            unique_clicks: Int) extends MandrillResponse

case class MSendersDomainResponses(domain: String,
                                    created_at: String,
                                    last_tested_at: String,
                                    spf: MSendersDom,
                                    dkim: MSendersDom,
                                    verified_at: String,
                                    valid_signing: Boolean) extends MandrillResponse

case class MSendersDom(valid: Boolean,
                       valid_after: String,
                       error: String)

case class MSendersVerifyDomResp(status: String,
                                 domain: String,
                                 email: String) extends MandrillResponse

case class MSendersInfoResp(address: String,
                            created_at: String,
                            sent: Int,
                            hard_bounces: Int,
                            soft_bounces: Int,
                            rejects: Int,
                            complaints: Int,
                            unsubs: Int,
                            opens: Int,
                            clicks: Int,
                            stats: MSendersStats) extends MandrillResponse

case class MSendersStats(today: MStat,
                  last_7_days: MStat,
                  last_30_days: MStat,
                  last_60_days: MStat,
                  last_90_days: MStat)

case class MSenderTSResponse(time: String,
                             sent: Int,
                             hard_bounces: Int,
                             soft_bounces: Int,
                             rejects: Int,
                             complaints: Int,
                             unsubs: Int,
                             opens: Int,
                             unique_opens: Int,
                             clicks: Int,
                             unique_clicks: Int) extends MandrillResponse
