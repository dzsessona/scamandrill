package com.joypeg.scamandrill.models

//TODO: not whether as in documentation
case class MWhitelistAddResponse(email: String, added: Boolean) extends MandrillResponse

case class MWhitelistListResponse(email: String,
                                  detail: String,
                                  created_at: String) extends MandrillResponse

case class MWhitelistDeleteResponse(email: String, deleted: Boolean) extends MandrillResponse
