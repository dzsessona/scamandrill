package com.joypeg.scamandrill.models


case class MExportResponse(id: String,
                           created_at: String,
                           `type`: String,
                           finished_at: String,
                           state: String,
                           result_url: String) extends MandrillResponse
