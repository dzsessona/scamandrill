package com.joypeg.scamandrill.client

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.io.IO
import scala.concurrent.Future
import scala.concurrent.duration._
import spray.client.pipelining._
import spray.http.{HttpRequest, HttpResponse, HttpEntity}
import spray.can.Http
import spray.util._
import com.joypeg.scamandrill.utils.SimpleLogger

trait ScamandrillSendReceive extends SimpleLogger {

  type Entity = Either[Throwable, HttpEntity]

  implicit lazy val system: ActorSystem = ActorSystem("scamandrill")
  import system.dispatcher

  //<: MandrillResponse
  def executeQuery[S ](endpoint: String, reqBody: Entity)(handler:(HttpResponse => S)): Future[S] = {

    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
    val query = Post(s"https://mandrillapp.com/api/1.0$endpoint", reqBody)
    logger.debug(s"Request: $query")

    pipeline(query).transform(
      ok => {logger.debug(s"Response OK: $ok"); ok ~> handler},
      ko => {logger.debug(s"Response OK: $ko"); ko}
    )
  }

  def shutdown(): Unit = {
    IO(Http).ask(Http.CloseAll)(1.second).await
    system.shutdown()
  }
}
