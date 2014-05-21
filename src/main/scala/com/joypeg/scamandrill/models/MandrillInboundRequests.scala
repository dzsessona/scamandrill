package com.joypeg.scamandrill.models

/**
 * The inbound domain
 * @param key - a valid API key
 * @param domain - a domain name
 */
case class MInboundDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String) extends MandrillRequest

/**
 * The Route
 * @param key - a valid API key
 * @param domain - a domain name
 * @param pattern - the search pattern that the mailbox name should match
 * @param url - the webhook URL where the inbound messages will be published
 */
case class MInboundRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String,
                          pattern: String,
                          url: String) extends MandrillRequest

/**
 * The Route
 * @param key - a valid API key
 * @param id - the unique identifier of an existing mailbox route
 * @param pattern - the search pattern that the mailbox name should match
 * @param url - the webhook URL where the inbound messages will be published
 */
case class MInboundUpdateRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                              id: String,
                              pattern: String,
                              url: String) extends MandrillRequest

/**
 * The route
 * @param key - a valid API key
 * @param id - the unique identifier of an existing route
 */
case class MInboundDelRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                            id: String) extends MandrillRequest

/**
 * Inbound raw
 * @param key - a valid API key
 * @param raw_message - the full MIME document of an email message
 * @param to - optionally define the recipients to receive the message - otherwise we'll use the To, Cc, and Bcc headers provided in the document
 * @param mail_from - the address specified in the MAIL FROM stage of the SMTP conversation. Required for the SPF check.
 * @param helo - the identification provided by the client mta in the MTA state of the SMTP conversation. Required for the SPF check.
 * @param client_address - the remote MTA's ip address. Optional; required for the SPF check.
 */
case class MInboundRaw(key: String = DefaultConfig.defaultKeyFromConfig,
                       raw_message: String,
                       to: List[String],
                       mail_from: String,
                       helo: String,
                       client_address: String ) extends MandrillRequest


