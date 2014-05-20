package com.joypeg.scamandrill.models

//TODO: check if the first_sent_at is actually returned
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


