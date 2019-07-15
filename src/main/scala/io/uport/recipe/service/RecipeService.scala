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

package io.uport.recipe.service

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.{ IncomingConnection, ServerBinding }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route._
import akka.stream.scaladsl.{ Sink, Source }
import io.uport.recipe.config.AkkaConfig
import io.uport.recipe.routes.RecipeServiceRoutes
import io.uport.recipe.swagger.SwaggerDocService

import scala.concurrent.Future
import scala.io.StdIn

object RecipeService extends App with AkkaConfig {

  import io.uport.recipe.config.Settings._

  val service: Source[IncomingConnection, Future[ServerBinding]] = Http(actorSystem).bind(httpHost, httpPort)

  log.info(s"\nAkka HTTP Server - Version ${actorSystem.settings.ConfigVersion} - running at http://$httpHost:$httpPort/")

  val handler: Future[ServerBinding] =
    service
      .to(
        Sink.foreach { connection =>
          connection
            .handleWithAsyncHandler(
              asyncHandler {
                new RecipeServiceRoutes().routes ~
                SwaggerDocService.routes ~
                SwaggerDocService.site
              }
            )
        }
      )
      .run()

  handler.failed.foreach { case ex: Exception => log.error(ex, "Failed to bind to {}:{}", httpHost, httpPort) }

  val std = StdIn.readLine(s"\nPress RETURN to stop...")

  handler
    .flatMap(binding => binding.unbind())
    .onComplete { done =>
      done.failed.foreach(ex => log.error(ex, "Failed unbinding"))
      actorSystem.terminate()
    }
}
// TODO CoordinatedShutdown instead of terminate
