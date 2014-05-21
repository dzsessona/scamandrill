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

  /**
   * Return the senders that have tried to use this account.
   * @param key - the key of the account to use
   * @return the senders that have tried to use this account.
   */
  def sendersList(key: MKey): Any

  /**
   * Returns the sender domains that have been added to this account.
   * @param key - the key of the account to use
   * @return the sender domains that have been added to this account.
   */
  def sendersDomains(key: MKey): Any

  /**
   * Adds a sender domain to your account. Sender domains are added automatically as you send,
   * but you can use this call to add them ahead of time.
   * @param snd - the domain to add
   * @return information about the domain
   */
  def sendersAddDomain(snd: MSenderDomain): Any

  /**
   * Checks the SPF and DKIM settings for a domain.
   * If you haven't already added this domain to your account, it will be added automatically.
   * @param snd - the domain to add
   * @return information about the domain
   */
  def sendersCheckDomain(snd: MSenderDomain): Any

  /**
   * Sends a verification email in order to verify ownership of a domain. Domain verification is an optional step to
   * confirm ownership of a domain. Once a domain has been verified in a Mandrill account, other accounts may not have
   * their messages signed by that domain unless they also verify the domain.
   * This prevents other Mandrill accounts from sending mail signed by your domain.
   * @param snd - the verification email to send
   * @return information about the verification that was sent
   */
  def sendersVerifyDomain(snd: MSenderVerifyDomain): Any

  /**
   * Return more detailed information about a single sender, including aggregates of recent stats
   * @param snd - the email address of the sender
   * @return the detailed information on the sender
   */
  def sendersInfo(snd: MSenderAddress): Any

  /**
   * Return the recent history (hourly stats for the last 30 days) for a sender
   * @param snd - the email address of the sender
   * @return the array of history information
   */
  def sendersTimeSeries(snd: MSenderAddress): Any

  ////////////////////////////////////////////////////////////
  //URLS calls https://mandrillapp.com/api/docs/urls.JSON.html
  ////////////////////////////////////////////////////////////

  /**
   * Get the 100 most clicked URLs
   * @param key - the key of the account to use
   * @return the 100 most clicked URLs
   */
  def urlsList(key: MKey): Any

  /**
   * Return the 100 most clicked URLs that match the search query given
   * @param url - a search query
   * @return the 100 most clicked URLs that match the search query given
   */
  def urlsSearch(url: MUrlSearch): Any

  /**
   * Return the recent history (hourly stats for the last 30 days) for a url
   * @param url - a search query
   * @return the recent history (hourly stats for the last 30 days) for a url
   */
  def urlsTimeSeries(url: MUrlTimeSeries): Any

  /**
   * Get the list of tracking domains set up for this account
   * @param key - the key of the account to use
   * @return the list of tracking domains set up for this account
   */
  def urlsTrackingDomain(key: MKey): Any

  /**
   * Checks the CNAME settings for a tracking domain. The domain must have been added already with the add-tracking-domain call
   * @param url - an existing tracking domain name
   * @return information about the tracking domain
   */
  def urlsCheckTrackingDomain(url: MUrlDomain): Any

  /**
   * Add a tracking domain to your account
   * @param url - a domain
   * @return information about the domain
   */
  def urlsAddTrackingDomain(url: MUrlDomain): Any

  /////////////////////////////////////////////////////////////////////
  //TEMPLATE calls https://mandrillapp.com/api/docs/templates.JSON.html
  /////////////////////////////////////////////////////////////////////

  /**
   * Add a new template
   * @param template - the template
   * @return the information saved about the new template
   */
  def templateAdd(template: MTemplate): Any

  /**
   * Get the information for an existing template
   * @param template - the template
   * @return the requested template information
   */
  def templateInfo(template: MTemplateInfo): Any

  /**
   * Update the code for an existing template. If null is provided for any fields, the values will remain unchanged
   * @param template - the template
   * @return the template that was updated
   */
  def templateUpdate(template: MTemplate): Any

  /**
   * Publish the content for the template. Any new messages sent using this template will start using the content that was previously in draft.
   * @param template - the template
   * @return the template that was published
   */
  def templatePublish(template: MTemplateInfo): Any

  /**
   * Delete a template
   * @param template - the template
   * @return the template that was deleted
   */
  def templateDelete(template: MTemplateInfo): Any

  /**
   * Return a list of all the templates available to this user
   * @param template - the template
   * @return an array of objects with information about each template
   */
  def templateList(template: MTemplateList): Any

  /**
   * Return the recent history (hourly stats for the last 30 days) for a template
   * @param template - the template
   * @return an array of history information
   */
  def templateTimeSeries(template: MTemplateInfo): Any

  /**
   * Inject content and optionally merge fields into a template, returning the HTML that results
   * @param template - the template
   * @return the result of rendering the given template with the content and merge field values injected
   */
  def templateRender(template: MTemplateRender): Any

  ////////////////////////////////////////////////////////////////////
  //WEBHOOKS calls https://mandrillapp.com/api/docs/webhooks.JSON.html
  ////////////////////////////////////////////////////////////////////

  /**
   * Get the list of all webhooks defined on the account
   * @param key - the key of the account to use
   * @return the webhooks associated with the account
   */
  def webhookList(key: MKey): Any

  /**
   * Add a new webhook
   * @param webhook
   * @return the information saved about the new webhook
   */
  def webhookAdd(webhook: MWebhook): Any

  /**
   * Given the ID of an existing webhook, return the data about it
   * @param webhook - the existing webhook
   * @return the information saved about the new webhook
   */
  def webhookInfo(webhook: MWebhookInfo): Any

  /**
   * Update an existing webhook
   * @param webhook - the existing webhook to update
   * @return the information saved about the new webhook
   */
  def webhookUpdate(webhook: MWebhookUpdate): Any

  /**
   * Delete an existing webhook
   * @param webhook - the webhook to delete
   * @return the information saved about the new webhook
   */
  def webhookDelete(webhook: MWebhookInfo): Any

  //////////////////////////////////////////////////////////////////////////
  //SUBACCOUNTS calls https://mandrillapp.com/api/docs/subaccounts.JSON.html
  //////////////////////////////////////////////////////////////////////////

  /**
   * Get the list of subaccounts defined for the account, optionally filtered by a prefix
   * @param subacc - the prefix
   * @return the subaccounts for the account, up to a maximum of 1,000
   */
  def subaccountList(subacc: MSubaccountList): Any

  /**
   * Add a new subaccount
   * @param subacc - the subaccount to add
   * @return the information saved about the new subaccount
   */
  def subaccountAdd(subacc: MSubaccount): Any

  /**
   * Given the ID of an existing subaccount, return the data about it
   * @param subacc - the existing subaccount
   * @return the information about the subaccount
   */
  def subaccountInfo(subacc: MSubaccountInfo): Any

  /**
   * Update an existing subaccount
   * @param subacc - the existing subaccount to update
   * @return the information for the updated subaccount
   */
  def subaccountUpdate(subacc: MSubaccount): Any

  /**
   * Delete an existing subaccount. Any email related to the subaccount will be saved, but stats will be removed and any future sending calls to this subaccount will fail.
   * @param subacc - the subaccount to delete
   * @return the information for the deleted subaccount
   */
  def subaccountDelete(subacc: MSubaccountInfo): Any

  /**
   * Pause a subaccount's sending. Any future emails delivered to this subaccount will be queued for a maximum of 3 days until the subaccount is resumed.
   * @param subacc - the subaccount to pause
   * @return the information for the paused subaccount
   */
  def subaccountPause(subacc: MSubaccountInfo): Any

  /**
   * Resume a paused subaccount's sending
   * @param subacc - the subaccount to resume
   * @return the information for the resumed subaccount
   */
  def subaccountResume(subacc: MSubaccountInfo): Any

  ////////////////////////////////////////////////////////////
  //INBOUND https://mandrillapp.com/api/docs/key.JSON.html
  ////////////////////////////////////////////////////////////

  /**
   * List the domains that have been configured for inbound delivery
   * @param key - the key of the account to use
   * @return the inbound domains associated with the account
   */
  def inboundDomains(key: MKey): Any

  /**
   * Add an inbound domain to your account
   * @param inbound - the domain to add
   * @return information about the domain
   */
  def inboundAddDomain(inbound: MInboundDomain): Any

  /**
   * Check the MX settings for an inbound domain. The domain must have already been added with the add-domain call
   * @param inbound - the domain to check
   * @return information about the inbound domain
   */
  def inboundCheckDomain(inbound: MInboundDomain): Any

  /**
   * Delete an inbound domain from the account. All mail will stop routing for this domain immediately.
   * @param inbound - the domain to delete
   * @return information about the inbound domain
   */
  def inboundDeleteDomain(inbound: MInboundDomain): Any

  /**
   * List the mailbox routes defined for an inbound domain
   * @param inbound - the domain
   * @return the routes associated with the domain
   */
  def inboundRoutes(inbound: MInboundDomain): Any

  /**
   * Add a new mailbox route to an inbound domain
   * @param inbound - the domain
   * @return the added mailbox route information
   */
  def inboundAddRoute(inbound: MInboundRoute): Any

  /**
   * Update the pattern or webhook of an existing inbound mailbox route. If null is provided for any fields, the values will remain unchanged.
   * @param inbound - the route to update
   * @return the updated mailbox route information
   */
  def inboundUpdateRoute(inbound: MInboundUpdateRoute): Any

  /**
   * Delete an existing inbound mailbox route
   * @param inbound - the route to delete
   * @return the deleted mailbox route information
   */
  def inboundDeleteRoute(inbound: MInboundDelRoute): Any

  /**
   * Take a raw MIME document destined for a domain with inbound domains set up, and send it to the inbound hook exactly as if it had been sent over SMTP
   * @param inbound - raw MIME document
   * @return an array of the information for each recipient in the message (usually one) that matched an inbound route
   */
  def inboudSendRaw(inbound: MInboundRaw): Any

  ////////////////////////////////////////////////////////////
  //EXPORT https://mandrillapp.com/api/docs/exports.JSON.html
  ////////////////////////////////////////////////////////////

  /**
   * Returns information about an export job. If the export job's state is 'complete', the returned data will include
   * a URL you can use to fetch the results. Every export job produces a zip archive, but the format of the archive
   * is distinct for each job type.
   * The api calls that initiate exports include more details about the output format for that job type.
   * @param export - the export type
   * @return the information about the export
   */
  def exportInfo(export: MExportInfo): Any

  /**
   * Returns a list of your exports.
   * @param key - the key of the account to use
   * @return the account's exports
   */
  def exportList(key: MKey): Any

  /**
   * Begins an export of your rejection blacklist. The blacklist will be exported to a zip archive containing a single
   * file named rejects.csv that includes the following fields: email, reason, detail, created_at, expires_at, last_event_at, expires_at.
   * @param export - the export job
   * @return information about the rejects export job that was started
   */
  def exportReject(export: MExportNotify): Any

  /**
   * Begins an export of your rejection whitelist. The whitelist will be exported to a zip archive containing a single
   * file named whitelist.csv that includes the following fields: email, detail, created_at.
   * @param export - the export job
   * @return information about the whitelist export job that was started
   */
  def exportWhitelist(export: MExportNotify): Any

  /**
   * Begins an export of your activity history. The activity will be exported to a zip archive containing a single file
   * named activity.csv in the same format as you would be able to export from your account's activity view.
   * It includes the following fields: Date, Email Address, Sender, Subject, Status, Tags, Opens, Clicks, Bounce Detail.
   * If you have configured any custom metadata fields, they will be included in the exported data.
   * @param export - the export activity
   * @return information about the activity export job that was started
   */
  def exportActivity(export: MExportActivity): Any

  ////////////////////////////////////////////////////
  //ISP https://mandrillapp.com/api/docs/ips.JSON.html
  ////////////////////////////////////////////////////

  /**
   * Lists your dedicated IPs.
   * @param key - the key of the account to use
   * @return an array of structs for each dedicated IP
   */
  def ispList(key: MKey): Any

  /**
   * Retrieves information about a single dedicated ip.
   * @param isp - the isp
   * @return Information about the dedicated ip
   */
  def ispInfo(isp: MIspIp): Any

  /**
   * Requests an additional dedicated IP for your account. Accounts may have one outstanding request at any time,
   * and provisioning requests are processed within 24 hours.
   * @param isp - the isp
   * @return a description of the provisioning request that was created
   */
  def ispProvision(isp: MIspPool): Any

  /**
   * Begins the warmup process for a dedicated IP. During the warmup process, Mandrill will gradually increase the
   * percentage of your mail that is sent over the warming-up IP, over a period of roughly 30 days. The rest of your
   * mail will be sent over shared IPs or other dedicated IPs in the same pool.
   * @param isp - the isp
   * @return Information about the dedicated IP
   */
  def ispStartWarmup(isp: MIspIp): Any

  /**
   * Cancels the warmup process for a dedicated IP.
   * @param isp - the isp
   * @return Information about the dedicated IP
   */
  def ispCancelWarmup(isp: MIspIp): Any

  /**
   * Moves a dedicated IP to a different pool.
   * @param isp - the isp
   * @return Information about the updated state of the dedicated IP
   */
  def ispSetPool(isp: MIspSetPool): Any

  /**
   * Deletes a dedicated IP. This is permanent and cannot be undone.
   * @param isp - the ip
   * @return a description of the ip that was removed from your account.
   */
  def ispDelete(isp: MIspIp): Any

  /**
   * Lists your dedicated IP pools.
   * @param key - the key of the account to use
   * @return the dedicated IP pools for your account, up to a maximum of 1,000
   */
  def ispListPool(key: MKey): Any

  /**
   * Describes a single dedicated IP pool.
   * @param isp - the ip pool
   * @return Information about the dedicated ip pool
   */
  def ispPoolInfo(isp: MIspPoolInfo): Any

  /**
   * Creates a pool and returns it. If a pool already exists with this name, no action will be performed.
   * @param isp - the pool
   * @return Information about the dedicated ip pool
   */
  def ispCreatePool(isp: MIspPoolInfo): Any

  /**
   * Deletes a pool. A pool must be empty before you can delete it, and you cannot delete your default pool.
   * @param isp - the pool
   * @return information about the status of the pool that was deleted
   */
  def ispDeletePool(isp: MIspPoolInfo): Any

  /**
   * Configures the custom DNS name for a dedicated IP.
   * @param isp - custom dns
   * @return information about the dedicated IP's new configuration
   */
  def ispSetCustomDns(isp: MIspDns): Any

  //////////////////////////////////////////////////////////////
  //METADATA https://mandrillapp.com/api/docs/metadata.JSON.html
  //////////////////////////////////////////////////////////////

  /**
   * Get the list of custom metadata fields indexed for the account.
   * @param key - the key of the account to use
   * @return the custom metadata fields for the account
   */
  def metadataList(key: MKey): Any

  /**
   * Add a new custom metadata field to be indexed for the account.
   * @param meta - the metadata to add
   * @return the information saved about the new metadata field
   */
  def metadataAdd(meta: MMeteadatapAdd): Any

  /**
   * Update an existing custom metadata field.
   * @param meta - the metadata to update
   * @return the information for the updated metadata field
   */
  def metadataUpdate(meta: MMeteadatapAdd): Any

  /**
   * Delete an existing custom metadata field. Deletion isn't instataneous, and /metadata/list will continue to return
   * the field until the asynchronous deletion process is complete.
   * @param meta - the metadata to delete
   * @return the information for the deleted metadata field
   */
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
    //key
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
    //key
    val inbdom          = Value("inbdom",       "/inbound/domains.json")
    val inbadddom       = Value("inbadddom",    "/inbound/add-domain.json")
    val inbchkdom       = Value("inbchkdom",    "/inbound/check-domain.json")
    val inbdeldom       = Value("inbdeldom",    "/inbound/delete-domain.json")
    val inbroutes       = Value("inbroutes",    "/inbound/routes.json")
    val inbaddroute     = Value("inbaddroute",  "/inbound/add-route.json")
    val inbuproute      = Value("inbuproute",   "/inbound/update-route.json")
    val inbdelroute     = Value("inbdelroute",  "/inbound/delete-route.json")
    val inbraw          = Value("inbraw",       "/inbound/send-raw.json")
    //key
    val expinfo         = Value("expinfo",      "/exports/info.json")
    val explist         = Value("explist",      "/exports/list.json")
    val exprec          = Value("exprej",       "/exports/rejects.json")
    val expwhite        = Value("expwhite",     "/exports/whitelist.json")
    val expactivity     = Value("expact",       "/exports/activity.json")
    //key
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
