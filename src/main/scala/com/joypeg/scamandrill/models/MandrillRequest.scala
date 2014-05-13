package com.joypeg.scamandrill.models

trait MandrillRequest

case class MPing(key: String) extends MandrillRequest
