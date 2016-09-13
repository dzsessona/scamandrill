package com.joypeg.scamandrill.client

import akka.actor.ActorSystem
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{HttpResponse, _}
import akka.http.scaladsl.unmarshalling.Unmarshaller._
import akka.http.scaladsl.unmarshalling._
import com.joypeg.scamandrill.models._
import spray.json.RootJsonFormat

import scala.concurrent.Future

class MandrillAsyncClient(val system: ActorSystem = ActorSystem("scamandrill")) extends MandrillClient with ScamandrillSendReceive {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import com.joypeg.scamandrill.models.MandrillJsonProtocol._

  /**
   * Asks all the underlying actors to close (waiting for 1 second)
   * and then shut down the system. Because the blocking client is
   * basically a wrapper of the async one, bot the async and blocking
   * client are supposed to call this method when they are not required
   * or the application using them exit.
   * @see [[com.joypeg.scamandrill.client.ScamandrillSendReceive]]
   */

  def marshal[T: RootJsonFormat](value: T): Future[MessageEntity] = Marshal(value).to[MessageEntity]

  def unmarshal[T: FromResponseUnmarshaller]: HttpResponse => Future[T] = response => Unmarshal(response).to[T]

  override def shutdownSystem(): Unit = shutdown()

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

  override def usersPing(ping: MKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping.endpoint, marshal(ping)){resp => Unmarshal(resp).to[String].map(MPingResponse.apply(_))}
  }

  override def usersPing2(ping: MKey): Future[MPingResponse] = {
    executeQuery[MPingResponse](Endpoints.ping2.endpoint, marshal(ping))(unmarshal[MPingResponse])
  }

  override def usersSenders(ping: MKey): Future[List[MSenderDataResponse]] = {
    executeQuery[List[MSenderDataResponse]](Endpoints.senders.endpoint, marshal(ping))(unmarshal[List[MSenderDataResponse]])
  }

  override def usersInfo(ping: MKey): Future[MInfoResponse] = {
    executeQuery[MInfoResponse](Endpoints.info.endpoint, marshal(ping))(unmarshal[MInfoResponse])
  }

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

  override def messagesSend(msg: MSendMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.send.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def messagesSendTemplate(msg: MSendTemplateMessage): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.sendTemplate.endpoint, marshal(msg))(unmarshal[List[MSendResponse]])
  }

  override def messagesSearch(q: MSearch): Future[List[MSearchResponse]] = {
    executeQuery[List[MSearchResponse]](Endpoints.search.endpoint, marshal(q))(unmarshal[List[MSearchResponse]])
  }

  override def messagesSearchTimeSeries(q: MSearchTimeSeries): Future[List[MTimeSeriesResponse]] = {
    executeQuery[List[MTimeSeriesResponse]](Endpoints.searchTimeS.endpoint, marshal(q))(unmarshal[List[MTimeSeriesResponse]])
  }

  override def messagesInfo(q: MMessageInfo): Future[MMessageInfoResponse] = {
    executeQuery[MMessageInfoResponse](Endpoints.msginfo.endpoint, marshal(q))(unmarshal[MMessageInfoResponse])
  }

  override def messagesContent(q: MMessageInfo): Future[MContentResponse] = {
    executeQuery[MContentResponse](Endpoints.content.endpoint, marshal(q))(unmarshal[MContentResponse])
  }

  override def messagesParse(raw: MParse): Future[MParseResponse] = {
    executeQuery[MParseResponse](Endpoints.parse.endpoint, marshal(raw))(unmarshal[MParseResponse])
  }

  override def messagesSendRaw(raw: MSendRaw): Future[List[MSendResponse]] = {
    executeQuery[List[MSendResponse]](Endpoints.sendraw.endpoint, marshal(raw))(unmarshal[List[MSendResponse]])
  }

  override def messagesListSchedule(sc: MListSchedule): Future[List[MScheduleResponse]] = {
    executeQuery[List[MScheduleResponse]](Endpoints.listSchedule.endpoint, marshal(sc))(unmarshal[List[MScheduleResponse]])
  }

  override def messagesCancelSchedule(sc: MCancelSchedule): Future[MScheduleResponse] = {
    executeQuery[MScheduleResponse](Endpoints.cancelSchedule.endpoint, marshal(sc))(unmarshal[MScheduleResponse])
  }

  override def messagesReschedule(sc: MReSchedule): Future[MScheduleResponse] = {
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

  override def rejectDelete(reject: MRejectDelete): Future[MRejectDeleteResponse] = {
    executeQuery[MRejectDeleteResponse](Endpoints.regdelete.endpoint, marshal(reject))(unmarshal[MRejectDeleteResponse])
  }

  override def rejectList(reject: MRejectList): Future[List[MRejectListResponse]] = {
    executeQuery[List[MRejectListResponse]](Endpoints.rejlist.endpoint, marshal(reject))(unmarshal[List[MRejectListResponse]])
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

  override def whitelistList(mail: MWhitelist): Future[List[MWhitelistListResponse]] = {
    executeQuery[List[MWhitelistListResponse]](Endpoints.wlistlist.endpoint, marshal(mail))(unmarshal[List[MWhitelistListResponse]])
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

  override def sendersCheckDomain(snd: MSenderDomain): Future[MSendersDomainResponses] = {
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

  override def urlsSearch(url: MUrlSearch): Future[List[MUrlResponse]] = {
    executeQuery[List[MUrlResponse]](Endpoints.urlsearch.endpoint, marshal(url))(unmarshal[List[MUrlResponse]])
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
  //INBOUND https://mandrillapp.com/api/docs/key.JSON.html
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

  override def inboundSendRaw(inbound: MInboundRaw): Future[List[MInboundRawResponse]] = {
    executeQuery[List[MInboundRawResponse]](Endpoints.inbraw.endpoint, marshal(inbound))(unmarshal[List[MInboundRawResponse]])
  }

  ////////////////////////////////////////////////////////////
  //EXPORT https://mandrillapp.com/api/docs/exports.JSON.html
  ////////////////////////////////////////////////////////////

  override def exportInfo(export: MExportInfo): Future[MExportResponse] = {
    executeQuery[MExportResponse](Endpoints.expinfo.endpoint, marshal(export))(unmarshal[MExportResponse])
  }

  override def exportList(export: MKey): Future[List[MExportResponse]] = {
    executeQuery[List[MExportResponse]](Endpoints.explist.endpoint, marshal(export))(unmarshal[List[MExportResponse]])
  }

  override def exportReject(export: MExportNotify): Future[MExportResponse] = {
    executeQuery[MExportResponse](Endpoints.exprec.endpoint, marshal(export))(unmarshal[MExportResponse])
  }

  override def exportWhitelist(export: MExportNotify): Future[MExportResponse] = {
    executeQuery[MExportResponse](Endpoints.expwhite.endpoint, marshal(export))(unmarshal[MExportResponse])
  }

  override def exportActivity(export: MExportActivity): Future[MExportResponse] = {
    executeQuery[MExportResponse](Endpoints.expactivity.endpoint, marshal(export))(unmarshal[MExportResponse])
  }

  ////////////////////////////////////////////////////
  //ISP https://mandrillapp.com/api/docs/ips.JSON.html
  ////////////////////////////////////////////////////

  override def ispList(isp: MKey): Future[List[MIspResponse]] = {
    executeQuery[List[MIspResponse]](Endpoints.isplist.endpoint, marshal(isp))(unmarshal[List[MIspResponse]])
  }

  override def ispInfo(isp: MIspIp): Future[MIspResponse] = {
    executeQuery[MIspResponse](Endpoints.ispinfo.endpoint, marshal(isp))(unmarshal[MIspResponse])
  }

  override def ispProvision(isp: MIspPool): Future[MIspProvisionResp] = {
    executeQuery[MIspProvisionResp](Endpoints.ispprov.endpoint, marshal(isp))(unmarshal[MIspProvisionResp])
  }

  override def ispStartWarmup(isp: MIspIp): Future[MIspResponse] = {
    executeQuery[MIspResponse](Endpoints.ispstwarm.endpoint, marshal(isp))(unmarshal[MIspResponse])
  }

  override def ispCancelWarmup(isp: MIspIp): Future[MIspResponse] = {
    executeQuery[MIspResponse](Endpoints.ispcancwarm.endpoint, marshal(isp))(unmarshal[MIspResponse])
  }

  override def ispSetPool(isp: MIspSetPool): Future[MIspResponse] = {
    executeQuery[MIspResponse](Endpoints.ispsetpool.endpoint, marshal(isp))(unmarshal[MIspResponse])
  }

  override def ispDelete(isp: MIspIp): Future[MIspDelete] = {
    executeQuery[MIspDelete](Endpoints.ispdel.endpoint, marshal(isp))(unmarshal[MIspDelete])
  }

  override def ispListPool(isp: MKey): Future[List[MIspInfoPool]] = {
    executeQuery[List[MIspInfoPool]](Endpoints.isplistpool.endpoint, marshal(isp))(unmarshal[List[MIspInfoPool]])
  }

  override def ispPoolInfo(isp: MIspPoolInfo): Future[MIspInfoPool] = {
    executeQuery[MIspInfoPool](Endpoints.isppoolinfo.endpoint, marshal(isp))(unmarshal[MIspInfoPool])
  }

  override def ispCreatePool(isp: MIspPoolInfo): Future[MIspInfoPool] = {
    executeQuery[MIspInfoPool](Endpoints.ispcreatep.endpoint, marshal(isp))(unmarshal[MIspInfoPool])
  }

  override def ispDeletePool(isp: MIspPoolInfo): Future[MIspDeletePoolResponse] = {
    executeQuery[MIspDeletePoolResponse](Endpoints.ispdeletep.endpoint, marshal(isp))(unmarshal[MIspDeletePoolResponse])
  }

  override def ispSetCustomDns(isp: MIspDns): Future[MIspDnsResponse] = {
    executeQuery[MIspDnsResponse](Endpoints.ispdns.endpoint, marshal(isp))(unmarshal[MIspDnsResponse])
  }

  //////////////////////////////////////////////////////////////
  //METADATA https://mandrillapp.com/api/docs/metadata.JSON.html
  //////////////////////////////////////////////////////////////

  override def metadataList(meta: MKey): Future[List[MIMetadataResponse]] = {
    executeQuery[List[MIMetadataResponse]](Endpoints.metalist.endpoint, marshal(meta))(unmarshal[List[MIMetadataResponse]])
  }

  override def metadataAdd(meta: MMeteadatapAdd): Future[MIMetadataResponse] = {
    executeQuery[MIMetadataResponse](Endpoints.metaadd.endpoint, marshal(meta))(unmarshal[MIMetadataResponse])
  }

  override def metadataUpdate(meta: MMeteadatapAdd): Future[MIMetadataResponse] = {
    executeQuery[MIMetadataResponse](Endpoints.metaupdate.endpoint, marshal(meta))(unmarshal[MIMetadataResponse])
  }

  override def metadataDelete(meta: MMeteadatapDelete): Future[MIMetadataResponse] = {
    executeQuery[MIMetadataResponse](Endpoints.metadel.endpoint, marshal(meta))(unmarshal[MIMetadataResponse])
  }

}
