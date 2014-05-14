package com.joypeg.scamandrill.client

trait MandrillClient {

  lazy val configApiKey: String = "Su9Twr4SZKU6aoWRQy4DIA"

  def ping(apiKey: String): Any

  def ping2(apiKey: String): Any

  def shutdownSystem(): Unit

  object Endpoints extends Enumeration {
    val ping  = Value("ping",  "/users/ping.json")
    val ping2 = Value("ping2", "/users/ping2.json")

    class MyVal(val endpoint: String) extends Val(nextId, endpoint)
    private[MandrillClient] final def Value(name: String, endpoint: String): MyVal = new MyVal(endpoint)
  }
}
