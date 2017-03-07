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

sealed trait CookingRecipe

final case class Recipe(
    id: Int,
    stars: Double,
    name: String,
    difficulty: Difficulty,
    minutes: Int,
    persons: Int,
    budget: Budget,
    ingredients: List[Ingredient],
    steps: List[CookingStep]
) extends CookingRecipe

final case class CookingStep(description: String) extends CookingRecipe

final case class Ingredient(name: String, amount: Double, unit: String) extends CookingRecipe
