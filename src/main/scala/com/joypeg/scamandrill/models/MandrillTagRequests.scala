package com.joypeg.scamandrill.models

/**
 * An existing tag
 * @param key - a valid API key
 * @param tag - an existing tag name
 */
case class MTagRequest(key: String = DefaultConfig.defaultKeyFromConfig, tag: String) extends MandrillRequest

