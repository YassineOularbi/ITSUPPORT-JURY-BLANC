import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ClientService } from '../../../../core/services/client.service';
import { EquipmentService } from '../../../../core/services/equipment.service';
import { CommonModule } from '@angular/common';
import {  FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from '../../../../core/models/client.model';

@Component({
  selector: 'app-assign-equipment-popup',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './assign-equipment-popup.component.html',
  styleUrl: './assign-equipment-popup.component.css'
})
export class AssignEquipmentPopupComponent {
  @Input() equipmentId: number | null = null;
  @Output() close = new EventEmitter<void>();
  clients: Client[] = [];
  selectedClientId: number | null = null;

  constructor(
    private clientService: ClientService,
    private equipmentService: EquipmentService,
    private router: Router
  ) {}

  
  getImageUrl(imageName: string | undefined): string {
    return imageName ? `http://localhost:8080/images/${imageName}` : 'https://th.bing.com/th/id/OIP.P5ZiKujilpP0W1rBHWMa5AAAAA?rs=1&pid=ImgDetMain';
  }

  ngOnInit(): void {
    console.log(this.equipmentId)
    this.clientService.getAllClients().subscribe({
      next: (clients) => {
        this.clients = clients;
      },
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    });
  }

  closePopup(): void {
    this.close.emit();
  }

  onClientSelect(clientId: number): void {
    if (this.selectedClientId === clientId) {
      this.selectedClientId = null;
    } else {
      this.selectedClientId = clientId;
    }
    
  }

  assignEquipment(): void {
    if (this.selectedClientId && this.equipmentId) {
      this.equipmentService.assignEquipmentToClient(this.equipmentId.toString(), this.selectedClientId.toString()).subscribe({
        next: ()=>{
          this.router.navigateByUrl('/dashboard/equipments');
          this.closePopup();
        },
        error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
      });
    }
  }
}
