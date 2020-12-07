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

package io.uport.recipe.repository

import io.uport.recipe.model._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object RecipeRepository {

  private val carrotGingerSoup =
    Recipe(
      id = 1,
      stars = 5,
      name = "Soup",
      Difficulty.Easy,
      minutes = 15,
      persons = 2,
      Budget.Low,
      List[Ingredient](Ingredient("Salt", 1, "Spoon"), Ingredient("Carrot", 5, "Pieces"), Ingredient("Ginger", 1, "Piece")),
      List[CookingStep](CookingStep("Prepare"), CookingStep("Cook"), CookingStep("Serve"))
    )

  private val panCake =
    Recipe(
      id = 2,
      stars = 5,
      name = "Pancake",
      Difficulty.Easy,
      minutes = 15,
      persons = 2,
      Budget.Low,
      List[Ingredient](Ingredient("Sugar", 1, "Spoon"), Ingredient("Flour", 250, "g"), Ingredient("Water", 100, "ml")),
      List[CookingStep](CookingStep("Prepare"), CookingStep("Cook"), CookingStep("Serve"))
    )

  private val recipes: List[Recipe]                     = List(carrotGingerSoup, panCake)
  private val recipesById: Map[Int, Recipe]             = recipes.map(r => (r.id -> r)).toMap
  private val recipesByName: Map[(String, Int), Recipe] = recipes.map(r => ((r.name, r.id) -> r)).toMap

  def getRecipe(id: Int): Future[Option[Recipe]] =
    Future {
      require(id > 0, "Id must be greater than zero.")
      recipesById.get(id)
    }

  def getRecipes(): Future[List[Recipe]] =
    Future {
      recipes
    }

  def getRecipeByName(name: String): Future[List[Recipe]] =
    Future {
      require(name.nonEmpty, "The name must be present.")
      recipesByName.filterKeys(key => key._1.equalsIgnoreCase(name)).values.toList
    }
}
