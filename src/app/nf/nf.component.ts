import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
@Component({
  selector: 'app-nf',
  imports: [MatIcon],
  template: `<div class="container">
    <p>404 NOT FOUND <mat-icon [inline]="true">highlight_off</mat-icon></p>
  </div>`,
  styleUrl: './nf.component.css',
})
export class NfComponent {}
