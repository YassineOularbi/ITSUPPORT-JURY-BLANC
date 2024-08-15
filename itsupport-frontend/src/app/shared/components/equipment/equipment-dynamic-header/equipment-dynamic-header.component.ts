import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ShowOnPathDirective } from '../../../directives/show-on-path.directive';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-equipment-dynamic-header',
  standalone: true,
  imports: [
    CommonModule, 
    ShowOnPathDirective, 
    RouterLink,
    RouterModule
  ],
  templateUrl: './equipment-dynamic-header.component.html',
  styleUrl: './equipment-dynamic-header.component.css'
})
export class EquipmentDynamicHeaderComponent {

}
