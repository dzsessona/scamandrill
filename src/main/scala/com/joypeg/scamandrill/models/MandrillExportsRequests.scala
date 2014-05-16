package com.joypeg.scamandrill.models


case class MExportInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                       id: String) extends MandrillRequest

case class MExportNotify(key: String = DefaultConfig.defaultKeyFromConfig,
                         notify_email: String) extends MandrillRequest

case class MExportActivity(key: String = DefaultConfig.defaultKeyFromConfig,
                           notify_email: String,
                           date_from: String,
                           date_to: String,
                           tags: List[String],
                           senders: List[String],
                           states: List[String],
                           api_keys: List[String]) extends MandrillRequest

