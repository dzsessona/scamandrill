package com.joypeg.scamandrill.models

/**
 * The sender domain
 * @param key - a valid API key
 * @param domain - a domain name at which you can receive email
 */
case class MSenderDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                    domain: String) extends MandrillRequest

/**
 * The sender domain
 * @param key - a valid API key
 * @param domain - a domain name at which you can receive email
 * @param mailbox - a mailbox at the domain where the verification email should be sent
 */
case class MSenderVerifyDomain(key: String = DefaultConfig.defaultKeyFromConfig,
                          domain: String,
                          mailbox: String) extends MandrillRequest

/**
 * The sender address
 * @param key - a valid API key
 * @param address - the email address of the sender
 */
case class MSenderAddress(key: String = DefaultConfig.defaultKeyFromConfig,
                     address: String) extends MandrillRequest