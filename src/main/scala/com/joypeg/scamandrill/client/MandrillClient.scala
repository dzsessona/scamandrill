package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._

trait MandrillClient {

  /////////////////////////////////////////////////////////////
  //USER calls https://mandrillapp.com/api/docs/users.JSON.html
  /////////////////////////////////////////////////////////////

  /**
   * Validate an API key and respond to a ping
   * @param key - the key of the account to use
   * @return - the string "PONG!" if successful
   */
  def usersPing(key: MKey): Any

  /**
   * Validate an API key and respond to a ping (anal JSON parser version)
   * @param key - the key of the account to use
   * @return - the string "PONG!" if successful
   */
  def usersPing2(key: MKey): Any

  /**
   * Return the senders that have tried to use this account, both verified and unverified
   * @param key - the key of the account to use
   * @return the senders that have tried to use this account, both verified and unverified
   */
  def usersSenders(key: MKey): Any

  /**
   * Return the information about the API-connected user
   * @param key - the key of the account to use
   * @return the information about the API-connected user 
   */
  def usersInfo(key: MKey): Any

  ////////////////////////////////////////////////////////////////////
  //MESSAGES calls https://mandrillapp.com/api/docs/messages.JSON.html
  ////////////////////////////////////////////////////////////////////

  /**
   * Send a new transactional message through Mandrill
   * @param msg - the message to send
   * @return - an of structs for each recipient containing the key "email" with the email address and "status" as either "sent", "queued", or "rejected"
   */
  def messagesSend(msg: MSendMessage): Any

  /**
   * Send a new transactional message through Mandrill using a template
   * @param msg - the message to send
   * @return - an of structs for each recipient containing the key "email" with the email address and "status" as either "sent", "queued", or "rejected"
   */
  def messagesSendTemplate(msg: MSendTemplateMessage): Any

  /**
   * Search the content of recently sent messages and optionally narrow by date range, tags and senders.
   * This method may be called up to 20 times per minute. If you need the data more often, you can use
   * /messages/info.json to get the information for a single message, or webhooks to push activity to
   * your own application for querying.
   * @param q - the search values
   * @return an array of information for a single matching message
   */
  def messagesSearch(q: MSearch): Any

  /**
   * Search the content of recently sent messages and return the aggregated hourly stats for matching messages
   * @param q - the search values
   * @return the history information
   */
  def messagesSearchTimeSeries(q: MSearchTimeSeries): Any

  /**
   * Get the information for a single recently sent message
   * @param q - the message info (containing unique id)
   * @return the information for the message
   */
  def messagesInfo(q: MMessageInfo): Any

  /**
   * Get the full content of a recently sent message
   * @param q - the message info (containing unique id)
   * @return the content of the message
   */
  def messagesContent(q: MMessageInfo): Any

  /**
   * Parse the full MIME document for an email message, returning the content of the message broken into its constituent pieces
   * @param raw - the full MIME document of an email message
   * @return the parsed message
   */
  def messagesParse(raw: MParse): Any

  /**
   * Take a raw MIME document for a message, and send it exactly as if it were sent through Mandrill's SMTP servers
   * @param raw - the full MIME document of an email message
   * @return an array for each recipient containing the key "email" with the email address and "status" as either "sent", "queued", or "rejected"
   */
  def messagesSendRaw(raw: MSendRaw): Any

  /**
   * Queries your scheduled emails by sender or recipient, or both.
   * @param to - the recipient address to restrict results to
   * @return a list of up to 1000 scheduled emails
   */
  def messagesListSchedule(to: MListSchedule): Any

  /**
   * Cancels a scheduled email
   * @param sc - the scheduled mail
   * @return information about the scheduled email that was cancelled.
   */
  def messagesCancelSchedule(sc: MCancelSchedule): Any

  /**
   * Reschedules a scheduled email.
   * @param sc - the mail to reschedule
   * @return information about the scheduled email that was rescheduled.
   */
  def messagesReschedule(sc: MReSchedule): Any

  ////////////////////////////////////////////////////////////
  //TAGS calls https://mandrillapp.com/api/docs/tags.JSON.html
  ////////////////////////////////////////////////////////////

  /**
   * Return all of the user-defined key information
   * @param key - the key of the account to use
   * @return all of the user-defined key information
   */
  def tagList(key: MKey): Any

  /**
   * Deletes a tag permanently. Deleting a tag removes the tag from any messages that have been sent, and also deletes the tag's stats.
   * There is no way to undo this operation, so use it carefully.
   * @param tag - the existing tag info
   * @return the tag that was deleted
   */
  def tagDelete(tag: MTagRequest): Any

  /**
   * Return more detailed information about a single tag, including aggregates of recent stats
   * @param tag - the existing tag info
   * @return the tag asked
   */
  def tagInfo(tag: MTagRequest): Any

