import { IngredientUnit } from "./ingredientUnit";

export interface Ingredient {
    id : number,
    name : string,
    amount : number,
    unit : IngredientUnit, 
}
