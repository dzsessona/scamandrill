package com.joypeg.scamandrill.models

case class MTag(key: String = DefaultConfig.defaultKeyFromConfig) extends MandrillResponse
case class MTagRequest(key: String = DefaultConfig.defaultKeyFromConfig, tag: String) extends MandrillRequest

