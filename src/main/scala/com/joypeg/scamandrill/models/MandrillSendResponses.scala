package com.joypeg.scamandrill.models


case class MSendResponse(email: String,
                        status: String,
                        reject_reason: String,
                        _id: String) extends MandrillResponse
