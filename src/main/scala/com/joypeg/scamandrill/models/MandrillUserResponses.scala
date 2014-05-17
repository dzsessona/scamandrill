package com.joypeg.scamandrill.models

case class MPingResponse(PING: String) extends MandrillResponse

case class MSenderDataResponse(address: String,
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

case class MInfoResponse( username: String,
                          created_at: String,
                          public_id: String,
                          reputation: Int,
                          hourly_quota: Int,
                          backlog: Int,
                          stats: MStats) extends MandrillResponse

case class MStats(today: MStat,
                  last_7_days: MStat,
                  last_30_days: MStat,
                  last_60_days: MStat,
                  last_90_days: MStat,
                  all_time: Option[MStat])


case class MStat(sent: Int,
                  hard_bounces: Int,
                  soft_bounces: Int,
                  rejects: Int,
                  complaints: Int,
                  unsubs: Int,
                  opens: Int,
                  unique_opens: Int,
                  clicks: Int,
                  unique_clicks: Int)



