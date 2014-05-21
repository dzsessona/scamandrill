package com.joypeg.scamandrill.models

/**
 * Object containing the address and the result of the operation
 * @param email - an email address to block
 * @param added - whether the operation succeeded
 */
case class MRejectAddResponse(email: String, added: Boolean) extends MandrillResponse

/**
 * Object containing the address and the result of the operation
 * @param email - the email address that was removed from the blacklist
 * @param deleted - whether the address was deleted successfully.
 * @param subaccount - the subaccount blacklist that the address was removed from, if any
 */
case class MRejectDeleteResponse(email: String, deleted: Boolean, subaccount: Option[String]) extends MandrillResponse

//TODO: expires at = false???
/**
 * The information for each rejection blacklist entry
 * @param email - the email that is blocked
 * @param reason - the type of event (hard-bounce, soft-bounce, spam, unsub) that caused this rejection
 * @param detail - extended details about the event, such as the SMTP diagnostic for bounces or the comment for manually-created rejections
 * @param created_at - when the email was added to the blacklist
 * @param last_event_at - the timestamp of the most recent event that either created or renewed this rejection
 * @param expires_at - when the blacklist entry will expire (this may be in the past)
 * @param expired - whether the blacklist entry has expired
 * @param subaccount - the subaccount blacklist that the address was removed from, if any
 * @param sender - the sender that this blacklist entry applies to, or null if none.
 */
case class MRejectListResponse(email: String,
                               reason: String,
                               detail: Option[String],
                               created_at: String,
                               last_event_at: String,
                               expires_at: Boolean,
                               expired: Boolean,
                               subaccount: Option[String],
                               sender: Option[MSenderDataResponse]) extends MandrillResponse
