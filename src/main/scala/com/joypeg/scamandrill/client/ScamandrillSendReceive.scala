package com.joypeg.scamandrill.client

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import com.joypeg.scamandrill.models.DefaultConfig
import com.joypeg.scamandrill.utils.SimpleLogger

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
  * This trait abstract on top of spray the handling of all request / response to the mandrill API. Its
  * executeQuery fuction is the one use by both the async client and the blocking one (wrapper).
  */
trait ScamandrillSendReceive extends SimpleLogger {

  type Entity = Either[Throwable, RequestEntity]

  implicit val system: ActorSystem
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = DefaultConfig.defaultExecutionContext


  /**
    * Fire a request to Mandrill API and try to parse the response. Because it return a Future[S], the
    * unmarshalling of the response body is done via a partially applied function on the Future transformation.
    * Uses spray-can internally to fire the request and unmarshall the response, with spray-json to do that.
    *
    * @param endpoint - the Mandrill API endpoint for the operation, for example '/messages/send.json'
    * @param reqBody  - the body of the post request already marshalled as json
    * @param handler  - this is the unmarshaller fuction of the response body, partially applied function
    * @tparam S - the type of the expected body response once unmarshalled
    * @return - a future of the expected type S
    * @note as from the api documentation, all requests are POST, and You can consider any non-200 HTTP
    *       response code an error - the returned data will contain more detailed information
    */
  def executeQuery[S](endpoint: String, reqBodyF: Future[RequestEntity])(handler: (HttpResponse => Future[S])): Future[S] = {

    //TODO: reqbody <: MandrillResponse and S :< MandrillRequest
    reqBodyF.flatMap { reqBody =>
      val request = HttpRequest(method = HttpMethods.POST, uri = Uri("/api/1.0" + endpoint), entity = reqBody)
      val clientFlow = Http().cachedHostConnectionPoolHttps[Int]("mandrillapp.com")
      val futureResponse = Source.single(request -> 1).via(clientFlow).runWith(Sink.head)
      futureResponse.flatMap { case (tryResponse, dummyInt) =>
        tryResponse match {
          case Success(rsp) => if(rsp.status.isSuccess()) handler(rsp)
            else Future.failed(new UnsuccessfulResponseException(rsp))
          case Failure(e) => Future.failed(e)
        }
      }
    }
  }

  /**
    * Asks all the underlying actors to close (waiting for 1 second)
    * and then shut down the system. Because the blocking client is
    * basically a wrapper of the async one, both the async and blocking
    * client are supposed to call this method when they are not required
    * or the application using them exit.
    */
  def shutdown(): Unit = {
    logger.info("asking all actor to close")
    Await.ready(Http().shutdownAllConnectionPools(), 1 second)
    Await.ready(system.terminate(), 1 second)
    logger.info("actor system shut down")
  }
}
