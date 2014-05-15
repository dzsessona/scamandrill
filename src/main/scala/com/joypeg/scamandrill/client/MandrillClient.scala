package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._

trait MandrillClient {

  def ping(ping: MPing): Any

  def ping2(ping: MPing): Any

  def senders(ping: MPing): Any

  def info(ping: MPing): Any

  def send(msg: MSendMessage): Any

  def sendTemplate(msg: MSendTemplateMessage): Any

  def search(q: MSearch): Any

  def searchTimeSeries(q: MSearchTimeSeries): Any

  def messageInfo(q: MMessageInfo): Any

  def content(q: MMessageInfo): Any

  def shutdownSystem(): Unit

  object Endpoints extends Enumeration {
    val ping  =         Value("ping",         "/users/ping.json")
    val ping2 =         Value("ping2",        "/users/ping2.json")
    val senders =       Value("senders",      "/users/senders.json")
    val info =          Value("info",         "/users/info.json")
    val send =          Value("send",         "/messages/send.json")
    val sendTemplate =  Value("sendtemplate", "/messages/send-template.json")
    val search =        Value("search",       "/messages/search.json")
    val searchTimeS =   Value("searchts",     "/messages/search-time-series.json")
    val msginfo =       Value("msginfo",      "/messages/info.json")
    val content =       Value("content",      "/messages/content.json")

    class MyVal(val endpoint: String) extends Val(nextId, endpoint)
    private[MandrillClient] final def Value(name: String, endpoint: String): MyVal = new MyVal(endpoint)
  }
}
