package com.joypeg.scamandrill.models

/**
 * The search information
 * @param key - a valid API key
 * @param q - a search query
 */
case class MUrlSearch(key: String = DefaultConfig.defaultKeyFromConfig, q: String) extends MandrillRequest

/**
 * An url for the time series
 * @param key - a valid API key
 * @param url - an existing URL
 */
case class MUrlTimeSeries(key: String = DefaultConfig.defaultKeyFromConfig, url: String) extends MandrillRequest

/**
 * A valid domain
 * @param key - a valid API key
 * @param domain - a domain name
 */
case class MUrlDomain(key: String = DefaultConfig.defaultKeyFromConfig, domain: String) extends MandrillRequest