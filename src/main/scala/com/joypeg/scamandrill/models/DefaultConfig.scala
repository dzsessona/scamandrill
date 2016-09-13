package com.joypeg.scamandrill.models

import akka.actor.ActorSystem
import com.joypeg.scamandrill.utils._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object DefaultConfig{

  lazy val defaultKeyFromConfig: String = config.getString("Mandrill.key")
  lazy val defaultTimeout: FiniteDuration = config.getInt("Mandrill.timoutInSeconds").seconds
  def defaultExecutionContext(implicit system: ActorSystem): ExecutionContext = {
    // TODO this may not be consistent with com.joypeg.scamandrill.utils.config
    if (system.settings.config.hasPath("Mandrill.defaultDispatcher")) {
      system.dispatchers.lookup("Mandrill.defaultDispatcher")
    } else system.dispatcher
  }
}
