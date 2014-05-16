package com.joypeg.scamandrill.models

case class MIspId(key: String = DefaultConfig.defaultKeyFromConfig,
                  id: String) extends MandrillRequest

case class MIspIp(key: String = DefaultConfig.defaultKeyFromConfig,
                  ip: String) extends MandrillRequest

case class MIspPool(key: String = DefaultConfig.defaultKeyFromConfig,
                    warmup: Boolean,
                    pool: String) extends MandrillRequest

case class MIspPoolInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                        pool: String) extends MandrillRequest

case class MIspSetPool(key: String = DefaultConfig.defaultKeyFromConfig,
                      ip: String,
                      pool: String,
                      create_pool: Boolean) extends MandrillRequest

case class MIspDns(key: String = DefaultConfig.defaultKeyFromConfig,
                  ip: String,
                  domain: String) extends MandrillRequest



