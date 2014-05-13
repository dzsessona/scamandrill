package com.joypeg.scamandrill.client

import scala.concurrent.Future
import com.joypeg.scamandrill.models.MPing2Response

trait MandrillClient {

  def ping2(apiKey: String): Future[MPing2Response]

  object Endpoints extends Enumeration {
    val ping2 = Value("ping2", "/users/ping2.json")

    class MyVal(val endpoint: String) extends Val(nextId, endpoint)
    private[MandrillClient] final def Value(name: String, endpoint: String): MyVal = new MyVal(endpoint)
  }
}
