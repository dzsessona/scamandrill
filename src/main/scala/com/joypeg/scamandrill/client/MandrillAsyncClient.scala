package com.joypeg.scamandrill.client

import scala.concurrent.Future
import spray.httpx.marshalling._
import spray.client.pipelining._
import com.joypeg.scamandrill.models._

object MandrillAsyncClient extends MandrillClient with ScamandrillSendReceive {

  import spray.httpx.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

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

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

  override def send(msg: MSendMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.send.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def sendTemplate(msg: MSendTemplateMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.sendTemplate.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def search(q: MSearch): Future[List[MSearchResponse]] = {
    executeQuery[List[MSearchResponse]](Endpoints.search.endpoint, marshal(q))(unmarshal[List[MSearchResponse]])
  }

  override def searchTimeSeries(q: MSearchTimeSeries): Future[List[MTimeSeriesResponse]] = {
    executeQuery[List[MTimeSeriesResponse]](Endpoints.searchTimeS.endpoint, marshal(q))(unmarshal[List[MTimeSeriesResponse]])
  }

  override def messageInfo(q: MMessageInfo): Future[MMessageInfoResponse] = {
    executeQuery[MMessageInfoResponse](Endpoints.msginfo.endpoint, marshal(q))(unmarshal[MMessageInfoResponse])
  }

  override def content(q: MMessageInfo): Future[MContentResponse] = {
    executeQuery[MContentResponse](Endpoints.content.endpoint, marshal(q))(unmarshal[MContentResponse])
  }

  override def parse(raw: MParse): Future[MParseResponse] = {
    executeQuery[MParseResponse](Endpoints.parse.endpoint, marshal(raw))(unmarshal[MParseResponse])
  }

  override def sendRaw(raw: MSendRaw): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.sendraw.endpoint, marshal(raw))(unmarshal[List[MSendResponse]])
  }

  override def listSchedule(sc: MListSchedule): Future[List[MScheduleResponse]] = {
    executeQuery[List[MScheduleResponse]](Endpoints.listSchedule.endpoint, marshal(sc))(unmarshal[List[MScheduleResponse]])
  }

  override def cancelSchedule(sc: MCancelSchedule): Future[MScheduleResponse] = {
    executeQuery[MScheduleResponse](Endpoints.cancelSchedule.endpoint, marshal(sc))(unmarshal[MScheduleResponse])
  }

  override def reSchedule(sc: MReSchedule): Future[MScheduleResponse] = {
    executeQuery[MScheduleResponse](Endpoints.reschedule.endpoint, marshal(sc))(unmarshal[MScheduleResponse])
  }

  ////////////////////////////////////////////////////////////
  //TAGS calls https://mandrillapp.com/api/docs/tags.JSON.html
  ////////////////////////////////////////////////////////////

  override def tagList(tag: MTag): Future[List[MTagResponse]] = {
    executeQuery[List[MTagResponse]](Endpoints.taglist.endpoint, marshal(tag))(unmarshal[List[MTagResponse]])
  }

  override def tagDelete(tag: MTagRequest): Future[MTagResponse] = {
    executeQuery[MTagResponse](Endpoints.tagdelete.endpoint, marshal(tag))(unmarshal[MTagResponse])
  }

  override def tagInfo(tag: MTagRequest): Future[MTagInfoResponse] = {
    executeQuery[MTagInfoResponse](Endpoints.taginfo.endpoint, marshal(tag))(unmarshal[MTagInfoResponse])
  }

  override def tagTimeSeries(tag: MTagRequest): Future[List[MTimeSeriesResponse]] = {
    executeQuery[List[MTimeSeriesResponse]](Endpoints.tagtimeseries.endpoint, marshal(tag))(unmarshal[List[MTimeSeriesResponse]])
  }

  override def tagAllTimeSeries(tag: MTag): Future[List[MTimeSeriesResponse]] = {
    executeQuery[List[MTimeSeriesResponse]](Endpoints.tagalltime.endpoint, marshal(tag))(unmarshal[List[MTimeSeriesResponse]])
  }

