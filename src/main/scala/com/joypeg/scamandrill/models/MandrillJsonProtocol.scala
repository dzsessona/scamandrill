package com.joypeg.scamandrill.models

import spray.json.DefaultJsonProtocol

object MandrillJsonProtocol extends DefaultJsonProtocol {
  implicit val pingJson = jsonFormat1(MPing)
  implicit val pongJson = jsonFormat1(MPingResponse)
}

object MandrillEndpoints {
  def getMandrillEndpoint(operation: Any): String = operation match {
    case a: MPing => "/users/ping2.json"
    case _ => ""
  }
}