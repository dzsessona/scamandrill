package com.joypeg.scamandrill.models

/**
 * The Isp
 * @param key - a valid API key
 * @param id - th unique identifier
 */
case class MIspId(key: String = DefaultConfig.defaultKeyFromConfig,
                  id: String) extends MandrillRequest

/**
 * The Isp
 * @param key - a valid API key
 * @param ip - a dedicated IP address
 */
case class MIspIp(key: String = DefaultConfig.defaultKeyFromConfig,
                  ip: String) extends MandrillRequest

/**
 * The Isp pool
 * @param key - a valid API key
 * @param warmup - whether the ip is currently in warmup mode
 * @param pool - the name of the new pool to add the dedicated ip to
 */
case class MIspPool(key: String = DefaultConfig.defaultKeyFromConfig,
                    warmup: Boolean,
                    pool: String) extends MandrillRequest

/**
 * The Isp pool info
 * @param key - a valid API key
 * @param pool - the name of the new pool to add the dedicated ip to
 */
case class MIspPoolInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                        pool: String) extends MandrillRequest

/**
 * The Isp pool info
 * @param key - a valid API key
 * @param ip - a dedicated IP address
 * @param pool - the name of the new pool to add the dedicated ip to
 * @param create_pool - whether to create the pool if it does not exist; if false and the pool does not exist, an Unknown_Pool will be thrown.
 */
case class MIspSetPool(key: String = DefaultConfig.defaultKeyFromConfig,
                      ip: String,
                      pool: String,
                      create_pool: Boolean) extends MandrillRequest

/**
 * The dns
 * @param key - a valid API key
 * @param ip - a dedicated IP address
 * @param domain - the domain name to test
 */
case class MIspDns(key: String = DefaultConfig.defaultKeyFromConfig,
                  ip: String,
                  domain: String) extends MandrillRequest



