import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ErrorHighlightDirective } from '../../../directives/error-highlight.directive';
import { BreakdownPriority } from '../../../../core/enums/breakdown-priority.enum';
import { BreakdownService } from '../../../../core/services/breakdown.service';
import { BreakdownDto } from '../../../../core/dtos/breakdown-dto.model';
import { BreakdownType } from '../../../../core/enums/breakdown-type.enum';

@Component({
  selector: 'app-add-update-breakdown',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,
    ErrorHighlightDirective
  ],
  templateUrl: './add-update-breakdown.component.html',
  styleUrl: './add-update-breakdown.component.css'
})
export class AddUpdateBreakdownComponent {
  breakdownForm: FormGroup;
  isEditMode = false;
  breakdownId!: number;
  breakdownError: string | null = null;
  priorities = Object.values(BreakdownPriority);
  types = Object.values(BreakdownType);

  constructor(
    private fb: FormBuilder,
    private breakdownService: BreakdownService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.breakdownForm = this.fb.group({
      name: ['', [Validators.required]],
      description: [''],
      type: ['', [Validators.required]],
      priority: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.breakdownId = params['id'];
      if (this.breakdownId) {
        this.isEditMode = true;
        this.loadBreakdown(this.breakdownId.toString());
      }
    });
  }

  loadBreakdown(id: string): void {
    this.breakdownService.getBreakdownById(id).subscribe({
      next: (breakdown) => {
        this.breakdownForm.patchValue({
          name: breakdown.name,
          description: breakdown.description,
          type: breakdown.type,
          priority: breakdown.priority
        });
      },
      error: (error) => this.breakdownError = error.message
    });
  }

  onSubmit(): void {
    if (this.breakdownForm.invalid) {
      return;
    }

    const breakdownData: BreakdownDto = this.breakdownForm.value;

    if (this.isEditMode) {
      this.breakdownService.updateBreakdown(this.breakdownId.toString(), breakdownData).subscribe({
        next: () => this.router.navigate(['/dashboard/breakdowns']),
        error: (error) => this.breakdownError = error.message
      });
    } else {
      this.breakdownService.createBreakdown(breakdownData).subscribe({
        next: () => this.router.navigate(['/dashboard/breakdowns']),
        error: (error) => this.breakdownError = error.message
      });
    }
  }
}
