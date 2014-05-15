package com.joypeg.scamandrill.models


case class MSendResponse(_id: String,
                        email: String,
                        status: String,
                        reject_reason: Option[String]) extends MandrillResponse
