package com.joypeg.scamandrill.models


case class MInboundDomainResponse(domain: String,
                                  created_at: String,
                                  valid_mx: Boolean) extends MandrillResponse

case class MInboundRouteResponse(id: String,
                                 pattern: String,
                                 url: Boolean) extends MandrillResponse

case class MInboundRawResponse(email: String,
                               pattern: String,
                               url: Boolean) extends MandrillResponse
