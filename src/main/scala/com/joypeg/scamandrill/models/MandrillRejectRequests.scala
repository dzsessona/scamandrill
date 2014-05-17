package com.joypeg.scamandrill.models

case class MRejectAdd(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      comment: Option[String] = None,
                      subaccount: Option[String] = None) extends MandrillRequest

case class MRejectList(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      include_expired: Boolean = false,
                      subaccount: Option[String] = None) extends MandrillRequest

case class MRejectDelete(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String,
                      subaccount: Option[String] = None) extends MandrillRequest


