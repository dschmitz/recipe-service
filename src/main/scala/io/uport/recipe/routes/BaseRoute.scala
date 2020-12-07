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

package io.uport.recipe.routes

import akka.http.scaladsl.coding.Gzip
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

trait BaseRoute {

  //For more information regarding the CORS directive, please https://github.com/lomigmegard/akka-http-cors

  def api(dsl: Route, prefix: Boolean): Route = api(dsl, prefix, "")

  def api(dsl: Route, prefix: Boolean, version: String): Route =
    cors() {
      if (prefix)
        pathPrefix("api" / version)(encodeResponseWith(Gzip)(dsl))
      else
        encodeResponseWith(Gzip)(dsl)
    }
}
