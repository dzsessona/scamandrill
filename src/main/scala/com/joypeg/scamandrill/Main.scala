package com.joypeg.scamandrill

import com.joypeg.scamandrill.client.MandrillAsyncClient
import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App{
  val pong1 = MandrillAsyncClient.ping()
  val pong2 = MandrillAsyncClient.ping2()
  val res1 = Await.result(pong1, 6 seconds)
  val res2 = Await.result(pong2, 6 seconds)
  println("Result ping1 : " +res1)
  println("Result ping2: " + res2)
}
