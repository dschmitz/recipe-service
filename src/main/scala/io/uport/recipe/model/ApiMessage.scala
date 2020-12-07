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

package io.uport.recipe.model

/**
  * Created by schmitda on 12.03.17.
  */
import akka.actor.ActorSystem
import io.uport.recipe.buildinfo.BuildInfo

final case class ApiMessage(message: String)
final case class ApiStatusMessage(message: String, buildInfo: String)

object ApiStatusMessages {

  def currentStatus()(implicit actorSystem: ActorSystem): String =
    s"""|service: ${actorSystem.name}| uptime: ${(actorSystem.uptime.toFloat / 3600).formatted("%10.2f")} hours
     """.stripMargin

  def buildInfo(): String = BuildInfo.toString

  def status()(implicit actorSystem: ActorSystem): ApiStatusMessage = ApiStatusMessage(currentStatus(), buildInfo())

  val unknownException = "An error occurred during the request."
}
