package com.joypeg.scamandrill.client

import spray.http.HttpEntity
import akka.actor.ActorSystem
import com.joypeg.scamandrill.models.MandrillResponse
import scala.concurrent.Future
import spray.client.pipelining._
import spray.http.HttpRequest
import spray.http.HttpResponse

trait ScamandrillSendReceive {

  type Entity = Either[Throwable, HttpEntity]

  implicit val system: ActorSystem = ActorSystem("scamandrill")
  import system.dispatcher

  def executeQuery[S <: MandrillResponse](endpoint: String, reqBody: Entity)(handler:(HttpResponse => S)): Future[S] = {

    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
    val query = Post(s"https://mandrillapp.com/api/1.0$endpoint", reqBody)

    pipeline(query).transform(
      ok => ok ~> handler,
      ko => ko
    )
  }
}
