package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._
import scala.util.Try
import scala.concurrent._
import scala.concurrent.duration._


object MandrillBlockingClient extends MandrillClient {

  override def shutdownSystem(): Unit = MandrillAsyncClient.shutdownSystem()

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

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

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

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

  ////////////////////////////////////////////////////////////
  //TAGS calls https://mandrillapp.com/api/docs/tags.JSON.html
  ////////////////////////////////////////////////////////////

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

  /////////////////////////////////////////////////////////////////
  //REJECT calls https://mandrillapp.com/api/docs/rejects.JSON.html
  /////////////////////////////////////////////////////////////////

  override def rejectAdd(reject: MRejectAdd): Try[MRejectAddResponse] = Try {
    Await.result(MandrillAsyncClient.rejectAdd(reject), 5 seconds)
  }

  override def rejectDelete(reject: MRejectDeleteResponse): Try[MRejectDeleteResponse] = Try {
    Await.result(MandrillAsyncClient.rejectDelete(reject), 5 seconds)
  }

  override def rejectList(reject: MRejectListResponse): Try[MRejectListResponse] = Try {
    Await.result(MandrillAsyncClient.rejectList(reject), 5 seconds)
  }

  ///////////////////////////////////////////////////////////////////////
  //WHITELIST calls https://mandrillapp.com/api/docs/whitelists.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def whitelistAdd(mail: MWhitelist): Try[MWhitelistAddResponse] = Try {
    Await.result(MandrillAsyncClient.whitelistAdd(mail), 5 seconds)
  }

  override def whitelistDelete(mail: MWhitelist): Try[MWhitelistDeleteResponse] = Try {
    Await.result(MandrillAsyncClient.whitelistDelete(mail), 5 seconds)
  }

  override def whitelistList(mail: MWhitelist): Try[MWhitelistListResponse] = Try {
    Await.result(MandrillAsyncClient.whitelistList(mail), 5 seconds)
  }

  ///////////////////////////////////////////////////////////////////////
  //SENDERS calls https://mandrillapp.com/api/docs/senders.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def sendersList(snd: MSenders): Try[List[MSendersListResp]] = Try {
    Await.result(MandrillAsyncClient.sendersList(snd), 5 seconds)
  }

  override def sendersDomains(snd: MSenders): Try[List[MSendersDomainResponses]] = Try {
    Await.result(MandrillAsyncClient.sendersDomains(snd), 5 seconds)
  }

  override def sendersAddDomain(snd: MSenderDomain): Try[MSendersDomainResponses] = Try {
    Await.result(MandrillAsyncClient.sendersAddDomain(snd), 5 seconds)
  }

  override def sendersCheckDomain(snd: MSenders): Try[MSendersDomainResponses] = Try {
    Await.result(MandrillAsyncClient.sendersCheckDomain(snd), 5 seconds)
  }

  override def sendersVerifyDomain(snd: MSenderVerifyDomain): Try[MSendersVerifyDomResp] = Try {
    Await.result(MandrillAsyncClient.sendersVerifyDomain(snd), 5 seconds)
  }

  override def sendersInfo(snd: MSenderAddress): Try[MSendersInfoResp] = Try {
    Await.result(MandrillAsyncClient.sendersInfo(snd), 5 seconds)
  }

  override def sendersTimeSeries(snd: MSenderAddress): Try[List[MSenderTSResponse]] = Try {
    Await.result(MandrillAsyncClient.sendersTimeSeries(snd), 5 seconds)
  }

  ////////////////////////////////////////////////////////////
  //URLS calls https://mandrillapp.com/api/docs/urls.JSON.html
  ////////////////////////////////////////////////////////////

  override def urlsList(url: MUrls): Try[List[MUrlResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsList(url), 5 seconds)
  }

  override def urlsSearch(url: MUrlSearch): Try[MUrlResponse] = Try {
    Await.result(MandrillAsyncClient.urlsSearch(url), 5 seconds)
  }

  override def urlsTimeSeries(url: MUrlTimeSeries): Try[List[MUrlTimeResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsTimeSeries(url), 5 seconds)
  }

  override def urlsTrackingDomain(url: MUrls): Try[List[MUrlDomainResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsTrackingDomain(url), 5 seconds)
  }

  override def urlsCheckTrackingDomain(url: MUrlDomain): Try[MUrlDomainResponse] = Try {
    Await.result(MandrillAsyncClient.urlsCheckTrackingDomain(url), 5 seconds)
  }

  override def urlsAddTrackingDomain(url: MUrlDomain): Try[MUrlDomainResponse] = Try {
    Await.result(MandrillAsyncClient.urlsAddTrackingDomain(url), 5 seconds)
  }

  /////////////////////////////////////////////////////////////////////
  //TEMPLATE calls https://mandrillapp.com/api/docs/templates.JSON.html
  /////////////////////////////////////////////////////////////////////

  override def templateAdd(template: MTemplate): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateAdd(template), 5 seconds)
  }

  override def templateInfo(template: MTemplateInfo): Try[MTemplateAddResponses]= Try {
    Await.result(MandrillAsyncClient.templateInfo(template), 5 seconds)
  }

  override def templateUpdate(template: MTemplate): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateUpdate(template), 5 seconds)
  }

  override def templatePublish(template: MTemplateInfo): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templatePublish(template), 5 seconds)
  }

  override def templateDelete(template: MTemplateInfo): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateDelete(template), 5 seconds)
  }

  override def templateList(template: MTemplateList): Try[List[MTemplateAddResponses]] = Try {
    Await.result(MandrillAsyncClient.templateList(template), 5 seconds)
  }

  override def templateTimeSeries(template: MTemplateInfo): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.templateTimeSeries(template), 5 seconds)
  }

  override def templateRender(template: MTemplateRender): Try[MTemplateRenderResponse] = Try {
    Await.result(MandrillAsyncClient.templateRender(template), 5 seconds)
  }

}
