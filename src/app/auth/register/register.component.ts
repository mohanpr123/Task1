import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  template: `
    <h2>Register</h2>
    <div class="register-container">
      <form (ngSubmit)="onRegister()" class="register-form">
        <label for="username">Username:</label>
        <input
          [(ngModel)]="username"
          name="username"
          required
          placeholder="Enter your username"
        />

        <label for="emaill">Email:</label>
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
        <label for="confirmPassword">Confirm Password:</label>
        <input
          [(ngModel)]="confirmpassword"
          (ngModelChange)="checkPasswords()"
          name="confirmPassword"
          type="password"
          required
          placeholder="Confirm your password"
        />

        <div *ngIf="passwordMismatch" class="error-message">
          Passwords do not match.
        </div>

        <button type="submit">Register</button>
      </form>
    </div>
  `,
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  username: string = '';
  email: string = '';
  password: string = '';
  confirmpassword: string = '';
  passwordMismatch: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  checkPasswords() {
    this.passwordMismatch = this.confirmpassword !== this.password;
  }

  onRegister() {
    if (this.passwordMismatch) {
      alert('Password do not match');
      return;
    }

    const user = {
      username: this.username,
      email: this.email,
      password: this.password,
    };

    this.authService.register(user).subscribe({
      next: (Response) => {
        console.log('Registration done');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.log('Registration failed');
      },
    });

    //   () => {
    //     this.router.navigate(['/login']);
    //   },
    //   (error) => {
    //     console.error('Registration failed', error);
    //   }
  }
}
