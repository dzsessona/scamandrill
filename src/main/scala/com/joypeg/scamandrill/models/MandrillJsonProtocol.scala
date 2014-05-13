package com.joypeg.scamandrill.models

import spray.json.DefaultJsonProtocol

object MandrillJsonProtocol extends DefaultJsonProtocol {
  implicit val pingJson = jsonFormat1(MPing2)
  implicit val pongJson = jsonFormat1(MPing2Response)
}

object MandrillEndpoints {
  def getMandrillEndpoint(operation: Any): String = operation match {
    case a: MPing2 => "/users/ping2.json"
    case _ => ""
  }
}