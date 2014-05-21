package com.joypeg.scamandrill.models

//TODO: check if the first_sent_at is actually returned
/**
 * Information about a subaccount
 * @param id - a unique indentifier for the subaccount
 * @param name - an optional display name for the subaccount
 * @param custom_quota - an optional manual hourly quota for the subaccount. If not specified, the hourly quota will be managed based on reputation
 * @param status - the current sending status of the subaccount, one of "active" or "paused"
 * @param reputation - the subaccount's current reputation on a scale from 0 to 100
 * @param created_at - the date and time that the subaccount was created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param sent_weekly - the number of emails the subaccount has sent so far this week (weeks start on midnight Monday, UTC)
 * @param sent_monthly - the number of emails the subaccount has sent so far this month (months start on midnight of the 1st, UTC)
 * @param sent_total - the number of emails the subaccount has sent since it was created
 */
case class MSubaccountsResponse(id: String,
                                name: String,
                                custom_quota: Int,
                                status: String,
                                reputation: Int,
                                created_at: String,
                                //first_sent_at: String,
                                sent_weekly: Int,
                                sent_monthly: Int,
                                sent_total: Int) extends MandrillResponse

/**
 * Information about a subaccount
 * @param id - a unique indentifier for the subaccount
 * @param name - an optional display name for the subaccount
 * @param custom_quota - an optional manual hourly quota for the subaccount. If not specified, the hourly quota will be managed based on reputation
 * @param status - the current sending status of the subaccount, one of "active" or "paused"
 * @param reputation - the subaccount's current reputation on a scale from 0 to 100
 * @param created_at - the date and time that the subaccount was created as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param first_sent_at - the date and time that the subaccount first sent as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param sent_weekly - the number of emails the subaccount has sent so far this week (weeks start on midnight Monday, UTC)
 * @param sent_monthly - the number of emails the subaccount has sent so far this month (months start on midnight of the 1st, UTC)
 * @param sent_total - the number of emails the subaccount has sent since it was created
 * @param sent_hourly - the number of emails the subaccount has sent in the last hour
 * @param hourly_quota - the current hourly quota for the subaccount, either manual or reputation-based
 * @param last_30_days - stats for this subaccount in the last 30 days
 */
case class MSubaccountsInfoResponse(id: String,
                                    name: String,
                                    notes: String,
                                    custom_quota: Int,
                                    status: String,
                                    reputation: Int,
                                    created_at: String,
                                    first_sent_at: Option[String],
                                    sent_weekly: Int,
                                    sent_monthly: Int,
                                    sent_total: Int,
                                    sent_hourly: Int,
                                    hourly_quota: Int,
                                    last_30_days: MStat) extends MandrillResponse


