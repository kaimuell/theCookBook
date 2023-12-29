import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
    loginForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
    });

    onSubmit() {
      
      // TODO: Use EventEmitter with form value
      console.warn(this.loginForm.value);
    }
}
