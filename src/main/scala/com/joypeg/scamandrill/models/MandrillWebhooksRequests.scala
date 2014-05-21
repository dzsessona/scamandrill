package com.joypeg.scamandrill.models

/**
 * The webhook info
 * @param key - a valid API key
 * @param url - The URL that the event data will be posted to
 * @param description a description of the webhook
 * @param events - The message events that will be posted to the hook
 */
case class MWebhook(key: String = DefaultConfig.defaultKeyFromConfig,
                     url: String,
                     description: String,
                     events: List[String]) extends MandrillRequest

/**
 * The webhook info
 * @param key - a valid API key
 * @param id - a unique integer indentifier for the webhook
 */
case class MWebhookInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                        id: Int) extends MandrillRequest

/**
 * The webhook to update
 * @param key - a valid API key
 * @param id - a unique integer indentifier for the webhook
 * @param url - The URL that the event data will be posted to
 * @param description a description of the webhook
 * @param events - The message events that will be posted to the hook
 */
case class MWebhookUpdate(key: String = DefaultConfig.defaultKeyFromConfig,
                          id: Int,
                          url: String,
                          description: String,
                          events: List[String]) extends MandrillRequest


