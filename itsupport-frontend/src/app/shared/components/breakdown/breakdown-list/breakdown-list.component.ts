import { Component, OnInit } from '@angular/core';
import { Breakdown } from '../../../../core/models/breakdown.model';
import { BreakdownService } from '../../../../core/services/breakdown.service';
import { CommonModule } from '@angular/common';
import { BreakdownPriority } from '../../../../core/enums/breakdown-priority.enum';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-breakdown-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './breakdown-list.component.html',
  styleUrl: './breakdown-list.component.css'
})
export class BreakdownListComponent implements OnInit{
  breakdowns: Breakdown[] = [];
  selectedType: string | null = null;

  constructor(private breakdownService: BreakdownService, private router: Router, private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.selectedType = params['type'] || null;
      this.loadBreakdowns(this.selectedType);
    });
  }

  OnDelete(id: any): void{
    this.breakdownService.deleteBreakdown(id).subscribe({
      next: () => this.loadBreakdowns(this.selectedType),
      error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
    })
  }

  loadBreakdowns(type: string | null): void {
    console.log(type);
    
    switch (type) {
      case 'all':
      this.breakdownService.getAllBreakdowns().subscribe({
        next: (breakdowns) => {
          this.breakdowns = breakdowns;
        },
        error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
      });
      break;
      case 'network':
        this.breakdownService.getNetworkBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'software':
        this.breakdownService.getSoftwareBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'hardware':
        this.breakdownService.getHardwareBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'power':
        this.breakdownService.getPowerBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case 'peripheral':
        this.breakdownService.getPeripheralBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
      case null:
        this.breakdownService.getAllBreakdowns().subscribe({
          next: (breakdowns) => {
            this.breakdowns = breakdowns;
          },
          error: (error) => this.router.navigate(['/dashboard/error'], { queryParams: { message: error.message } })
        });
        break;
    }
  }

  getStars(priority: string): string[] {
    switch (priority) {
      case 'LOW':
        return [
          'assets/star-1.svg',
          'assets/star-0.svg',
          'assets/star-0.svg',
          'assets/star-0.svg',
          'assets/star-0.svg'
        ];
      case 'MEDIUM':
        return [
          'assets/star-1.svg',
          'assets/star-1.svg',
          'assets/star-1.svg',
          'assets/star-0.svg',
          'assets/star-0.svg'
        ];
      case 'HIGH':
        return [
          'assets/star-1.svg',
          'assets/star-1.svg',
          'assets/star-1.svg',
          'assets/star-1.svg',
          'assets/star-1.svg'
        ];
      default:
        return [];
    }
  }

  getImageForType(type: string): string {
    switch (type) {
      case 'NETWORK':
        return 'assets/network.jpg';
      case 'SOFTWARE':
        return 'assets/software.png';
      case 'HARDWARE':
        return 'assets/hardware.png';
        case 'POWER':
          return 'assets/power.png';
          case 'PERIPHERAL':
            return 'assets/peripheral.png';
      default:
        return '';
    }
  }
}
