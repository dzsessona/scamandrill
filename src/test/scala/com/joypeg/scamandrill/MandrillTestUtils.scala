package com.joypeg.scamandrill

import com.joypeg.scamandrill.models.{MTo, MSendMsg}

object MandrillTestUtils {
  val validMessage = new MSendMsg(
    html = "<h1>test</h1>",
    text = "test",
    subject = "subject test",
    from_email = "scamandrill@test.com",
    from_name = "Scamandrill",
    to = List(MTo("test@recipient.com")),
    bcc_address = "somebcc@address.com",
    tracking_domain = "fromname",
    signing_domain = "fromname",
    return_path_domain = "fromname"
  )

  val diegoMessage = new MSendMsg(
    html = "<h1>test</h1>",
    text = "test",
    subject = "subject test",
    from_email = "joypegtech@gmail.com",
    from_name = "Joypeg",
    to = List(MTo("diego.sessona@gmail.com")),
    bcc_address = "somebcc@address.com",
    tracking_domain = "fromname",
    signing_domain = "fromname",
    return_path_domain = "fromname"
  )
}
