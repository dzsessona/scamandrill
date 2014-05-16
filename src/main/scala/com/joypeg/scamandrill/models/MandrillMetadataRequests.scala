package com.joypeg.scamandrill.models

case class MMeteadatapAdd(key: String = DefaultConfig.defaultKeyFromConfig,
                          name: String,
                          view_template: String) extends MandrillRequest

case class MMeteadatapDelete(key: String = DefaultConfig.defaultKeyFromConfig,
                            name: String) extends MandrillRequest
