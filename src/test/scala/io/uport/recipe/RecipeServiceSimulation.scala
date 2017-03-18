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

package io.uport.recipe

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecipeServiceSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8000")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .acceptLanguageHeader("de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
    )

  val headers_0 = Map("Cache-Control" -> "max-age=0", "Upgrade-Insecure-Requests" -> "1")

  val uri1 = "http://localhost:8000/api/v1/service1/recipe/1"

  val scn = scenario("RecipeServiceSimulation")
    .exec(
      http("request_0")
        .get("/api/v1/service1/recipe/1")
        .headers(headers_0)
    )

  setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
