package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._
import scala.util.Try
import scala.concurrent._
import scala.concurrent.duration._


object MandrillBlockingClient extends MandrillClient {

  override def ping(ping: MPing): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.ping(ping), 5 seconds)
  }

  override def ping2(ping: MPing): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.ping2(ping), 5 seconds)
  }

  override def senders(ping: MPing): Try[List[MSenderDataResponse]] = Try {
    Await.result(MandrillAsyncClient.senders(ping), 5 seconds)
  }

  override def info(ping: MPing): Try[MInfoResponse] = Try {
    Await.result(MandrillAsyncClient.info(ping), 5 seconds)
  }

  override def send(msg: MSendMessage): Try[List[MSendResponse]] = Try {
    Await.result(MandrillAsyncClient.send(msg), 5 seconds)
  }

  override def sendTemplate(msg: MSendTemplateMessage): Try[List[MSendResponse]] = Try {
    Await.result(MandrillAsyncClient.sendTemplate(msg), 5 seconds)
  }

  override def search(q: MSearch): Try[List[MSearchResponse]] = Try {
    Await.result(MandrillAsyncClient.search(q), 5 seconds)
  }

  override def searchTimeSeries(q: MSearchTimeSeries): Try[List[MSearchTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.searchTimeSeries(q), 5 seconds)
  }

  override def messageInfo(q: MMessageInfo): Try[MMessageInfoResponse] = Try {
    Await.result(MandrillAsyncClient.messageInfo(q), 5 seconds)
  }

  override def content(q: MMessageInfo): Try[MContentResponse] = Try {
    Await.result(MandrillAsyncClient.content(q), 5 seconds)
  }

  override def shutdownSystem(): Unit = MandrillAsyncClient.shutdownSystem()
}
