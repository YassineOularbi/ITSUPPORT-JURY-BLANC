import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../core/services/admin.service';
import { ClientService } from '../../../../core/services/client.service';
import { TechnicianService } from '../../../../core/services/technician.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
  ],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: any[] = [];
  userType: string | null = null;


  constructor(
    private adminService: AdminService,
    private clientService: ClientService,
    private technicianService: TechnicianService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userType = params['type'];
      this.loadUsers(this.userType);
    });
  }

  getImageUrl(imageName: string | undefined): string {
    return imageName ? `http://localhost:8080/images/${imageName}` : 'https://th.bing.com/th/id/OIP.P5ZiKujilpP0W1rBHWMa5AAAAA?rs=1&pid=ImgDetMain';
  }
  

  private loadUsers(userType: string | null): void {
    switch (userType) {
      case 'admin':
        this.adminService.getAllAdmins().subscribe({
          next: users => {
            this.users = users;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'client':
        this.clientService.getAllClients().subscribe({
          next: users => {
            this.users = users;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'technician':
        this.technicianService.getAllTechnicians().subscribe({
          next: users => {
            this.users = users;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      default:
        this.router.navigate(['/dashboard/error']);
        break;
    }
  }

  OnDelete(id: string): void {
    switch (this.userType) {
      case 'admin':
        this.adminService.deleteAdmin(id).subscribe({
          next: () => this.loadUsers(this.userType),
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'client':
        this.clientService.deleteClient(id).subscribe({
          next: () => this.loadUsers(this.userType),
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'technician':
        this.technicianService.deleteTechnician(id).subscribe({
          next: () => this.loadUsers(this.userType), 
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      default:
        this.router.navigate(['/dashboard/error']);
        break;
    }
  }

}
