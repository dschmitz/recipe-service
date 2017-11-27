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

package io.uport.recipe.swagger

import akka.http.scaladsl.server.Route
import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import io.uport.recipe.routes.RecipeServiceRoutes

object SwaggerDocService extends SwaggerHttpService with SwaggerUi {

  import io.uport.recipe.config.Settings._

  override val apiClasses: Set[Class[_]] = Set(classOf[RecipeServiceRoutes])
  override val host                      = s"${httpHost}:${httpPort}"
  //the url of your api, not swagger's json endpoint
  override val basePath    = "/"                   //the basePath for the API you are exposing
  override val apiDocsPath = "api-docs"            //where you want the swagger-json endpoint exposed
  override val info        = Info(version = "1.0") //provides license and other description details
}
