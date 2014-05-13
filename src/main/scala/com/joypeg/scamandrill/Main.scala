package com.joypeg.scamandrill

import com.joypeg.scamandrill.client.MandrillAsyncClient
import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App{
  val pong = MandrillAsyncClient.ping2()
  println("Result: " + Await.result(pong, 3 seconds))
}
