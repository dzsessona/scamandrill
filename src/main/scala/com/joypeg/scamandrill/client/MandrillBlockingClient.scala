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

  override def searchTimeSeries(q: MSearchTimeSeries): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.searchTimeSeries(q), 5 seconds)
  }

  override def messageInfo(q: MMessageInfo): Try[MMessageInfoResponse] = Try {
    Await.result(MandrillAsyncClient.messageInfo(q), 5 seconds)
  }

  override def content(q: MMessageInfo): Try[MContentResponse] = Try {
    Await.result(MandrillAsyncClient.content(q), 5 seconds)
  }

  override def parse(raw: MParse): Try[MParseResponse] = Try {
    Await.result(MandrillAsyncClient.parse(raw), 5 seconds)
  }

  override def sendRaw(raw: MSendRaw): Try[List[MSendResponse]] = Try{
    Await.result(MandrillAsyncClient.sendRaw(raw), 5 seconds)
  }

  override def listSchedule(sc: MListSchedule): Try[List[MScheduleResponse]] = Try {
    Await.result(MandrillAsyncClient.listSchedule(sc), 5 seconds)
  }

  override def cancelSchedule(sc: MCancelSchedule): Try[MScheduleResponse] = Try {
    Await.result(MandrillAsyncClient.cancelSchedule(sc), 5 seconds)
  }

  override def reSchedule(sc: MReSchedule): Try[MScheduleResponse] = Try {
    Await.result(MandrillAsyncClient.reSchedule(sc), 5 seconds)
  }

  override def tagList(tag: MTag): Try[List[MTagResponse]] = Try {
    Await.result(MandrillAsyncClient.tagList(tag), 5 seconds)
  }

  override def tagDelete(tag: MTagRequest): Try[MTagResponse] = Try {
    Await.result(MandrillAsyncClient.tagDelete(tag), 5 seconds)
  }

  override def tagInfo(tag: MTagRequest): Try[MTagInfoResponse] = Try {
    Await.result(MandrillAsyncClient.tagInfo(tag), 5 seconds)
  }

  override def tagTimeSeries(tag: MTagRequest): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.tagTimeSeries(tag), 5 seconds)
  }

  override def tagAllTimeSeries(tag: MTag): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.tagAllTimeSeries(tag), 5 seconds)
  }

  override def shutdownSystem(): Unit = MandrillAsyncClient.shutdownSystem()
}
