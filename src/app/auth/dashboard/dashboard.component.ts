import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-dashboard',
  imports: [MatButton],
  template: `
    <p>Hey CALIBRAINERS , heres my Small GIFT 🎁!!!</p>
    <div class="container">
      <button
        mat-raised-button
        color="primary"
        type="submit"
        class="full-width"
        (click)="onLogout()"
      >
        Log-out
      </button>
    </div>
    <br />
    <img [src]="image" alt="This is random image" />
  `,
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  constructor(private router: Router) {}

  image = 'https://wallpapercave.com/wp/wp7640378.jpg';

  onLogout() {
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }
}
