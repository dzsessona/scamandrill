package com.joypeg.scamandrill.client

import scala.concurrent.Future
import spray.httpx.marshalling._
import spray.client.pipelining._
import com.joypeg.scamandrill.models._

object MandrillAsyncClient extends MandrillClient with ScamandrillSendReceive {

  import spray.httpx.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  override def ping(apiKey: String = configApiKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping.endpoint, marshal(MPing(apiKey)))(unmarshal[String].andThen(MPingResponse))
  }

  override def ping2(apiKey: String = configApiKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping2.endpoint, marshal(MPing(apiKey)))(unmarshal[MPingResponse])
  }

  override def shutdownSystem(): Unit = shutdown()

}
