package com.joypeg.scamandrill.models


case class MInboundDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String) extends MandrillRequest

case class MInboundRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String,
                          pattern: String,
                          url: String) extends MandrillRequest

case class MInboundUpdateRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                              id: String,
                              pattern: String,
                              url: String) extends MandrillRequest

case class MInboundDelRoute(key: String = DefaultConfig.defaultKeyFromConfig,
                            id: String) extends MandrillRequest

case class MInboundRaw(key: String = DefaultConfig.defaultKeyFromConfig,
                       raw_message: String,
                       to: List[String],
                       mail_from: String,
                       helo: String,
                       client_address: String ) extends MandrillRequest


