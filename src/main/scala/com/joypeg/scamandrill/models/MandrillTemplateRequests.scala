package com.joypeg.scamandrill.models

/**
 * A template
 * @param key - a valid API key
 * @param name - the name for the new template - must be unique
 * @param from_email - a default sending address for emails sent using this template
 * @param from_name - a default from name to be used
 * @param subject - a default subject line to be used
 * @param code - the HTML code for the template with mc:edit attributes for the editable elements
 * @param text - a default text part to be used when sending with this template
 * @param publish - set to false to add a draft template without publishing
 * @param labels - an optional array of up to 10 labels to use for filtering templates
 */
case class MTemplate(key: String = DefaultConfig.defaultKeyFromConfig,
                      name: String,
                      from_email: String,
                      from_name: String,
                      subject: String,
                      code: String,
                      text: String,
                      publish: Boolean,
                      labels: List[String]) extends MandrillRequest

/**
 * A template
 * @param key - a valid API key
 * @param name - the name for the new template - must be unique
 */
case class MTemplateInfo(key: String = DefaultConfig.defaultKeyFromConfig,
                         name: String) extends MandrillRequest

/**
 * Templates to filter
 * @param key - a valid API key
 * @param label - an optional label to filter the templates
 */
case class MTemplateList(key: String = DefaultConfig.defaultKeyFromConfig,
                         label: String) extends MandrillRequest

/**
 * The injection of a single piece of content into a single editable region
 * @param name - the name of the mc:edit editable region to inject into
 * @param content - the content to inject
 */
case class MTemplateCnt(name: String, content: String)

/**
 * The template to render
 * @param key - a valid API key
 * @param template_name - the immutable name of a template that exists in the user's account
 * @param template_content - an array of template content to render. Each item in the array should be a struct with two keys - name: the name of the content block to set the content for, and content: the actual content to put into the block
 * @param merge_vars - optional merge variables to use for injecting merge field content. If this is not provided, no merge fields will be replaced.
 */
case class MTemplateRender(key: String = DefaultConfig.defaultKeyFromConfig,
                           template_name: String,
                           template_content: List[MTemplateCnt],
                           merge_vars: List[MTemplateCnt]) extends MandrillRequest

