package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._

trait MandrillClient {

  lazy val configApiKey: String = "6gRGtx4kJBq4nURbiKrTdA"

  def ping(apiKey: String): Any

  def ping2(apiKey: String): Any

  def senders(apiKey: String = configApiKey): Any

  def info(apiKey: String = configApiKey): Any

  def send(msg: MSendMessage): Any

  def sendTemplate(msg: MSendTemplateMessage): Any

  def shutdownSystem(): Unit

  object Endpoints extends Enumeration {
    val ping  =         Value("ping",         "/users/ping.json")
    val ping2 =         Value("ping2",        "/users/ping2.json")
    val senders =       Value("senders",      "/users/senders.json")
    val info =          Value("info",         "/users/info.json")
    val send =          Value("send",         "/messages/send.json")
    val sendTemplate =  Value("sendtemplate", "/messages/send-template.json")

    class MyVal(val endpoint: String) extends Val(nextId, endpoint)
    private[MandrillClient] final def Value(name: String, endpoint: String): MyVal = new MyVal(endpoint)
  }
}
