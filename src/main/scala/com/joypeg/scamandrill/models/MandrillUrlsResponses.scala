package com.joypeg.scamandrill.models

/**
 * Details about the domain's CNAME record
 * @param valid - whether the domain's CNAME record is valid for use with Mandrill
 * @param valid_after - when the domain's CNAME record will be considered valid for use with Mandrill as a UTC string in YYYY-MM-DD HH:MM:SS format. If set, this indicates that the record is valid now, but was previously invalid, and Mandrill will wait until the record's TTL elapses to start using it.
 * @param error - an error describing the CNAME record, or null if the record is correct
 */
case class MUrlCname(valid: Boolean,
                     valid_after: Option[String],
                     error: Option[String])

/**
 * The individual URL stats
 * @param url - he URL to be tracked
 * @param sent - the number of emails that contained the URL
 * @param clicks - the number of times the URL has been clicked from a tracked email
 * @param unique_clicks - the number of unique emails that have generated clicks for this URL
 */
case class MUrlResponse(url: String,
                        sent: Int,
                        clicks: Int,
                        unique_clicks: Int) extends MandrillResponse

/**
 * The information for a single hour
 * @param time - the hour as a UTC date string in YYYY-MM-DD HH:MM:SS format
 * @param sent - the number of emails that contained the URL
 * @param clicks - the number of times the URL has been clicked from a tracked email
 * @param unique_clicks - the number of unique emails that have generated clicks for this URL
 */
case class MUrlTimeResponse(time: String,
                            sent: Int,
                            clicks: Int,
                            unique_clicks: Int) extends MandrillResponse

/**
 * The individual tracking domain
 * @param domain - the tracking domain name
 * @param created_at - the date and time that the tracking domain was added as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param last_tested_at - when the domain's DNS settings were last tested as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param valid_tracking - whether this domain can be used as a tracking domain for email.
 * @param cname - details about the domain's CNAME record
 */
case class MUrlDomainResponse(domain: String,
                              created_at: String,
                              last_tested_at: String,
                              valid_tracking: Boolean,
                              cname: MUrlCname) extends MandrillResponse



