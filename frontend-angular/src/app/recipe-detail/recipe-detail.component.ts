import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { RecipeService } from '../recipe.service';
import { Recipe } from '../entities/recipe';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './recipe-detail.component.html',
  styleUrl: './recipe-detail.component.css'
})
export class RecipeDetailComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  service = inject(RecipeService);
  recipe: Recipe | undefined;
  authorName = "";


  constructor() {
    const recipeId = parseInt(this.route.snapshot.params['id'], 10);
    this.service.getRecipeWithId(recipeId).then((recipe) => {
      this.recipe = recipe;
      if (recipe.authorId === undefined) {this.authorName = "UserId not returned"}
      else {
        try {
          this.service.getUserName(recipe.authorId).then((authorName) => {
            this.authorName = authorName;
          }
          );
        } catch (error) {
          this.authorName  = "Error fetching username : "
        }
        
      }
    });
  }

}
