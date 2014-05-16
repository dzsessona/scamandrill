package com.joypeg.scamandrill.models

case class MIspDnsResp(enabled: Boolean,
                       valid: Boolean,
                       error: String)

case class MIspWarmupResp(warming_up: Boolean,
                          start_at: String,
                          end_at: String)

case class MIspResponse(ip: String,
                        created_at: String,
                        pool: String,
                        domain: String,
                        custom_dns: MIspDnsResp,
                        warmup: MIspWarmupResp) extends MandrillResponse

case class MIspProvisionResp(requested_at: String) extends MandrillResponse

case class MIspDelete(ip: String, deleted: Boolean) extends MandrillResponse

case class MIspInfoPool(name: String,
                         created_at: String,
                         isp: List[MIspResponse]) extends MandrillResponse

case class MIspDnsResponse(valid: Boolean, error: String) extends MandrillResponse

case class MIspDeletePoolResponse(pool: String, deleted: Boolean) extends MandrillResponse