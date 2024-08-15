import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UserDynamicHeaderComponent } from "../../user/user-dynamic-header/user-dynamic-header.component";
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Equipment } from '../../../../core/models/equipment.model';
import { EquipmentService } from '../../../../core/services/equipment.service';
import { EquipmentStatus } from '../../../../core/enums/equipment-status.enum';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AssignEquipmentPopupComponent } from '../assign-equipment-popup/assign-equipment-popup.component';

@Component({
  selector: 'app-equipment-list',
  standalone: true,
  imports: [
    UserDynamicHeaderComponent,
    DatePipe,
    CurrencyPipe,
    CommonModule,
    RouterLink,
    AssignEquipmentPopupComponent
  ],
  templateUrl: './equipment-list.component.html',
  styleUrl: './equipment-list.component.css'
})
export class EquipmentListComponent implements OnInit{
  equipments: Equipment[] = [];
  EquipmentStatus = EquipmentStatus;
  equipmentType: string | null = null;
  isPopupOpen = false;
  selectedEquipmentId: number | null = null;
  @Output() assignEquipment = new EventEmitter<number>();

  constructor(private equipmentService: EquipmentService, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.equipmentType = params['type'];
      this.loadEquipments(this.equipmentType);
    });

    
  }



  OnDelete(id: any): void{
    this.equipmentService.deleteEquipment(id).subscribe({
      next: () => this.loadEquipments(this.equipmentType),
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    })
  }


  getImageUrl(imageName: string | undefined): string {
    return imageName ? `http://localhost:8080/images/${imageName}` : 'https://th.bing.com/th/id/OIP.P5ZiKujilpP0W1rBHWMa5AAAAA?rs=1&pid=ImgDetMain';
  }

  loadEquipments(equipmentType: string | null): void {

    switch (equipmentType) {
      case 'all':
        this.equipmentService.getAllEquipments().subscribe({
          next: (equipments) => {
            this.equipments = equipments;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'available':
        this.equipmentService.getAvailableEquipments().subscribe({
          next: (equipments) => {
            this.equipments = equipments;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'in-service':
        this.equipmentService.getInServiceEquipments().subscribe({
          next: (equipments) => {
            this.equipments = equipments;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
        case 'out-of-service':
          this.equipmentService.getAllEquipmentOutService().subscribe({
            next: (equipments) => {
              this.equipments = equipments;
            },
            error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
          });
          break;
          case 'broken-down':
            this.equipmentService.getBrokenDownEquipments().subscribe({
              next: (equipments) => {
                this.equipments = equipments;
              },
              error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
            });
            break;
      default:
        this.router.navigate(['/dashboard/error']);
        break;
    }
  }

  openAssignPopup(equipmentId: number): void {
    this.selectedEquipmentId = equipmentId;
    this.isPopupOpen = true;
  }

  closeAssignPopup(): void {
    this.isPopupOpen = false;
    this.selectedEquipmentId = null;
  }
}
