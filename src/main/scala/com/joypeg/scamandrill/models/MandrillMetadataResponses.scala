package com.joypeg.scamandrill.models

/**
 * The metadata information
 * @param name - the unique identifier of the metadata field to update
 * @param state - the current state of the metadata field, one of "active", "delete", or "index"
 * @param view_template - Mustache template to control how the metadata is rendered in your activity log
 */
case class MIMetadataResponse(name: String,
                              state: String,
                              view_template: String) extends MandrillResponse
