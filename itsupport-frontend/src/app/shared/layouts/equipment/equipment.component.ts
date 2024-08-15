import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EquipmentDynamicHeaderComponent } from '../../components/equipment/equipment-dynamic-header/equipment-dynamic-header.component';

@Component({
  selector: 'app-equipment',
  standalone: true,
  imports: [
    RouterOutlet,
    EquipmentDynamicHeaderComponent
  ],
  templateUrl: './equipment.component.html',
  styleUrl: './equipment.component.css'
})
export class EquipmentComponent {

}
