package com.joypeg.scamandrill.client

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.concurrent.Await
import com.joypeg.scamandrill.models._
import com.joypeg.scamandrill.MandrillTestUtils._
import com.joypeg.scamandrill.utils.SimpleLogger
import scala.util.Failure
import scala.util.Success

class MetadataCallsTest  extends FlatSpec with Matchers with SimpleLogger {


//  "MetadataAdd" should "work getting a valid MIMetadataResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.metadataAdd(MMeteadatapAdd(name = "testingmetadata", view_template = "test")), DefaultConfig.defaultTimeout)
//    res.name shouldBe "testingmetadata"
//    res.state shouldBe "index"
//    res.view_template shouldBe "test"
//  }
  "MetadataAdd" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.metadataAdd(MMeteadatapAdd(name = "testingmetadata", view_template = "test", key="invalid")))
  }

//  "MetadataUpdate" should "work getting a valid MIMetadataResponse" in {
//    val res = Await.result(MandrillAsyncClient.metadataUpdate(MMeteadatapAdd(name = "testingmetadata", view_template = "test")), DefaultConfig.defaultTimeout)
//    res.name shouldBe "testingmetadata"
//  }
  "MetadataUpdate" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.metadataUpdate(MMeteadatapAdd(name = "testingmetadata", view_template = "test", key="invalid")))
  }

//  "MetadataList" should "work getting a valid List[MIMetadataResponse]" in {
//    val res = Await.result(MandrillAsyncClient.metadataList(MKey()), DefaultConfig.defaultTimeout)
//    res.head.name shouldBe "testingmetadata"
//  }
  "MetadataList" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.metadataList(MKey(key="invalid")))
  }


//  "MetadataDelete" should "work getting a valid MIMetadataResponse (async client)" in {
//    val res = Await.result(MandrillAsyncClient.metadataDelete(MMeteadatapDelete(name = "testingmetadata")), DefaultConfig.defaultTimeout)
//    res.name shouldBe "testingmetadata"
//  }
  "MetadataDelete" should "fail if the key passed is invalid, with an 'Invalid_Key' code" in {
    checkFailedBecauseOfInvalidKey(MandrillBlockingClient.metadataDelete(MMeteadatapDelete(name = "testingmetadata", key="invalid")))
  }



}
