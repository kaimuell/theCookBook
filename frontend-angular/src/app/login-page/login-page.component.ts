import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RecipeService } from '../recipe.service';
import { LoginRequest } from '../entities/loginRequest';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  service = inject(RecipeService);
    loginForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
    });


    onSubmit() {
      console.log("button clicked");
      console.log(this.loginForm.value);
      const loginRequest : LoginRequest = this.loginForm.value as LoginRequest;
    
      this.service.postLoginData(loginRequest).then((isSuccessfull) => {
        // TODO : if is not successfull show error message
        // TODO : if successfull route to startingpage
      })
    }
}
