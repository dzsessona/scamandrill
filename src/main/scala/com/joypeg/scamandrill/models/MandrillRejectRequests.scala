package com.joypeg.scamandrill.models

/**
 * The mail to be blacklisted
 * @param key - a valid API key
 * @param email - the email address to add to the blacklist
 * @param comment - an optional comment describing the rejection
 * @param subaccount an optional unique identifier for the subaccount to limit the blacklist entry
 */
case class MRejectAdd(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      comment: Option[String] = None,
                      subaccount: Option[String] = None) extends MandrillRequest

/**
 * Information about the list of mail that are blacklisted to be retrieved
 * @param key - a valid API key
 * @param email - the email that is blacklisted
 * @param include_expired - whether to include rejections that have already expired.
 * @param subaccount an optional unique identifier for the subaccount to limit the blacklist entry
 */
case class MRejectList(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      include_expired: Boolean = false,
                      subaccount: Option[String] = None) extends MandrillRequest

/**
 * The mail to be removed from the blacklist
 * @param key - a valid API key
 * @param email  - the email address to remove from the blacklist
 * @param subaccount an optional unique identifier for the subaccount to limit the blacklist entry
 */
case class MRejectDelete(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      subaccount: Option[String] = None) extends MandrillRequest


