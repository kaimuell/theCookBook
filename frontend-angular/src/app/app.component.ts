import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CookbookComponent } from './cookbook/cookbook.component';
import { HttpClientModule } from '@angular/common/http';
import { RecipeService } from './recipe.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule, 
    RouterOutlet,
     CookbookComponent, 
     RouterLink,
    ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'The CookBook';
}
