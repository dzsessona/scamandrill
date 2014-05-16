package com.joypeg.scamandrill.client

import scala.concurrent.Future
import spray.httpx.marshalling._
import spray.client.pipelining._
import com.joypeg.scamandrill.models._

object MandrillAsyncClient extends MandrillClient with ScamandrillSendReceive {

  import spray.httpx.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  override def shutdownSystem(): Unit = shutdown()

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

  override def ping(ping: MKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping.endpoint, marshal(ping))(unmarshal[String].andThen(MPingResponse))
  }

  override def ping2(ping: MKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping2.endpoint, marshal(ping))(unmarshal[MPingResponse])
  }

  override def senders(ping: MKey): Future[List[MSenderDataResponse]] = {
    executeQuery[List[MSenderDataResponse]](Endpoints.senders.endpoint, marshal(ping))(unmarshal[List[MSenderDataResponse]])
  }

  override def info(ping: MKey): Future[MInfoResponse] = {
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

  override def tagList(tag: MKey): Future[List[MTagResponse]] = {
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

  override def tagAllTimeSeries(tag: MKey): Future[List[MTimeSeriesResponse]] = {
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

  override def sendersList(snd: MKey): Future[List[MSendersListResp]] = {
    executeQuery[List[MSendersListResp]](Endpoints.senderslist.endpoint, marshal(snd))(unmarshal[List[MSendersListResp]])
  }

  override def sendersDomains(snd: MKey): Future[List[MSendersDomainResponses]] = {
    executeQuery[List[MSendersDomainResponses]](Endpoints.sendersdomains.endpoint, marshal(snd))(unmarshal[List[MSendersDomainResponses]])
  }

  override def sendersAddDomain(snd: MSenderDomain): Future[MSendersDomainResponses] = {
    executeQuery[MSendersDomainResponses](Endpoints.sendersadddom.endpoint, marshal(snd))(unmarshal[MSendersDomainResponses])
  }

  override def sendersCheckDomain(snd: MKey): Future[MSendersDomainResponses] = {
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

  ////////////////////////////////////////////////////////////
  //URLS calls https://mandrillapp.com/api/docs/urls.JSON.html
  ////////////////////////////////////////////////////////////

  override def urlsList(url: MKey): Future[List[MUrlResponse]] = {
    executeQuery[List[MUrlResponse]](Endpoints.urllist.endpoint, marshal(url))(unmarshal[List[MUrlResponse]])
  }

  override def urlsSearch(url: MUrlSearch): Future[MUrlResponse] = {
    executeQuery[MUrlResponse](Endpoints.urlsearch.endpoint, marshal(url))(unmarshal[MUrlResponse])
  }

  override def urlsTimeSeries(url: MUrlTimeSeries): Future[List[MUrlTimeResponse]] = {
    executeQuery[List[MUrlTimeResponse]](Endpoints.urlts.endpoint, marshal(url))(unmarshal[List[MUrlTimeResponse]])
  }

  override def urlsTrackingDomain(url: MKey): Future[List[MUrlDomainResponse]] = {
    executeQuery[List[MUrlDomainResponse]](Endpoints.urltrackdom.endpoint, marshal(url))(unmarshal[List[MUrlDomainResponse]])
  }

  override def urlsCheckTrackingDomain(url: MUrlDomain): Future[MUrlDomainResponse] = {
    executeQuery[MUrlDomainResponse](Endpoints.urlchktrackdom.endpoint, marshal(url))(unmarshal[MUrlDomainResponse])
  }

  override def urlsAddTrackingDomain(url: MUrlDomain): Future[MUrlDomainResponse] = {
    executeQuery[MUrlDomainResponse](Endpoints.urladdtrackdom.endpoint, marshal(url))(unmarshal[MUrlDomainResponse])
  }

  /////////////////////////////////////////////////////////////////////
  //TEMPLATE calls https://mandrillapp.com/api/docs/templates.JSON.html
  /////////////////////////////////////////////////////////////////////

  override def templateAdd(template: MTemplate): Future[MTemplateAddResponses] = {
    executeQuery[MTemplateAddResponses](Endpoints.tmpadd.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
  }

  override def templateInfo(template: MTemplateInfo): Future[MTemplateAddResponses] = {
    executeQuery[MTemplateAddResponses](Endpoints.tmpinfo.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
  }

  override def templateUpdate(template: MTemplate): Future[MTemplateAddResponses] = {
    executeQuery[MTemplateAddResponses](Endpoints.tmpupdate.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
  }

  override def templatePublish(template: MTemplateInfo): Future[MTemplateAddResponses] = {
    executeQuery[MTemplateAddResponses](Endpoints.tmppublish.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
  }

  override def templateDelete(template: MTemplateInfo): Future[MTemplateAddResponses] = {
    executeQuery[MTemplateAddResponses](Endpoints.tmpdelete.endpoint, marshal(template))(unmarshal[MTemplateAddResponses])
  }

  override def templateList(template: MTemplateList): Future[List[MTemplateAddResponses]] = {
    executeQuery[List[MTemplateAddResponses]](Endpoints.tmplist.endpoint, marshal(template))(unmarshal[List[MTemplateAddResponses]])
  }

  override def templateTimeSeries(template: MTemplateInfo): Future[List[MTimeSeriesResponse]] = {
    executeQuery[List[MTimeSeriesResponse]](Endpoints.tmpts.endpoint, marshal(template))(unmarshal[List[MTimeSeriesResponse]])
  }

  override def templateRender(template: MTemplateRender): Future[MTemplateRenderResponse] = {
    executeQuery[MTemplateRenderResponse](Endpoints.tmprender.endpoint, marshal(template))(unmarshal[MTemplateRenderResponse])
  }

  ////////////////////////////////////////////////////////////////////
  //WEBHOOKS calls https://mandrillapp.com/api/docs/webhooks.JSON.html
  ////////////////////////////////////////////////////////////////////

  override def webhookList(webhook: MKey): Future[List[MWebhooksResponse]] = {
    executeQuery[List[MWebhooksResponse]](Endpoints.webhlist.endpoint, marshal(webhook))(unmarshal[List[MWebhooksResponse]])
  }

  override def webhookAdd(webhook: MWebhook): Future[MWebhooksResponse] = {
    executeQuery[MWebhooksResponse](Endpoints.webhadd.endpoint, marshal(webhook))(unmarshal[MWebhooksResponse])
  }

  override def webhookInfo(webhook: MWebhookInfo): Future[MWebhooksResponse] = {
    executeQuery[MWebhooksResponse](Endpoints.webhinfo.endpoint, marshal(webhook))(unmarshal[MWebhooksResponse])
  }

  override def webhookUpdate(webhook: MWebhookUpdate): Future[MWebhooksResponse] = {
    executeQuery[MWebhooksResponse](Endpoints.webhupdate.endpoint, marshal(webhook))(unmarshal[MWebhooksResponse])
  }

  override def webhookDelete(webhook: MWebhookInfo): Future[MWebhooksResponse] = {
    executeQuery[MWebhooksResponse](Endpoints.webhdelete.endpoint, marshal(webhook))(unmarshal[MWebhooksResponse])
  }

  //////////////////////////////////////////////////////////////////////////
  //SUBACCOUNTS calls https://mandrillapp.com/api/docs/subaccounts.JSON.html
  //////////////////////////////////////////////////////////////////////////

  override def subaccountList(subacc: MSubaccountList): Future[List[MSubaccountsResponse]] = {
    executeQuery[List[MSubaccountsResponse]](Endpoints.sublist.endpoint, marshal(subacc))(unmarshal[List[MSubaccountsResponse]])
  }

  override def subaccountAdd(subacc: MSubaccount): Future[MSubaccountsResponse] = {
    executeQuery[MSubaccountsResponse](Endpoints.subadd.endpoint, marshal(subacc))(unmarshal[MSubaccountsResponse])
  }

  override def subaccountInfo(subacc: MSubaccountInfo): Future[MSubaccountsInfoResponse] = {
    executeQuery[MSubaccountsInfoResponse](Endpoints.subinfo.endpoint, marshal(subacc))(unmarshal[MSubaccountsInfoResponse])
  }

  override def subaccountUpdate(subacc: MSubaccount): Future[MSubaccountsResponse] = {
    executeQuery[MSubaccountsResponse](Endpoints.subupdate.endpoint, marshal(subacc))(unmarshal[MSubaccountsResponse])
  }

  override def subaccountDelete(subacc: MSubaccountInfo): Future[MSubaccountsResponse] = {
    executeQuery[MSubaccountsResponse](Endpoints.subdelete.endpoint, marshal(subacc))(unmarshal[MSubaccountsResponse])
  }

  override def subaccountPause(subacc: MSubaccountInfo): Future[MSubaccountsResponse] = {
    executeQuery[MSubaccountsResponse](Endpoints.subpause.endpoint, marshal(subacc))(unmarshal[MSubaccountsResponse])
  }

  override def subaccountResume(subacc: MSubaccountInfo): Future[MSubaccountsResponse] = {
    executeQuery[MSubaccountsResponse](Endpoints.subresume.endpoint, marshal(subacc))(unmarshal[MSubaccountsResponse])
  }

  ////////////////////////////////////////////////////////////
  //INBOUND https://mandrillapp.com/api/docs/inbound.JSON.html
  ////////////////////////////////////////////////////////////

  override def inboundDomains(inbound: MKey): Future[List[MInboundDomainResponse]] = {
    executeQuery[List[MInboundDomainResponse]](Endpoints.inbdom.endpoint, marshal(inbound))(unmarshal[List[MInboundDomainResponse]])
  }

  override def inboundAddDomain(inbound: MInboundDomain): Future[MInboundDomainResponse] = {
    executeQuery[MInboundDomainResponse](Endpoints.inbadddom.endpoint, marshal(inbound))(unmarshal[MInboundDomainResponse])
  }

  override def inboundCheckDomain(inbound: MInboundDomain): Future[MInboundDomainResponse] = {
    executeQuery[MInboundDomainResponse](Endpoints.inbchkdom.endpoint, marshal(inbound))(unmarshal[MInboundDomainResponse])
  }

  override def inboundDeleteDomain(inbound: MInboundDomain): Future[MInboundDomainResponse] = {
    executeQuery[MInboundDomainResponse](Endpoints.inbdeldom.endpoint, marshal(inbound))(unmarshal[MInboundDomainResponse])
  }

  override def inboundRoutes(inbound: MInboundDomain): Future[List[MInboundRouteResponse]] = {
    executeQuery[List[MInboundRouteResponse]](Endpoints.inbroutes.endpoint, marshal(inbound))(unmarshal[List[MInboundRouteResponse]])
  }

  override def inboundAddRoute(inbound: MInboundRoute): Future[MInboundRouteResponse] = {
    executeQuery[MInboundRouteResponse](Endpoints.inbaddroute.endpoint, marshal(inbound))(unmarshal[MInboundRouteResponse])
  }

  override def inboundUpdateRoute(inbound: MInboundUpdateRoute): Future[MInboundRouteResponse] = {
    executeQuery[MInboundRouteResponse](Endpoints.inbdelroute.endpoint, marshal(inbound))(unmarshal[MInboundRouteResponse])
  }

  override def inboundDeleteRoute(inbound: MInboundDelRoute): Future[MInboundRouteResponse] = {
    executeQuery[MInboundRouteResponse](Endpoints.inbdelroute.endpoint, marshal(inbound))(unmarshal[MInboundRouteResponse])
  }

  override def inboudSendRaw(inbound: MInboundRaw): Future[List[MInboundRawResponse]] = {
    executeQuery[List[MInboundRawResponse]](Endpoints.inbraw.endpoint, marshal(inbound))(unmarshal[List[MInboundRawResponse]])
  }


}
