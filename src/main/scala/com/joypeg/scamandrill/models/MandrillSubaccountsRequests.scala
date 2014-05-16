package com.joypeg.scamandrill.models


case class MSubaccountList(key: String = DefaultConfig.defaultKeyFromConfig,
                           q: String) extends MandrillRequest

case class MSubaccountInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                           id: String) extends MandrillRequest

case class MSubaccount(key: String = DefaultConfig.defaultKeyFromConfig,
                       id: String,
                       name: String,
                       notes: String,
                       custom_quota: Int) extends MandrillRequest
