package io.uport.recipe

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.uport.recipe.service.RecipeService

class RecipeServiceSimulation extends Simulation {

//  RecipeService.main()

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
