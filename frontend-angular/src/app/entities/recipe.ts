import { Ingredient } from "./ingredient";

export interface Recipe {
    id : number,
    name : string,
    instructions : string,
    autorId : number,
    ingredientList : Ingredient[],
    imagePath : string
}