import { Injectable } from '@angular/core';
import { Recipe } from './entities/recipe';
import { HttpClient } from '@angular/common/http';
import { LoginResponse } from './entities/loginResponse';



@Injectable({
  providedIn: 'root'
})


export class RecipeService {

  resServiceURL = 'http://localhost:8080';

  token : String|undefined = undefined;


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
  
  async postLoginData(body : FormData) : Promise<boolean> {
    const postUrl = this.resServiceURL + "/auth/login";
    console.log(body);
    var succ = false;
    this.http.post(postUrl, body).subscribe(
      (response) => {
        console.log("login successfull" + response); succ=true;
        const loginresponse : LoginResponse = response as LoginResponse;
        this.token = loginresponse.token;
        console.log(response)
      },
      (error) => {console.log("error on post login :"  + error.error); succ=false;}
      );
    return succ;
  }
}
