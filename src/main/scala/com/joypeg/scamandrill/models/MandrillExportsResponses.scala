package com.joypeg.scamandrill.models


case class MExportResponse(id: String,
                           created_at: String,
                           `type`: String,
                           finished_at: Option[String],
                           state: String,
                           result_url: Option[String]) extends MandrillResponse

