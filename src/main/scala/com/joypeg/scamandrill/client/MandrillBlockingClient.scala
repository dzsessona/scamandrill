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

  override def usersPing(ping: MKey): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.usersPing(ping), DefaultConfig.defaultTimeout)
  }

  override def usersPing2(ping: MKey): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.usersPing2(ping), DefaultConfig.defaultTimeout)
  }

  override def usersSenders(ping: MKey): Try[List[MSenderDataResponse]] = Try {
    Await.result(MandrillAsyncClient.usersSenders(ping), DefaultConfig.defaultTimeout)
  }

  override def usersInfo(ping: MKey): Try[MInfoResponse] = Try {
    Await.result(MandrillAsyncClient.usersInfo(ping), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

  override def messagesSend(msg: MSendMessage): Try[List[MSendResponse]] = Try {
    Await.result(MandrillAsyncClient.messagesSend(msg), DefaultConfig.defaultTimeout)
  }

  override def messagesSendTemplate(msg: MSendTemplateMessage): Try[List[MSendResponse]] = Try {
    Await.result(MandrillAsyncClient.messagesSendTemplate(msg), DefaultConfig.defaultTimeout)
  }

  override def messagesSearch(q: MSearch): Try[List[MSearchResponse]] = Try {
    Await.result(MandrillAsyncClient.messagesSearch(q), DefaultConfig.defaultTimeout)
  }

  override def messagesSearchTimeSeries(q: MSearchTimeSeries): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.messagesSearchTimeSeries(q), DefaultConfig.defaultTimeout)
  }

  override def messagesInfo(q: MMessageInfo): Try[MMessageInfoResponse] = Try {
    Await.result(MandrillAsyncClient.messagesInfo(q), DefaultConfig.defaultTimeout)
  }

  override def messagesContent(q: MMessageInfo): Try[MContentResponse] = Try {
    Await.result(MandrillAsyncClient.messagesContent(q), DefaultConfig.defaultTimeout)
  }

  override def messagesParse(raw: MParse): Try[MParseResponse] = Try {
    Await.result(MandrillAsyncClient.messagesParse(raw), DefaultConfig.defaultTimeout)
  }

  override def messagesSendRaw(raw: MSendRaw): Try[List[MSendResponse]] = Try{
    Await.result(MandrillAsyncClient.messagesSendRaw(raw), DefaultConfig.defaultTimeout)
  }

  override def messagesListSchedule(sc: MListSchedule): Try[List[MScheduleResponse]] = Try {
    Await.result(MandrillAsyncClient.messagesListSchedule(sc), DefaultConfig.defaultTimeout)
  }

  override def messagesCancelSchedule(sc: MCancelSchedule): Try[MScheduleResponse] = Try {
    Await.result(MandrillAsyncClient.messagesCancelSchedule(sc), DefaultConfig.defaultTimeout)
  }

  override def messagesReschedule(sc: MReSchedule): Try[MScheduleResponse] = Try {
    Await.result(MandrillAsyncClient.messagesReschedule(sc), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////
  //TAGS calls https://mandrillapp.com/api/docs/tags.JSON.html
  ////////////////////////////////////////////////////////////

  override def tagList(tag: MKey): Try[List[MTagResponse]] = Try {
    Await.result(MandrillAsyncClient.tagList(tag), DefaultConfig.defaultTimeout)
  }

  override def tagDelete(tag: MTagRequest): Try[MTagResponse] = Try {
    Await.result(MandrillAsyncClient.tagDelete(tag), DefaultConfig.defaultTimeout)
  }

  override def tagInfo(tag: MTagRequest): Try[MTagInfoResponse] = Try {
    Await.result(MandrillAsyncClient.tagInfo(tag), DefaultConfig.defaultTimeout)
  }

  override def tagTimeSeries(tag: MTagRequest): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.tagTimeSeries(tag), DefaultConfig.defaultTimeout)
  }

  override def tagAllTimeSeries(tag: MKey): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.tagAllTimeSeries(tag), DefaultConfig.defaultTimeout)
  }

  /////////////////////////////////////////////////////////////////
  //REJECT calls https://mandrillapp.com/api/docs/rejects.JSON.html
  /////////////////////////////////////////////////////////////////

  override def rejectAdd(reject: MRejectAdd): Try[MRejectAddResponse] = Try {
    Await.result(MandrillAsyncClient.rejectAdd(reject), DefaultConfig.defaultTimeout)
  }

  override def rejectDelete(reject: MRejectDelete): Try[MRejectDeleteResponse] = Try {
    Await.result(MandrillAsyncClient.rejectDelete(reject), DefaultConfig.defaultTimeout)
  }

  override def rejectList(reject: MRejectList): Try[List[MRejectListResponse]] = Try {
    Await.result(MandrillAsyncClient.rejectList(reject), DefaultConfig.defaultTimeout)
  }

  ///////////////////////////////////////////////////////////////////////
  //WHITELIST calls https://mandrillapp.com/api/docs/whitelists.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def whitelistAdd(mail: MWhitelist): Try[MWhitelistAddResponse] = Try {
    Await.result(MandrillAsyncClient.whitelistAdd(mail), DefaultConfig.defaultTimeout)
  }

  override def whitelistDelete(mail: MWhitelist): Try[MWhitelistDeleteResponse] = Try {
    Await.result(MandrillAsyncClient.whitelistDelete(mail), DefaultConfig.defaultTimeout)
  }

  override def whitelistList(mail: MWhitelist): Try[List[MWhitelistListResponse]] = Try {
    Await.result(MandrillAsyncClient.whitelistList(mail), DefaultConfig.defaultTimeout)
  }

  ///////////////////////////////////////////////////////////////////////
  //SENDERS calls https://mandrillapp.com/api/docs/senders.JSON.html
  ///////////////////////////////////////////////////////////////////////

  override def sendersList(snd: MKey): Try[List[MSendersListResp]] = Try {
    Await.result(MandrillAsyncClient.sendersList(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersDomains(snd: MKey): Try[List[MSendersDomainResponses]] = Try {
    Await.result(MandrillAsyncClient.sendersDomains(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersAddDomain(snd: MSenderDomain): Try[MSendersDomainResponses] = Try {
    Await.result(MandrillAsyncClient.sendersAddDomain(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersCheckDomain(snd: MSenderDomain): Try[MSendersDomainResponses] = Try {
    Await.result(MandrillAsyncClient.sendersCheckDomain(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersVerifyDomain(snd: MSenderVerifyDomain): Try[MSendersVerifyDomResp] = Try {
    Await.result(MandrillAsyncClient.sendersVerifyDomain(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersInfo(snd: MSenderAddress): Try[MSendersInfoResp] = Try {
    Await.result(MandrillAsyncClient.sendersInfo(snd), DefaultConfig.defaultTimeout)
  }

  override def sendersTimeSeries(snd: MSenderAddress): Try[List[MSenderTSResponse]] = Try {
    Await.result(MandrillAsyncClient.sendersTimeSeries(snd), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////
  //URLS calls https://mandrillapp.com/api/docs/urls.JSON.html
  ////////////////////////////////////////////////////////////

  override def urlsList(url: MKey): Try[List[MUrlResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsList(url), DefaultConfig.defaultTimeout)
  }

  override def urlsSearch(url: MUrlSearch): Try[List[MUrlResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsSearch(url), DefaultConfig.defaultTimeout)
  }

  override def urlsTimeSeries(url: MUrlTimeSeries): Try[List[MUrlTimeResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsTimeSeries(url), DefaultConfig.defaultTimeout)
  }

  override def urlsTrackingDomain(url: MKey): Try[List[MUrlDomainResponse]] = Try {
    Await.result(MandrillAsyncClient.urlsTrackingDomain(url), DefaultConfig.defaultTimeout)
  }

  override def urlsCheckTrackingDomain(url: MUrlDomain): Try[MUrlDomainResponse] = Try {
    Await.result(MandrillAsyncClient.urlsCheckTrackingDomain(url), DefaultConfig.defaultTimeout)
  }

  override def urlsAddTrackingDomain(url: MUrlDomain): Try[MUrlDomainResponse] = Try {
    Await.result(MandrillAsyncClient.urlsAddTrackingDomain(url), DefaultConfig.defaultTimeout)
  }

  /////////////////////////////////////////////////////////////////////
  //TEMPLATE calls https://mandrillapp.com/api/docs/templates.JSON.html
  /////////////////////////////////////////////////////////////////////

  override def templateAdd(template: MTemplate): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateAdd(template), DefaultConfig.defaultTimeout)
  }

  override def templateInfo(template: MTemplateInfo): Try[MTemplateAddResponses]= Try {
    Await.result(MandrillAsyncClient.templateInfo(template), DefaultConfig.defaultTimeout)
  }

  override def templateUpdate(template: MTemplate): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateUpdate(template), DefaultConfig.defaultTimeout)
  }

  override def templatePublish(template: MTemplateInfo): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templatePublish(template), DefaultConfig.defaultTimeout)
  }

  override def templateDelete(template: MTemplateInfo): Try[MTemplateAddResponses] = Try {
    Await.result(MandrillAsyncClient.templateDelete(template), DefaultConfig.defaultTimeout)
  }

  override def templateList(template: MTemplateList): Try[List[MTemplateAddResponses]] = Try {
    Await.result(MandrillAsyncClient.templateList(template), DefaultConfig.defaultTimeout)
  }

  override def templateTimeSeries(template: MTemplateInfo): Try[List[MTimeSeriesResponse]] = Try {
    Await.result(MandrillAsyncClient.templateTimeSeries(template), DefaultConfig.defaultTimeout)
  }

  override def templateRender(template: MTemplateRender): Try[MTemplateRenderResponse] = Try {
    Await.result(MandrillAsyncClient.templateRender(template), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////////////
  //WEBHOOKS calls https://mandrillapp.com/api/docs/webhooks.JSON.html
  ////////////////////////////////////////////////////////////////////

  override def webhookList(webhook: MKey): Try[List[MWebhooksResponse]] = Try {
    Await.result(MandrillAsyncClient.webhookList(webhook), DefaultConfig.defaultTimeout)
  }

  override def webhookAdd(webhook: MWebhook): Try[MWebhooksResponse] = Try {
    Await.result(MandrillAsyncClient.webhookAdd(webhook), DefaultConfig.defaultTimeout)
  }

  override def webhookInfo(webhook: MWebhookInfo): Try[MWebhooksResponse] = Try {
    Await.result(MandrillAsyncClient.webhookInfo(webhook), DefaultConfig.defaultTimeout)
  }

  override def webhookUpdate(webhook: MWebhookUpdate): Try[MWebhooksResponse] = Try {
    Await.result(MandrillAsyncClient.webhookUpdate(webhook), DefaultConfig.defaultTimeout)
  }

  override def webhookDelete(webhook: MWebhookInfo): Try[MWebhooksResponse] = Try {
    Await.result(MandrillAsyncClient.webhookDelete(webhook), DefaultConfig.defaultTimeout)
  }

  //////////////////////////////////////////////////////////////////////////
  //SUBACCOUNTS calls https://mandrillapp.com/api/docs/subaccounts.JSON.html
  //////////////////////////////////////////////////////////////////////////

  override def subaccountList(subacc: MSubaccountList): Try[List[MSubaccountsResponse]] = Try {
    Await.result(MandrillAsyncClient.subaccountList(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountAdd(subacc: MSubaccount): Try[MSubaccountsResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountAdd(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountInfo(subacc: MSubaccountInfo): Try[MSubaccountsInfoResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountInfo(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountUpdate(subacc: MSubaccount): Try[MSubaccountsResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountUpdate(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountDelete(subacc: MSubaccountInfo): Try[MSubaccountsResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountDelete(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountPause(subacc: MSubaccountInfo): Try[MSubaccountsResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountPause(subacc), DefaultConfig.defaultTimeout)
  }

  override def subaccountResume(subacc: MSubaccountInfo): Try[MSubaccountsResponse] = Try {
    Await.result(MandrillAsyncClient.subaccountResume(subacc), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////
  //INBOUND https://mandrillapp.com/api/docs/key.JSON.html
  ////////////////////////////////////////////////////////////

  override def inboundDomains(inbound: MKey): Try[List[MInboundDomainResponse]] = Try {
    Await.result(MandrillAsyncClient.inboundDomains(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundAddDomain(inbound: MInboundDomain): Try[MInboundDomainResponse] = Try {
    Await.result(MandrillAsyncClient.inboundAddDomain(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundCheckDomain(inbound: MInboundDomain): Try[MInboundDomainResponse] = Try {
    Await.result(MandrillAsyncClient.inboundCheckDomain(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundDeleteDomain(inbound: MInboundDomain): Try[MInboundDomainResponse] = Try {
    Await.result(MandrillAsyncClient.inboundDeleteDomain(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundRoutes(inbound: MInboundDomain): Try[List[MInboundRouteResponse]] = Try {
    Await.result(MandrillAsyncClient.inboundRoutes(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundAddRoute(inbound: MInboundRoute): Try[MInboundRouteResponse] = Try {
    Await.result(MandrillAsyncClient.inboundAddRoute(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundUpdateRoute(inbound: MInboundUpdateRoute): Try[MInboundRouteResponse] = Try {
    Await.result(MandrillAsyncClient.inboundUpdateRoute(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboundDeleteRoute(inbound: MInboundDelRoute): Try[MInboundRouteResponse] = Try {
    Await.result(MandrillAsyncClient.inboundDeleteRoute(inbound), DefaultConfig.defaultTimeout)
  }

  override def inboudSendRaw(inbound: MInboundRaw): Try[List[MInboundRawResponse]] = Try {
    Await.result(MandrillAsyncClient.inboudSendRaw(inbound), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////////////
  //EXPORT https://mandrillapp.com/api/docs/exports.JSON.html
  ////////////////////////////////////////////////////////////

  override def exportInfo(export: MExportInfo): Try[MExportResponse] = Try {
    Await.result(MandrillAsyncClient.exportInfo(export), DefaultConfig.defaultTimeout)
  }

  override def exportList(export: MKey): Try[List[MExportResponse]] = Try {
    Await.result(MandrillAsyncClient.exportList(export), DefaultConfig.defaultTimeout)
  }

  override def exportReject(export: MExportNotify): Try[MExportResponse] = Try {
    Await.result(MandrillAsyncClient.exportReject(export), DefaultConfig.defaultTimeout)
  }

  override def exportWhitelist(export: MExportNotify): Try[MExportResponse] = Try {
    Await.result(MandrillAsyncClient.exportWhitelist(export), DefaultConfig.defaultTimeout)
  }

  override def exportActivity(export: MExportActivity): Try[MExportResponse] = Try {
    Await.result(MandrillAsyncClient.exportActivity(export), DefaultConfig.defaultTimeout)
  }

  ////////////////////////////////////////////////////
  //ISP https://mandrillapp.com/api/docs/ips.JSON.html
  ////////////////////////////////////////////////////

  override def ispList(isp: MKey): Try[List[MIspResponse]] = Try {
    Await.result(MandrillAsyncClient.ispList(isp), DefaultConfig.defaultTimeout)
  }

  override def ispInfo(isp: MIspIp): Try[MIspResponse] = Try {
    Await.result(MandrillAsyncClient.ispInfo(isp), DefaultConfig.defaultTimeout)
  }

  override def ispProvision(isp: MIspPool): Try[MIspProvisionResp] = Try {
    Await.result(MandrillAsyncClient.ispProvision(isp), DefaultConfig.defaultTimeout)
  }

  override def ispStartWarmup(isp: MIspIp): Try[MIspResponse] = Try {
    Await.result(MandrillAsyncClient.ispStartWarmup(isp), DefaultConfig.defaultTimeout)
  }

  override def ispCancelWarmup(isp: MIspIp): Try[MIspResponse] = Try {
    Await.result(MandrillAsyncClient.ispCancelWarmup(isp), DefaultConfig.defaultTimeout)
  }

  override def ispSetPool(isp: MIspSetPool): Try[MIspResponse] = Try {
    Await.result(MandrillAsyncClient.ispSetPool(isp), DefaultConfig.defaultTimeout)
  }

  override def ispDelete(isp: MIspIp): Try[MIspDelete] = Try {
    Await.result(MandrillAsyncClient.ispDelete(isp), DefaultConfig.defaultTimeout)
  }

  override def ispListPool(isp: MKey): Try[List[MIspInfoPool]] = Try {
    Await.result(MandrillAsyncClient.ispListPool(isp), DefaultConfig.defaultTimeout)
  }

  override def ispPoolInfo(isp: MIspPoolInfo): Try[MIspInfoPool] = Try {
    Await.result(MandrillAsyncClient.ispPoolInfo(isp), DefaultConfig.defaultTimeout)
  }

  override def ispCreatePool(isp: MIspPoolInfo): Try[MIspInfoPool] = Try {
    Await.result(MandrillAsyncClient.ispCreatePool(isp), DefaultConfig.defaultTimeout)
  }

  override def ispDeletePool(isp: MIspPoolInfo): Try[MIspDeletePoolResponse] = Try {
    Await.result(MandrillAsyncClient.ispDeletePool(isp), DefaultConfig.defaultTimeout)
  }

  override def ispSetCustomDns(isp: MIspDns): Try[MIspDnsResponse] = Try {
    Await.result(MandrillAsyncClient.ispSetCustomDns(isp), DefaultConfig.defaultTimeout)
  }

  //////////////////////////////////////////////////////////////
  //METADATA https://mandrillapp.com/api/docs/metadata.JSON.html
  //////////////////////////////////////////////////////////////

  override def metadataList(meta: MKey): Try[List[MIMetadataResponse]] = Try {
    Await.result(MandrillAsyncClient.metadataList(meta), DefaultConfig.defaultTimeout)
  }

  override def metadataAdd(meta: MMeteadatapAdd): Try[MIMetadataResponse] = Try {
    Await.result(MandrillAsyncClient.metadataAdd(meta), DefaultConfig.defaultTimeout)
  }

  override def metadataUpdate(meta: MMeteadatapAdd): Try[MIMetadataResponse] = Try {
    Await.result(MandrillAsyncClient.metadataUpdate(meta), DefaultConfig.defaultTimeout)
  }

  override def metadataDelete(meta: MMeteadatapDelete): Try[MIMetadataResponse] = Try {
    Await.result(MandrillAsyncClient.metadataDelete(meta), DefaultConfig.defaultTimeout)
  }
}
