package com.joypeg.scamandrill.models

/**
 * An array of sender data, one for each sending addresses used by the account
 * @param address - the sender's email address
 * @param created_at - the date and time that the sender was first seen by Mandrill as a UTC date string in YYYY-MM-DD HH:MM:SS format
 * @param sent - the total number of messages sent with this tag
 * @param hard_bounces - the total number of hard bounces by messages with this tag
 * @param soft_bounces - the total number of soft bounces by messages with this tag
 * @param rejects - the total number of rejected messages with this tag
 * @param complaints - the total number of spam complaints received for messages with this tag
 * @param unsubs - the total number of unsubscribe requests received for messages with this tag
 * @param opens - the total number of times messages with this tag have been opened
 * @param clicks - the total number of times tracked URLs in messages with this tag have been clicked
 * @param unique_opens - the number of unique opens for emails sent with this tag
 * @param unique_clicks - the number of unique clicks for emails sent with this tag
 */
case class MSendersListResp(address: String,
                            created_at: String,
                            sent: Int,
                            hard_bounces: Int,
                            soft_bounces: Int,
                            rejects: Int,
                            complaints: Int,
                            unsubs: Int,
                            opens: Int,
                            clicks: Int,
                            unique_opens: Int,
                            unique_clicks: Int) extends MandrillResponse

/**
 * The information on each sending domain for the account
 * @param domain - the sender domain name
 * @param created_at - the date and time that the sending domain was first seen as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param last_tested_at - when the domain's DNS settings were last tested as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param spf - details about the domain's SPF record
 * @param dkim - details about the domain's DKIM record
 * @param verified_at - if the domain has been verified, this indicates when that verification occurred as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param valid_signing - whether this domain can be used to authenticate mail, either for itself or as a custom signing domain. If this is false but spf and dkim are both valid, you will need to verify the domain before using it to authenticate mail
 */
case class MSendersDomainResponses(domain: String,
                                    created_at: Option[String],
                                    last_tested_at: String,
                                    spf: MSendersDom,
                                    dkim: MSendersDom,
                                    verified_at: Option[String],
                                    valid_signing: Boolean) extends MandrillResponse

/**
 * Details about the domain's SPF record
 * @param valid - whether the domain's SPF record is valid for use with Mandrill
 * @param valid_after - when the domain's SPF record will be considered valid for use with Mandrill as a UTC string in YYYY-MM-DD HH:MM:SS format. If set, this indicates that the record is valid now, but was previously invalid, and Mandrill will wait until the record's TTL elapses to start using it.
 * @param error - an error describing the spf record, or null if the record is correct
 */
case class MSendersDom(valid: Boolean,
                       valid_after: Option[String],
                       error: Option[String])

/**
 * Information about the verification that was sent
 * @param status - "sent" indicates that the verification has been sent, "already_verified" indicates that the domain has already been verified with your account
 * @param domain - the domain name you provided
 * @param email - the email address the verification email was sent to
 */
case class MSendersVerifyDomResp(status: String,
                                 domain: String,
                                 email: String) extends MandrillResponse

/**
 * The detailed information on the sender
 * @param address - the sender's email address
 * @param created_at - the date and time that the sender was first seen by Mandrill as a UTC date string in YYYY-MM-DD HH:MM:SS format
 * @param sent - the total number of messages sent with this tag
 * @param hard_bounces - the total number of hard bounces by messages with this tag
 * @param soft_bounces - the total number of soft bounces by messages with this tag
 * @param rejects - the total number of rejected messages with this tag
 * @param complaints - the total number of spam complaints received for messages with this tag
 * @param unsubs - the total number of unsubscribe requests received for messages with this tag
 * @param opens - the total number of times messages with this tag have been opened
 * @param clicks - the total number of times tracked URLs in messages with this tag have been clicked
 * @param stats - an aggregate summary of the sender's sending stats
 */
case class MSendersInfoResp(address: String,
                            created_at: String,
                            sent: Int,
                            hard_bounces: Int,
                            soft_bounces: Int,
                            rejects: Int,
                            complaints: Int,
                            unsubs: Int,
                            opens: Int,
                            clicks: Int,
                            stats: MSendersStats) extends MandrillResponse

/**
 * An aggregate summary of the sender's sending stats
 * @param today - stats for this sender today
 * @param last_7_days - stats for this sender in the last 7 days
 * @param last_30_days - stats for this sender in the last 30 days
 * @param last_60_days - stats for this sender in the last 60 days
 * @param last_90_days - stats for this sender in the last 90 days
 */
case class MSendersStats(today: MStat,
                  last_7_days: MStat,
                  last_30_days: MStat,
                  last_60_days: MStat,
                  last_90_days: MStat)

/**
 * The stats for a single hour
 * @param time - the hour as a UTC date string in YYYY-MM-DD HH:MM:SS format
 * @param sent - the total number of messages sent with this tag
 * @param hard_bounces - the total number of hard bounces by messages with this tag
 * @param soft_bounces - the total number of soft bounces by messages with this tag
 * @param rejects - the total number of rejected messages with this tag
 * @param complaints - the total number of spam complaints received for messages with this tag
 * @param unsubs - the total number of unsubscribe requests received for messages with this tag
 * @param opens - the total number of times messages with this tag have been opened
 * @param clicks - the total number of times tracked URLs in messages with this tag have been clicked
 * @param unique_opens - the number of unique opens for emails sent with this tag
 * @param unique_clicks - the number of unique clicks for emails sent with this tag
 */
case class MSenderTSResponse(time: String,
                             sent: Int,
                             hard_bounces: Int,
                             soft_bounces: Int,
                             rejects: Int,
                             complaints: Int,
                             unsubs: Int,
                             opens: Int,
                             unique_opens: Int,
                             clicks: Int,
                             unique_clicks: Int) extends MandrillResponse
