/*
 * Copyright 2017 David Schmitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.uport.recipe.config

import akka.actor.ActorSystem
import akka.event.{ LogSource, Logging }
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

trait AkkaConfig {

  implicit val actorSystem: ActorSystem                   = ActorSystem("recipe-service")
  implicit val materializer: ActorMaterializer            = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  // Scala 2.12
  private def logger(implicit logSource: LogSource[_ <: AkkaConfig]) = Logging(actorSystem, this.getClass)
  private implicit val logSource: LogSource[AkkaConfig]              = (t: AkkaConfig) => t.getClass.getSimpleName
  //
  implicit val log = logger

  //implicit val log = Logging(actorSystem, this.getClass)

}

object AkkaConfig extends AkkaConfig
