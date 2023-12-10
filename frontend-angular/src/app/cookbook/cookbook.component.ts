import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecipeService } from '../recipe.service';
import { Recipe } from '../entities/recipe'
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cookbook',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './cookbook.component.html',
  styleUrl: './cookbook.component.css'
})
export class CookbookComponent {
  recipes : Recipe[] = [];
  recipeService : RecipeService = inject(RecipeService)
recipe: any;

  constructor(){
    this.recipeService.getAllRecipes().then((recipes : Recipe[]) => {this.recipes = recipes} );
  }
}
