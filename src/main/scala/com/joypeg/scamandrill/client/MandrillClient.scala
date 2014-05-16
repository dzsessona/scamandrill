package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._

trait MandrillClient {

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

  def ping(ping: MKey): Any

  def ping2(ping: MKey): Any

  def senders(ping: MKey): Any

  def info(ping: MKey): Any

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

  def send(msg: MSendMessage): Any

  def sendTemplate(msg: MSendTemplateMessage): Any

  def search(q: MSearch): Any

  def searchTimeSeries(q: MSearchTimeSeries): Any

  def messageInfo(q: MMessageInfo): Any

  def content(q: MMessageInfo): Any

  def parse(raw: MParse): Any

  def sendRaw(raw: MSendRaw): Any

  def listSchedule(sc: MListSchedule): Any

  def cancelSchedule(sc: MCancelSchedule): Any

  def reSchedule(sc: MReSchedule): Any

  ////////////////////////////////////////////////////////////
  //TAGS calls https://mandrillapp.com/api/docs/tags.JSON.html
  ////////////////////////////////////////////////////////////

  def tagList(tag: MKey): Any

  def tagDelete(tag: MTagRequest): Any

  def tagInfo(tag: MTagRequest): Any

  def tagTimeSeries(tag: MTagRequest): Any

  def tagAllTimeSeries(tag: MKey): Any

  /////////////////////////////////////////////////////////////////
  //REJECT calls https://mandrillapp.com/api/docs/rejects.JSON.html
  /////////////////////////////////////////////////////////////////

  def rejectAdd(reject: MRejectAdd): Any

  def rejectDelete(reject: MRejectDeleteResponse): Any

  def rejectList(reject: MRejectListResponse): Any

  ///////////////////////////////////////////////////////////////////////
  //WHITELIST calls https://mandrillapp.com/api/docs/whitelists.JSON.html
  ///////////////////////////////////////////////////////////////////////

  def whitelistAdd(mail: MWhitelist): Any

  def whitelistDelete(mail: MWhitelist): Any

  def whitelistList(mail: MWhitelist): Any

  //////////////////////////////////////////////////////////////////
  //SENDERS calls https://mandrillapp.com/api/docs/senders.JSON.html
  //////////////////////////////////////////////////////////////////

  def sendersList(snd: MKey): Any

  def sendersDomains(snd: MKey): Any

  def sendersAddDomain(snd: MSenderDomain): Any

  def sendersCheckDomain(snd: MKey): Any

  def sendersVerifyDomain(snd: MSenderVerifyDomain): Any

  def sendersInfo(snd: MSenderAddress): Any

  def sendersTimeSeries(snd: MSenderAddress): Any

  ////////////////////////////////////////////////////////////
  //URLS calls https://mandrillapp.com/api/docs/urls.JSON.html
  ////////////////////////////////////////////////////////////

  def urlsList(url: MKey): Any

  def urlsSearch(url: MUrlSearch): Any

  def urlsTimeSeries(url: MUrlTimeSeries): Any

  def urlsTrackingDomain(url: MKey): Any

  def urlsCheckTrackingDomain(url: MUrlDomain): Any

  def urlsAddTrackingDomain(url: MUrlDomain): Any

  /////////////////////////////////////////////////////////////////////
  //TEMPLATE calls https://mandrillapp.com/api/docs/templates.JSON.html
  /////////////////////////////////////////////////////////////////////

  def templateAdd(template: MTemplate): Any

  def templateInfo(template: MTemplateInfo): Any

  def templateUpdate(template: MTemplate): Any

  def templatePublish(template: MTemplateInfo): Any

  def templateDelete(template: MTemplateInfo): Any

  def templateList(template: MTemplateList): Any

  def templateTimeSeries(template: MTemplateInfo): Any

  def templateRender(template: MTemplateRender): Any

  ////////////////////////////////////////////////////////////////////
  //WEBHOOKS calls https://mandrillapp.com/api/docs/webhooks.JSON.html
  ////////////////////////////////////////////////////////////////////

  def webhookList(webhook: MKey): Any

  def webhookAdd(webhook: MWebhook): Any

  def webhookInfo(webhook: MWebhookInfo): Any

  def webhookUpdate(webhook: MWebhookUpdate): Any

  def webhookDelete(webhook: MWebhookInfo): Any

