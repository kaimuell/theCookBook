import { Ingredient } from "./ingredient";

export interface Recipe {
    id : number,
    name : string,
    instructions : string,
    authorId : number,
    ingredientList : Ingredient[],
    imagePath : string
}