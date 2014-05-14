package com.joypeg.scamandrill.client

import com.joypeg.scamandrill.models._
import scala.util.Try
import scala.concurrent._
import scala.concurrent.duration._


object MandrillBlockingClient extends MandrillClient {

  override def ping(apiKey: String = configApiKey): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.ping(apiKey), 5 seconds)
  }

  override def ping2(apiKey: String = configApiKey): Try[MPingResponse] = Try {
    Await.result(MandrillAsyncClient.ping2(apiKey), 5 seconds)
  }

  override def shutdownSystem(): Unit = MandrillAsyncClient.shutdownSystem()
}
