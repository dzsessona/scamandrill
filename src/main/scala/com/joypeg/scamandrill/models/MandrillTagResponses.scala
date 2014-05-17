package com.joypeg.scamandrill.models


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

//TODO: Check if returns all time too
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