  //////////////////////////////////////////////////////////////////////////
  //SUBACCOUNTS calls https://mandrillapp.com/api/docs/subaccounts.JSON.html
  //////////////////////////////////////////////////////////////////////////

  def subaccountList(subacc: MSubaccountList): Any

  def subaccountAdd(subacc: MSubaccount): Any

  def subaccountInfo(subacc: MSubaccountInfo): Any

  def subaccountUpdate(subacc: MSubaccount): Any

  def subaccountDelete(subacc: MSubaccountInfo): Any

  def subaccountPause(subacc: MSubaccountInfo): Any

  def subaccountResume(subacc: MSubaccountInfo): Any

  ////////////////////////////////////////////////////////////
  //INBOUND https://mandrillapp.com/api/docs/inbound.JSON.html
  ////////////////////////////////////////////////////////////

  def inboundDomains(inbound: MKey): Any

  def inboundAddDomain(inbound: MInboundDomain): Any

  def inboundCheckDomain(inbound: MInboundDomain): Any

  def inboundDeleteDomain(inbound: MInboundDomain): Any

  def inboundRoutes(inbound: MInboundDomain): Any

  def inboundAddRoute(inbound: MInboundRoute): Any

  def inboundUpdateRoute(inbound: MInboundUpdateRoute): Any

  def inboundDeleteRoute(inbound: MInboundDelRoute): Any

  def inboudSendRaw(inbound: MInboundRaw): Any





  def shutdownSystem(): Unit

