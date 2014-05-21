package com.joypeg.scamandrill.models

//TODO: not whether as in documentation
/**
 *
 * @param email - the email address you provided
 * @param added - true if the operation succeeded
 */
case class MWhitelistAddResponse(email: String, added: Boolean) extends MandrillResponse

/**
 * The information for each whitelist entry
 * @param email - the email that is whitelisted
 * @param detail - a description of why the email was whitelisted
 * @param created_at - when the email was added to the whitelist
 */
case class MWhitelistListResponse(email: String,
                                  detail: String,
                                  created_at: String) extends MandrillResponse

/**
 * Object containing the address and whether the deletion succeeded
 * @param email - the email address that was removed from the blacklist
 * @param deleted - whether the address was deleted successfully
 */
case class MWhitelistDeleteResponse(email: String, deleted: Boolean) extends MandrillResponse
