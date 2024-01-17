import { Injectable } from '@angular/core';
import { Recipe } from './entities/recipe';
import { HttpClient } from '@angular/common/http';
import { LoginResponse } from './entities/loginResponse';
import { LoginRequest } from './entities/loginRequest';



@Injectable({
  providedIn: 'root'
})


export class RecipeService {

  private resServiceURL = 'http://localhost:8080';

  private token : String|undefined = undefined;
  private useremail: String|undefined = undefined;
  private username: String|undefined = undefined;


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
  
  async postLoginData(loginRequest : LoginRequest) : Promise<boolean> {
    var body = new FormData();
    body.append("email", loginRequest.email);
    body.append("password", loginRequest.password);

    const postUrl = this.resServiceURL + "/auth/login";
    var succ = false;
    this.http.post(postUrl, body).subscribe(
      (response) => {
        console.log("login successfull"); succ=true;
        const loginresponse : LoginResponse = response as LoginResponse;
        this.token = loginresponse.token;
        this.useremail = loginRequest.email;
        this.username = loginresponse.username;
        console.log("hello " +  this.username);
      },
      (error) => {console.log("error on post login :"  + error.error); succ=false;}
      );
    return succ;
  }
}
