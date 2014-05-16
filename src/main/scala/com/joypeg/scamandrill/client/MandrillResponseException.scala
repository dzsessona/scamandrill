package com.joypeg.scamandrill.client

import spray.httpx.UnsuccessfulResponseException
import spray.json._

case class MandrillError(status: String, code: Int, name: String, message: String)

case class MandrillResponseException(httpCode: Int,
                                     httpReason: String,
                                     mandrillError: MandrillError) extends RuntimeException

object MandrillResponseExceptionJsonProtocol extends DefaultJsonProtocol {
  implicit val MandrillErrorj = jsonFormat4(MandrillError)
}

object MandrillResponseException {

  import spray.json._
  import MandrillResponseExceptionJsonProtocol._

  def apply(sprayException: UnsuccessfulResponseException): MandrillResponseException = {
    new MandrillResponseException(
      sprayException.response.status.intValue,
      sprayException.response.status.reason,
      JsonParser(sprayException.response.entity.asString).convertTo[MandrillError]
    )
  }

}
