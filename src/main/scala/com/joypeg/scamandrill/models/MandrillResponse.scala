package com.joypeg.scamandrill.models

trait MandrillResponse

case class MPingResponse(PING: String) extends MandrillResponse
