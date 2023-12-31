import { Injectable } from '@angular/core';
import { Recipe } from './entities/recipe';
import { FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Observable, lastValueFrom, take } from 'rxjs';



@Injectable({
  providedIn: 'root'
})


export class RecipeService {

  resServiceURL = 'http://localhost:8080'

  constructor(private http: HttpClient) {
   }
  
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
    //const username = this.httpClient.get(requestURL, {responseType:"text" });
    const username = await fetch(requestURL);
    return await username.text() ?? "User Not Found"
  }
  /*
  async postLoginData(body : FormGroup) : Promise<boolean> {
    const postUrl = this.resServiceURL + "/auth//login";
    var succ = false;
    this.http.post(postUrl, body).subscribe(
      (res) => {console.log(res); succ=true;},
      (err) => {console.log(err); succ=false;}
      );
    return succ;
  }
  */
}
