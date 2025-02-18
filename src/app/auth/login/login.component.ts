import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';
import { JwtResponse } from '../jwt-response.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  template: `
    <h2>Login</h2>
    <div class="login-container">
      <form (ngSubmit)="onLogin()" class="login-form">
        <div class="field">
          <label for="email">Email:</label>
          <input
            [(ngModel)]="email"
            #emailInput="ngModel"
            id="email"
            name="email"
            type="email"
            required
            pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$"
            placeholder="Enter your email"
          />
          <small *ngIf="emailInput.invalid && emailInput.touched">
            <span *ngIf="emailInput.errors?.['required']"
              >Email is required.</span
            >
            <span *ngIf="emailInput.errors?.['pattern']"
              >Invalid email format.</span
            >
          </small>
        </div>
        <label for="password">Password:</label>
        <input
          [(ngModel)]="password"
          #passwordInput="ngModel"
          id="password"
          name="password"
          type="password"
          required
          pattern="^(?=.*[A-Za-z])(?=.*d)[A-Za-zd]{8,}$"
          placeholder="Enter your password"
        />

        <small *ngIf="passwordInput.invalid && passwordInput.touched">
          <span *ngIf="passwordInput.errors?.['required']"
            >Password is required.</span
          >
          <span *ngIf="passwordInput.errors?.['pattern']">
            Password must be at least 8 characters, include letters and numbers.
          </span>
        </small>

        <button type="submit">Login</button>
      </form>
    </div>
  `,
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}
  onLogin() {
    if (!this.email) {
      return;
    }

    const user = { email: this.email, password: this.password };
    this.authService.login(user).subscribe({
      next: (response) => {
        console.log('Response received:'); // Log the full response
        const token = response?.jwt; // Access the token using 'jwt' as the key
        if (token) {
          this.authService.storeToken(token); // Store the token in your service
          alert('Token received: ' + token); // Show the token in the alert
        } else {
          console.error('JWT not found in response');
        }
      },
      error: (error) => {
        alert('Required Valid Credentials');
        console.error('Login failed', error);
      },
    });
  }
}
