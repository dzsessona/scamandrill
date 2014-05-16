package com.joypeg.scamandrill.models


case class MWebhook(key: String = DefaultConfig.defaultKeyFromConfig,
                     url: String,
                     description: String,
                     events: List[String]) extends MandrillRequest

case class MWebhookInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                        id: Int) extends MandrillRequest

case class MWebhookUpdate(key: String = DefaultConfig.defaultKeyFromConfig,
                          id: Int,
                          url: String,
                          description: String,
                          events: List[String]) extends MandrillRequest


