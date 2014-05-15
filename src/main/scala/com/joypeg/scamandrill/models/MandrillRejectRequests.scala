package com.joypeg.scamandrill.models

case class MRejectAdd(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      comment: String,
                      subaccount: String) extends MandrillRequest

case class MRejectList(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      include_expired: Boolean = false,
                      subaccount: String) extends MandrillRequest

case class MRejectDelete(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      subaccount: String) extends MandrillRequest


