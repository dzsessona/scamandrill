package com.joypeg.scamandrill.models


case class MTemplate(key: String = DefaultConfig.defaultKeyFromConfig,
                      name: String,
                      from_email: String,
                      from_name: String,
                      subject: String,
                      code: String,
                      text: String,
                      publish: Boolean,
                      labels: List[String]) extends MandrillRequest

case class MTemplateInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                         name: String) extends MandrillRequest

case class MTemplateList(key: String = DefaultConfig.defaultKeyFromConfig,
                         label: String) extends MandrillRequest

case class MTemplateCnt(name: String, content: String)

case class MTemplateRender(key: String = DefaultConfig.defaultKeyFromConfig,
                           template_name: String,
                           template_content: List[MTemplateCnt],
                           merge_vars: List[MTemplateCnt]) extends MandrillRequest

