package com.joypeg.scamandrill.models

/**
 *
 * @param tag - the actual tag as a string
 * @param reputation - the tag's current reputation on a scale from 0 to 100
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
case class MTagResponse(tag: String,
                       reputation: Int,
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
 *
 * @param tag - the actual tag as a string
 * @param sent - the total number of messages sent with this tag
 * @param hard_bounces - the total number of hard bounces by messages with this tag
 * @param soft_bounces - the total number of soft bounces by messages with this tag
 * @param rejects - the total number of rejected messages with this tag
 * @param complaints - the total number of spam complaints received for messages with this tag
 * @param unsubs - the total number of unsubscribe requests received for messages with this tag
 * @param opens - the total number of times messages with this tag have been opened
 * @param clicks - the total number of times tracked URLs in messages with this tag have been clicked
 * @param stats - an aggregate summary of the tag's sending stats
 */
case class MTagInfoResponse(tag: String,
                            sent: Int,
                            hard_bounces: Int,
                            soft_bounces: Int,
                            rejects: Int,
                            complaints: Int,
                            unsubs: Int,
                            opens: Int,
                            clicks: Int,
                            stats: MStats) extends MandrillResponse