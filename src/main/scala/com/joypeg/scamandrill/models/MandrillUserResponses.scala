package com.joypeg.scamandrill.models

/**
 * Response for ping and ping2
 * @param PING - the string "PONG!" if the operation is successful
 */
case class MPingResponse(PING: String) extends MandrillResponse

/**
 * The information on each sending address in the account
 * @param address - the sender's email address
 * @param created_at - the date and time that the sender was first seen by Mandrill as a UTC date string in YYYY-MM-DD HH:MM:SS format
 * @param sent - the total number of messages sent by this sender
 * @param hard_bounces - the total number of hard bounces by messages by this sender
 * @param soft_bounces - the total number of soft bounces by messages by this sender
 * @param rejects - the total number of rejected messages by this sender
 * @param complaints - the total number of spam complaints received for messages by this sender
 * @param unsubs - the total number of unsubscribe requests received for messages by this sender
 * @param opens - the total number of times messages by this sender have been opened
 * @param clicks - the total number of times tracked URLs in messages by this sender have been clicked
 * @param unique_opens - the number of unique opens for emails sent for this sender
 * @param unique_clicks - the number of unique clicks for emails sent for this sender
 */
case class MSenderDataResponse(address: String,
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
 * The user information including username, key, reputation, quota, and historical sending stats
 * @param username - the username of the user (used for SMTP authentication)
 * @param created_at - the date and time that the user's Mandrill account was created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param public_id - a unique, permanent identifier for this user
 * @param reputation - the reputation of the user on a scale from 0 to 100, with 75 generally being a "good" reputation
 * @param hourly_quota - the maximum number of emails Mandrill will deliver for this user each hour. Any emails beyond that will be accepted and queued for later delivery. Users with higher reputations will have higher hourly quotas
 * @param backlog - the number of emails that are queued for delivery due to exceeding your monthly or hourly quotas
 * @param stats - an aggregate summary of the account's sending stats
 */
case class MInfoResponse( username: String,
                          created_at: String,
                          public_id: String,
                          reputation: Int,
                          hourly_quota: Int,
                          backlog: Int,
                          stats: MStats) extends MandrillResponse

/**
 * An aggregate summary of the account's sending stats
 * @param today - stats for this user so far today
 * @param last_7_days - stats for this user in the last 7 days
 * @param last_30_days - stats for this user in the last 7 days
 * @param last_60_days - stats for this user in the last 7 days
 * @param last_90_days - stats for this user in the last 7 days
 * @param all_time - stats for the lifetime of the user's account
 */
case class MStats(today: MStat,
                  last_7_days: MStat,
                  last_30_days: MStat,
                  last_60_days: MStat,
                  last_90_days: MStat,
                  all_time: Option[MStat])

/**
 * Sending stats
 * @param sent - the number of emails sent for this user in the last 7 days
 * @param hard_bounces - the number of emails hard bounced for this user in the last 7 days
 * @param soft_bounces - the number of emails soft bounced for this user in the last 7 days
 * @param rejects - the number of emails rejected for sending this user in the last 7 days
 * @param complaints - the number of spam complaints for this user in the last 7 days
 * @param unsubs - the number of unsubscribes for this user in the last 7 days
 * @param opens - the number of times emails have been opened for this user in the last 7 days
 * @param unique_opens - the number of unique opens for emails sent for this user in the last 7 days
 * @param clicks - the number of URLs that have been clicked for this user in the last 7 days
 * @param unique_clicks - the number of unique clicks for emails sent for this user in the last 7 days
 */
case class MStat(sent: Int,
                  hard_bounces: Int,
                  soft_bounces: Int,
                  rejects: Int,
                  complaints: Int,
                  unsubs: Int,
                  opens: Int,
                  unique_opens: Int,
                  clicks: Int,
                  unique_clicks: Int)



