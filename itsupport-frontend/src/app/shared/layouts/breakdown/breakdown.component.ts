import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BreakdownDynamicHeaderComponent } from '../../components/breakdown/breakdown-dynamic-header/breakdown-dynamic-header.component';

@Component({
  selector: 'app-breakdown',
  standalone: true,
  imports: [
    RouterOutlet,
    BreakdownDynamicHeaderComponent
  ],
  templateUrl: './breakdown.component.html',
  styleUrl: './breakdown.component.css'
})
export class BreakdownComponent {

}
