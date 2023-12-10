import { Injectable } from '@angular/core';
import { Recipe } from './entities/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  resServiceURL = 'http://localhost:8080'
  
  constructor() { }
  
  async getAllRecipes(): Promise<Recipe[]>{
    const requestURL = this.resServiceURL + "/recipe/all";
    const recipes = await fetch(requestURL);
    return await recipes.json() ?? []
  }
  
  async getRecipeWithId(recipeId: number) : Promise<Recipe> {
    const requestURL = this.resServiceURL + "/recipe/withId?id=" + recipeId;
    const recipe = await fetch(requestURL);
    return await recipe.json() ?? undefined
  }

  async getUserName(authorId : number) : Promise<string> {
    const requestURL = this.resServiceURL + "/users/username?id=" + authorId;
    const recipe = await fetch(requestURL);
    return await recipe.json() ?? "User Not Found"
  }

}
