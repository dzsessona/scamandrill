package com.joypeg.scamandrill.client

import scala.concurrent.Future
import spray.httpx.marshalling._
import spray.client.pipelining._
import com.joypeg.scamandrill.models._

object MandrillAsyncClient extends MandrillClient with ScamandrillSendReceive {

  import spray.httpx.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  override def ping(ping: MPing): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping.endpoint, marshal(ping))(unmarshal[String].andThen(MPingResponse))
  }

  override def ping2(ping: MPing): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping2.endpoint, marshal(ping))(unmarshal[MPingResponse])
  }

  override def senders(ping: MPing): Future[List[MSenderDataResponse]] = {
    executeQuery[List[MSenderDataResponse]](Endpoints.senders.endpoint, marshal(ping))(unmarshal[List[MSenderDataResponse]])
  }

  override def info(ping: MPing): Future[MInfoResponse] = {
    executeQuery[MInfoResponse](Endpoints.info.endpoint, marshal(ping))(unmarshal[MInfoResponse])
  }

  override def send(msg: MSendMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.send.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def sendTemplate(msg: MSendTemplateMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.sendTemplate.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def search(q: MSearch): Future[List[MSearchResponse]] = {
    executeQuery[List[MSearchResponse]](Endpoints.search.endpoint, marshal(q))(unmarshal[List[MSearchResponse]])
  }

  override def searchTimeSeries(q: MSearchTimeSeries): Future[List[MSearchTimeSeriesResponse]] = {
    executeQuery[List[MSearchTimeSeriesResponse]](Endpoints.searchTimeS.endpoint, marshal(q))(unmarshal[List[MSearchTimeSeriesResponse]])
  }

  override def messageInfo(q: MMessageInfo): Future[MMessageInfoResponse] = {
    executeQuery[MMessageInfoResponse](Endpoints.msginfo.endpoint, marshal(q))(unmarshal[MMessageInfoResponse])
  }

  override def content(q: MMessageInfo): Future[MContentResponse] = {
    executeQuery[MContentResponse](Endpoints.content.endpoint, marshal(q))(unmarshal[MContentResponse])
  }

  override def shutdownSystem(): Unit = shutdown()

}
