package com.joypeg.scamandrill.client

import scala.concurrent.Future
import spray.httpx.marshalling._
import spray.client.pipelining._
import com.joypeg.scamandrill.models._

object MandrillAsyncClient extends MandrillClient with ScamandrillSendReceive{

  import spray.httpx.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  override def ping2(apiKey: String = "Su9Twr4SZKU6aoWRQy4DIA"): Future[MPing2Response] = {
    executeQuery[MPing2Response](Endpoints.ping2.endpoint, marshal(MPing2(apiKey)))(unmarshal[MPing2Response])
  }
}
