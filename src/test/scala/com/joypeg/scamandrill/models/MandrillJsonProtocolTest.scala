package com.joypeg.scamandrill.models

import org.scalatest.{FlatSpec, Matchers}
import com.joypeg.scamandrill.utils.SimpleLogger
import spray.json.{JsArray, JsObject, JsString, JsValue}

class MandrillJsonProtocolTest extends FlatSpec with Matchers with SimpleLogger {

  it should "test SeqMVars serialization" in {
    val testValues = Seq("one", "two", "three")
    val testValue : MVars = SeqMVars("test", testValues)
    val jsObj = MandrillJsonProtocol.MVarJson.write(testValue).asJsObject

    jsObj.getFields("name", "content") match {
      case Seq(JsString(name), JsArray(values: Vector[JsValue])) => {
        val strings: Vector[String] = values
          .collect { case jsString: JsString => jsString.value }

        assert(name.toString == "test")
        assert(strings.toList == testValues.toList)
      }
      case _ => throw new IllegalArgumentException("Object is not serialized correctly")
    }
  }

  it should "test StringMVars serialization" in {
    val testValue : MVars = StringMVars("test", "one")
    val jsObj = MandrillJsonProtocol.MVarJson.write(testValue).asJsObject

    jsObj.getFields("name", "content") match {
      case Seq(JsString(name), JsString(content)) => {
        assert(name == "test")
        assert(content == "one")
      }
      case _ => throw new IllegalArgumentException("Object is not serialized correctly")
    }
  }

  it should "test SeqMVars unserialization" in {
    val testContent = Vector("one", "two", "three")
    val testValue : MVars = SeqMVars("test", testContent)
    val jsValue = MandrillJsonProtocol.MVarJson.write(testValue)
    val unserializedObj = MandrillJsonProtocol.MVarJson.read(jsValue)

    unserializedObj match {
      case custom: SeqMVars => {
        assert(custom.name == "test")
        assert(custom.content == testContent)
      }
      case _ => throw new IllegalArgumentException("Object is not unserialized correctly")
    }
  }

  it should "test StringMVars unserialization" in {
    val testValue : MVars = StringMVars("test", "one")
    val jsValue = MandrillJsonProtocol.MVarJson.write(testValue)
    val unserializedObj = MandrillJsonProtocol.MVarJson.read(jsValue)

    unserializedObj match {
      case old: StringMVars => {
        assert(old.name == "test")
        assert(old.content == "one")
      }
      case _ => throw new IllegalArgumentException("Object is not unserialized correctly")
    }
  }

  it should "test if invalid data cannot be a MVars instance" in {
    val testValue = JsObject(Map(
      "invalid" -> JsString("test")
    ))

    intercept[IllegalArgumentException] {
      MandrillJsonProtocol.MVarJson.read(testValue)
    }
  }

  it should "test if invalid JsValue cannot be a MVars instance" in {
    val testValue = JsString("test")

    intercept[IllegalArgumentException] {
      MandrillJsonProtocol.MVarJson.read(testValue)
    }
  }

}

