package com.joypeg.scamandrill.models

case class MWhitelist(key: String = DefaultConfig.defaultKeyFromConfig,
                      email: String) extends MandrillRequest