  /**
   * Return the recent history (hourly stats for the last 30 days) for a tag
   * @param tag - the existing tag info
   * @return the recent history (hourly stats for the last 30 days) for a tag
   */
  def tagTimeSeries(tag: MTagRequest): Any

  /**
   * Return the recent history (hourly stats for the last 30 days) for all tags
   * @param key - the key of the account to use
   * @return the recent history (hourly stats for the last 30 days) for all tags
   */
  def tagAllTimeSeries(key: MKey): Any

  /////////////////////////////////////////////////////////////////
  //REJECT calls https://mandrillapp.com/api/docs/rejects.JSON.html
  /////////////////////////////////////////////////////////////////

  /**
   * Adds an email to your email rejection blacklist. Addresses that you add manually will never expire and there is no
   * reputation penalty for removing them from your blacklist. Attempting to blacklist an address that has been
   * whitelisted will have no effect.
   * @param add - info about the mail to blacklist
   * @return an object containing the address and the result of the operation
   */
  def rejectAdd(add: MRejectAdd): Any

  /**
   * Deletes an email rejection. There is no limit to how many rejections you can remove from your blacklist,
   * but keep in mind that each deletion has an affect on your reputation.
   * @param delete - the mail to delete from the blacklist
   * @return - an object containing the address and whether the deletion succeeded.
   */
  def rejectDelete(delete: MRejectDelete): Any

  /**
   * Retrieves your email rejection blacklist. You can provide an email address to limit the results.
   * Returns up to 1000 results. By default, entries that have expired are excluded from the results;
   * set include_expired to true to include them.
   * @param list - information about the list of mails to retrieve
   * @return up to 1000 results
   */
  def rejectList(list: MRejectList): Any

  ///////////////////////////////////////////////////////////////////////
  //WHITELIST calls https://mandrillapp.com/api/docs/whitelists.JSON.html
  ///////////////////////////////////////////////////////////////////////

  /**
   * Adds an email to your email rejection whitelist. If the address is currently on your blacklist,
   * that blacklist entry will be removed automatically.
   * @param mail - the mail to be added to the whitelist
   * @return an object containing the address and the result of the operation
   */
  def whitelistAdd(mail: MWhitelist): Any

  /**
   * Removes an email address from the whitelist.
   * @param mail - the mail to be removed from the whitelist
   * @return a status object containing the address and whether the deletion succeeded
   */
  def whitelistDelete(mail: MWhitelist): Any

  /**
   * Retrieves your email rejection whitelist. You can provide an email address or search prefix to limit the results.
   * @param mail - the list of mails to be returned
   * @return up to 1000 results
   */
  def whitelistList(mail: MWhitelist): Any

  //////////////////////////////////////////////////////////////////
  //SENDERS calls https://mandrillapp.com/api/docs/senders.JSON.html
  //////////////////////////////////////////////////////////////////

  def sendersList(snd: MKey): Any

  def sendersDomains(snd: MKey): Any

  def sendersAddDomain(snd: MSenderDomain): Any

  def sendersCheckDomain(snd: MSenderDomain): Any

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

  ////////////////////////////////////////////////////////////
  //EXPORT https://mandrillapp.com/api/docs/exports.JSON.html
  ////////////////////////////////////////////////////////////

  def exportInfo(export: MExportInfo): Any

  def exportList(export: MKey): Any

  def exportReject(export: MExportNotify): Any

  def exportWhitelist(export: MExportNotify): Any

  def exportActivity(export: MExportActivity): Any

  ////////////////////////////////////////////////////
  //ISP https://mandrillapp.com/api/docs/ips.JSON.html
  ////////////////////////////////////////////////////

  def ispList(isp: MKey): Any

  def ispInfo(isp: MIspIp): Any

  def ispProvision(isp: MIspPool): Any

  def ispStartWarmup(isp: MIspIp): Any

  def ispCancelWarmup(isp: MIspIp): Any

  def ispSetPool(isp: MIspSetPool): Any

  def ispDelete(isp: MIspIp): Any

  def ispListPool(isp: MKey): Any

  def ispPoolInfo(isp: MIspPoolInfo): Any

  def ispCreatePool(isp: MIspPoolInfo): Any

  def ispDeletePool(isp: MIspPoolInfo): Any

  def ispSetCustomDns(isp: MIspDns): Any

  //////////////////////////////////////////////////////////////
  //METADATA https://mandrillapp.com/api/docs/metadata.JSON.html
  //////////////////////////////////////////////////////////////

  def metadataList(meta: MKey): Any

  def metadataAdd(meta: MMeteadatapAdd): Any

  def metadataUpdate(meta: MMeteadatapAdd): Any

  def metadataDelete(meta: MMeteadatapDelete): Any



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
