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

  override def senders(apiKey: String = configApiKey): Future[List[MSenderDataResponse]] = {
    executeQuery[List[MSenderDataResponse]](Endpoints.senders.endpoint, marshal(MPing(apiKey)))(unmarshal[List[MSenderDataResponse]])
  }

  override def info(apiKey: String = configApiKey): Future[MInfoResponse] = {
    executeQuery[MInfoResponse](Endpoints.info.endpoint, marshal(MPing(apiKey)))(unmarshal[MInfoResponse])
  }

  override def send(msg: MSendMsg): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.send.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def shutdownSystem(): Unit = shutdown()

}
