import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ShowOnPathDirective } from '../../../directives/show-on-path.directive';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-breakdown-dynamic-header',
  standalone: true,
  imports: [
    CommonModule, 
    ShowOnPathDirective, 
    RouterLink,
    RouterModule
  ],
  templateUrl: './breakdown-dynamic-header.component.html',
  styleUrl: './breakdown-dynamic-header.component.css'
})
export class BreakdownDynamicHeaderComponent {

}
