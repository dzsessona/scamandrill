package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._
import scala.util.{Failure, Try}


object MandrillBlockingClient extends MandrillClient {

  override def ping(apiKey: String = "Su9Twr4SZKU6aoWRQy4DIA"): Try[MPingResponse] = ???

  override def ping2(apiKey: String = "Su9Twr4SZKU6aoWRQy4DIA"): Try[MPingResponse] = ???
}
