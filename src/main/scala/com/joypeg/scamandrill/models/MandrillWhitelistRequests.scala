package com.joypeg.scamandrill.models

/**
 * An email to be added retrieved or deleted from the whitelist
 * @param key - a valid API key
 * @param email - an email address for the whitelist
 */
case class MWhitelist(key: String = DefaultConfig.defaultKeyFromConfig, email: String) extends MandrillRequest