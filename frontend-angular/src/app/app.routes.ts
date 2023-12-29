import { Routes } from '@angular/router';
import { CookbookComponent } from './cookbook/cookbook.component';
import { RecipeDetailComponent } from './recipe-detail/recipe-detail.component';
import { LoginPageComponent } from './login-page/login-page.component';

export const routes: Routes = [
    {
        path: "",
        component: CookbookComponent,
        title: "Home"
    },
    {
        path: 'details/:id',
        component: RecipeDetailComponent,
        title: "Recipe details"
    },
    {
        path: "login",
        component: LoginPageComponent,
        title: "Login"
    },
];
