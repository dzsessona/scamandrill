package com.joypeg.scamandrill.models


case class MWhitelistAddResponse(email: String, whether: Boolean) extends MandrillResponse

case class MWhitelistListResponse(email: String,
                                  detail: String,
                                  created_at: String) extends MandrillResponse

case class MWhitelistDeleteResponse(email: String, deleted: Boolean) extends MandrillResponse
