package com.joypeg.scamandrill.models


case class MSenders(key: String = DefaultConfig.defaultKeyFromConfig) extends MandrillRequest

case class MSenderDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                    domain: String) extends MandrillRequest

case class MSenderVerifyDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String,
                          mailbox: String) extends MandrillRequest

case class MSenderAddress(key: String = DefaultConfig.defaultKeyFromConfig,
                     address: String) extends MandrillRequest