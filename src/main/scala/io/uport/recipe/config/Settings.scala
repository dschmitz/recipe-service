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

import com.typesafe.config.{ Config, ConfigFactory }

import scala.util.Try

object Settings {

  private val config: Config     = ConfigFactory.load()
  private val httpConfig: Config = config.getConfig("http")

  val httpHost: String = Try(httpConfig.getString("host")).getOrElse("0.0.0.0")
  val httpPort: Int    = Try(httpConfig.getInt("port")).getOrElse(8000)
}
