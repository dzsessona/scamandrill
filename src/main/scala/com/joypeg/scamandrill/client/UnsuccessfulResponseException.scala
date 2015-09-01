package com.joypeg.scamandrill.client

import akka.http.scaladsl.model.HttpResponse

/**
 * Created by mar on 26.08.2015.
 */
class UnsuccessfulResponseException(val response: HttpResponse) extends RuntimeException{

}