  object Endpoints extends Enumeration {
    //users
    val ping            = Value("ping",         "/users/ping.json")
    val ping2           = Value("ping2",        "/users/ping2.json")
    val senders         = Value("senders",      "/users/senders.json")
    val info            = Value("info",         "/users/info.json")
    //messages
    val send            = Value("send",         "/messages/send.json")
    val sendTemplate    = Value("sendtemplate", "/messages/send-template.json")
    val search          = Value("search",       "/messages/search.json")
    val searchTimeS     = Value("searchts",     "/messages/search-time-series.json")
    val msginfo         = Value("msginfo",      "/messages/info.json")
    val content         = Value("content",      "/messages/content.json")
    val parse           = Value("parse",        "/messages/parse.json")
    val sendraw         = Value("sendraw",      "/messages/send-raw.json")
    val listSchedule    = Value("sclist",       "/messages/list-scheduled.json")
    val cancelSchedule  = Value("sccanc",       "/messages/cancel-scheduled.json")
    val reschedule      = Value("scre",         "/messages/reschedule.json")
    //tags
    val taglist         = Value("taglist",      "/tags/list.json")
    val tagdelete       = Value("tagdelete",    "/tags/delete.json")
    val taginfo         = Value("taginfo",      "/tags/info.json")
    val tagtimeseries   = Value("tagts",        "/tags/time-series.json")
    val tagalltime      = Value("tagallts",     "/tags/all-time-series.json")
    //rejects
    val rejadd          = Value("rejadd",       "/rejects/add.json")
    val rejlist         = Value("rejlist",      "/rejects/list.json")
    val regdelete       = Value("regdelete",    "/rejects/delete.json")
    //whitelist
    val wlistadd        = Value("wlistadd",     "/whitelists/add.json")
    val wlistdelete     = Value("wlistdelete",  "/whitelists/delete.json")
    val wlistlist       = Value("wlistlist",    "/whitelists/list.json")
    //senders
    val senderslist     = Value("senderslist",  "/senders/list.json")
    val sendersdomains  = Value("sendersdom",   "/senders/domains.json")
    val sendersadddom   = Value("sendersadddom","/senders/add-domain.json")
    val senderschkdom   = Value("senderscheck", "/senders/check-domain.json")
    val sendersverdom   = Value("sendersverdom","/senders/verify-domain.json")
    val sendersinfo     = Value("sendersinfo",  "/senders/info.json")
    val sendersts       = Value("sendersts",    "/senders/time-series.json")
    //url
    val urllist         = Value("urllist",      "/urls/list.json")
    val urlsearch       = Value("urlsearch",    "/urls/search.json")
    val urlts           = Value("urlts",        "/urls/time-series.json")
    val urltrackdom     = Value("urltrackdom",  "/urls/tracking-domains.json")
    val urladdtrackdom  = Value("urladdtrackdm","/urls/add-tracking-domain.json")
    val urlchktrackdom  = Value("urlchktrackdm","/urls/check-tracking-domain.json")
    //template
    val tmpadd          = Value("tmpadd",       "/templates/add.json")
    val tmpinfo         = Value("tmpinfo",      "/templates/info.json")
    val tmpupdate       = Value("tmpupdate",    "/templates/update.json")
    val tmppublish      = Value("tmppublish",   "/templates/publish.json")
    val tmpdelete       = Value("tmpdelete",    "/templates/delete.json")
    val tmplist         = Value("tmplist",      "/templates/list.json")
    val tmpts           = Value("tmpts",        "/templates/time-series.json")
    val tmprender       = Value("tmprender",    "/templates/render.json")
    //webhooks
    val webhlist        = Value("webhlist",     "/webhooks/list.json")
    val webhadd         = Value("webhadd",      "/webhooks/add.json")
    val webhinfo        = Value("webhinfo",     "/webhooks/info.json")
    val webhupdate      = Value("webhupdate",   "/webhooks/update.json")
    val webhdelete      = Value("webhdelete",   "/webhooks/delete.json")
    //subaccounts
    val sublist         = Value("sublist",      "/subaccounts/list.json")
    val subadd          = Value("subadd",       "/subaccounts/add.json")
    val subinfo         = Value("subinfo",      "/subaccounts/info.json")
    val subupdate       = Value("subupdte",     "/subaccounts/update.json")
    val subdelete       = Value("subdelete",    "/subaccounts/delete.json")
    val subpause        = Value("subpause",     "/subaccounts/pause.json")
    val subresume       = Value("subresumt",    "/subaccounts/resume.json")
    //inbound
    val inbdom          = Value("inbdom",       "/inbound/domains.json")
    val inbadddom       = Value("inbadddom",    "/inbound/add-domain.json")
    val inbchkdom       = Value("inbchkdom",    "/inbound/check-domain.json")
    val inbdeldom       = Value("inbdeldom",    "/inbound/delete-domain.json")
    val inbroutes       = Value("inbroutes",    "/inbound/routes.json")
    val inbaddroute     = Value("inbaddroute",  "/inbound/add-route.json")
    val inbuproute      = Value("inbuproute",   "/inbound/update-route.json")
    val inbdelroute     = Value("inbdelroute",  "/inbound/delete-route.json")
    val inbraw          = Value("inbraw",       "/inbound/send-raw.json")
    //export
    val expinfo         = Value("expinfo",      "/exports/info.json")
    val explist         = Value("explist",      "/exports/list.json")
    val exprec          = Value("exprej",       "/exports/rejects.json")
    val expwhite        = Value("expwhite",     "/exports/whitelist.json")
    val expactivity     = Value("expact",       "/exports/activity.json")
    //isp
    val isplist         = Value("isplist",      "/ips/list.json")
    val ispinfo         = Value("ispinfo",      "/ips/info.json")
    val ispprov         = Value("ispprov",      "/ips/provision.json")
    val ispstwarm       = Value("isplistw",     "/ips/start-warmup.json")
    val ispcancwarm     = Value("ispcancw",     "/ips/cancel-warmup.json")
    val ispsetpool      = Value("ispsetpool",   "/ips/set-pool.json")
    val ispdel          = Value("ispdel",       "/ips/delete.json")
    val isplistpool     = Value("isplistpool",  "/ips/list-pools.json")
    val isppoolinfo     = Value("ispoolinfo",   "/ips/pool-info.json")
    val ispcreatep      = Value("ispcreatep",   "/ips/create-pool.json")
    val ispdeletep      = Value("ispdelpool",   "/ips/delete-pool.json")
    val ispdns          = Value("ispdns",       "/ips/set-custom-dns.json")
    //metadata
    val metalist        = Value("metalist",     "/metadata/list.json")
    val metaadd         = Value("metaadd",      "/metadata/add.json")
    val metaupdate      = Value("metaupdate",   "/metadata/update.json")
    val metadel         = Value("metadel",      "/metadata/delete.json")

    class MyVal(val endpoint: String) extends Val(nextId, endpoint)
    private[MandrillClient] final def Value(name: String, endpoint: String): MyVal = new MyVal(endpoint)
  }
}
