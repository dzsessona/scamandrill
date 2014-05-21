package com.joypeg.scamandrill.models

/**
 * The export information
 * @param key - a valid API key
 * @param id - an export job identifier
 */
case class MExportInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                       id: String) extends MandrillRequest

/**
 * The export notify info
 * @param key - a valid API key
 * @param notify_email - an optional email address to notify when the export job has finished
 */
case class MExportNotify(key: String = DefaultConfig.defaultKeyFromConfig,
                         notify_email: String) extends MandrillRequest

/**
 * The export activity
 * @param key - a valid API key
 * @param notify_email - an optional email address to notify when the export job has finished
 * @param date_from - start date as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param date_to - end date as a UTC string in YYYY-MM-DD HH:MM:SS format
 * @param tags - an array of tag names to narrow the export to; will match messages that contain ANY of the tags
 * @param senders - an array of senders to narrow the export to
 * @param states - an array of states to narrow the export to; messages with ANY of the states will be included
 * @param api_keys - an array of api keys to narrow the export to; messsagse sent with ANY of the keys will be included
 */
case class MExportActivity(key: String = DefaultConfig.defaultKeyFromConfig,
                           notify_email: String,
                           date_from: String,
                           date_to: String,
                           tags: List[String],
                           senders: List[String],
                           states: List[String],
                           api_keys: List[String]) extends MandrillRequest

