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

/**
 * This trait abstract on top of spray the handling of all request / response to the mandrill API. Its
 * executeQuery fuction is the one use by both the async client and the blocking one (wrapper).
 */
trait ScamandrillSendReceive extends SimpleLogger {

  type Entity = Either[Throwable, HttpEntity]

  implicit lazy val system: ActorSystem = ActorSystem("scamandrill")
  import system.dispatcher


  /**
   * Fire a request to Mandrill API and try to parse the response. Because it return a Future[S], the
   * unmarshalling of the response body is done via a partially applied function on the Future transformation.
   * Uses spray-can internally to fire the request and unmarshall the response, with spray-json to do that.
   * @param endpoint - the Mandrill API endpoint for the operation, for example '/messages/send.json'
   * @param reqBody - the body of the post request already marshalled as json
   * @param handler - this is the unmarshaller fuction of the response body, partially applied function
   * @tparam S - the type of the expected body response once unmarshalled
   * @return - a future of the expected type S
   * @note as from the api documentation, all requests are POST, and You can consider any non-200 HTTP
   *       response code an error - the returned data will contain more detailed information
   */
  def executeQuery[S ](endpoint: String, reqBody: Entity)(handler:(HttpResponse => S)): Future[S] = {

    //TODO: reqbody <: MandrillResponse and S :< MandrillRequest
    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive
    val query = Post("https://mandrillapp.com/api/1.0" + endpoint, reqBody)
    logger.debug("Request: " + query)

    pipeline(query).transform(
      ok => {logger.debug("Response OK: " + ok); ok ~> handler},
      ko => {logger.debug("Response OK: " + ko); ko}
    )
  }

  /**
   * Asks all the underlying actors to close (waiting for 1 second)
   * and then shut down the system. Because the blocking client is
   * basically a wrapper of the async one, bot the async and blocking
   * client are supposed to call this method when they are not required
   * or the application using them exit.
   */
  def shutdown(): Unit = {
    logger.info("asking all actor to close")
    IO(Http).ask(Http.CloseAll)(1.second).await
    system.shutdown()
    logger.info("actor system shut down")
  }
}
