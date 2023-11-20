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
}
