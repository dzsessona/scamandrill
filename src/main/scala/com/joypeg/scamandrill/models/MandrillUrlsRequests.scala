package com.joypeg.scamandrill.models


case class MUrlSearch(key: String = DefaultConfig.defaultKeyFromConfig,
                      q: String) extends MandrillRequest

case class MUrlTimeSeries(key: String = DefaultConfig.defaultKeyFromConfig,
                          url: String) extends MandrillRequest

case class MUrlDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                      domain: String) extends MandrillRequest