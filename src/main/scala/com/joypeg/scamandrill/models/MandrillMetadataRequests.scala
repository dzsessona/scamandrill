package com.joypeg.scamandrill.models

/**
 * The metadata
 * @param key - a valid API key
 * @param name - a unique identifier for the metadata field
 * @param view_template - optional Mustache template to control how the metadata is rendered in your activity log
 */
case class MMeteadatapAdd(key: String = DefaultConfig.defaultKeyFromConfig,
                          name: String,
                          view_template: String) extends MandrillRequest

/**
 * The metadata
 * @param key - a valid API key
 * @param name - the unique identifier of the metadata field to update
 */
case class MMeteadatapDelete(key: String = DefaultConfig.defaultKeyFromConfig,
                            name: String) extends MandrillRequest
