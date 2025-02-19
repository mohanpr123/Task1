import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <h1><i>ANGULAR-APP</i></h1>
    <h2>
      <nav>
        <a routerLink="/login">LOGIN</a>
        <a routerLink="/register">REGISTER</a>
      </nav>
    </h2>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
}
