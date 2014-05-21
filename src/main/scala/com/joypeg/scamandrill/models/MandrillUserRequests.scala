package com.joypeg.scamandrill.models

/**
 * A valid API key
 * @param key - a valid API key string
 */
case class MKey(key: String = DefaultConfig.defaultKeyFromConfig) extends MandrillRequest