  /////////////////////////////////////////////////////////////////
  //REJECT calls https://mandrillapp.com/api/docs/rejects.JSON.html
  /////////////////////////////////////////////////////////////////

  override def rejectAdd(reject: MRejectAdd): Future[MRejectAddResponse] = {
    executeQuery[MRejectAddResponse](Endpoints.rejadd.endpoint, marshal(reject))(unmarshal[MRejectAddResponse])
  }

  override def rejectDelete(reject: MRejectDeleteResponse): Future[MRejectDeleteResponse] = {
    executeQuery[MRejectDeleteResponse](Endpoints.regdelete.endpoint, marshal(reject))(unmarshal[MRejectDeleteResponse])
  }

  override def rejectList(reject: MRejectListResponse): Future[MRejectListResponse] = {
    executeQuery[MRejectListResponse](Endpoints.rejlist.endpoint, marshal(reject))(unmarshal[MRejectListResponse])
  }

  ///////////////////////////////////////////////////////////////////////
  //WHITELIST calls https://mandrillapp.com/api/docs/whitelists.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def whitelistAdd(mail: MWhitelist): Future[MWhitelistAddResponse] = {
    executeQuery[MWhitelistAddResponse](Endpoints.wlistadd.endpoint, marshal(mail))(unmarshal[MWhitelistAddResponse])
  }

  override def whitelistDelete(mail: MWhitelist): Future[MWhitelistDeleteResponse] = {
    executeQuery[MWhitelistDeleteResponse](Endpoints.wlistdelete.endpoint, marshal(mail))(unmarshal[MWhitelistDeleteResponse])
  }

  override def whitelistList(mail: MWhitelist): Future[MWhitelistListResponse] = {
    executeQuery[MWhitelistListResponse](Endpoints.wlistlist.endpoint, marshal(mail))(unmarshal[MWhitelistListResponse])
  }

  ///////////////////////////////////////////////////////////////////////
  //SENDERS calls https://mandrillapp.com/api/docs/senders.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def sendersList(snd: MSenders): Future[List[MSendersListResp]] = {
    executeQuery[List[MSendersListResp]](Endpoints.senderslist.endpoint, marshal(snd))(unmarshal[List[MSendersListResp]])
  }

  override def sendersDomains(snd: MSenders): Future[List[MSendersDomainResponses]] = {
    executeQuery[List[MSendersDomainResponses]](Endpoints.sendersdomains.endpoint, marshal(snd))(unmarshal[List[MSendersDomainResponses]])
  }

  override def sendersAddDomain(snd: MSenderDomain): Future[MSendersDomainResponses] = {
    executeQuery[MSendersDomainResponses](Endpoints.sendersadddom.endpoint, marshal(snd))(unmarshal[MSendersDomainResponses])
  }

  override def sendersCheckDomain(snd: MSenders): Future[MSendersDomainResponses] = {
    executeQuery[MSendersDomainResponses](Endpoints.senderschkdom.endpoint, marshal(snd))(unmarshal[MSendersDomainResponses])
  }

  override def sendersVerifyDomain(snd: MSenderVerifyDomain): Future[MSendersVerifyDomResp] = {
    executeQuery[MSendersVerifyDomResp](Endpoints.sendersverdom.endpoint, marshal(snd))(unmarshal[MSendersVerifyDomResp])
  }

  override def sendersInfo(snd: MSenderAddress): Future[MSendersInfoResp] = {
    executeQuery[MSendersInfoResp](Endpoints.sendersinfo.endpoint, marshal(snd))(unmarshal[MSendersInfoResp])
  }

  override def sendersTimeSeries(snd: MSenderAddress): Future[List[MSenderTSResponse]] = {
    executeQuery[List[MSenderTSResponse]](Endpoints.sendersts.endpoint, marshal(snd))(unmarshal[List[MSenderTSResponse]])
  }

  override def shutdownSystem(): Unit = shutdown()

}
