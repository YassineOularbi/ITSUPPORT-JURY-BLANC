import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HideShowPasswordDirective } from '../../../directives/hide-show-password.directive';
import { ErrorHighlightDirective } from '../../../directives/error-highlight.directive';
import { EquipmentService } from '../../../../core/services/equipment.service';

@Component({
  selector: 'app-add-update-equipment',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,
    HideShowPasswordDirective,
    ErrorHighlightDirective
  ],
  templateUrl: './add-update-equipment.component.html',
  styleUrls: ['./add-update-equipment.component.css']
})
export class AddUpdateEquipmentComponent implements OnInit {
  equipmentForm: FormGroup;
  equipmentError: string | null = null;
  imagePreviewUrl: string | ArrayBuffer | null = null;
  isEditMode: boolean = false;
  equipmentId!: string;

  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>;

  constructor(
    private fb: FormBuilder,
    private equipmentService: EquipmentService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.equipmentForm = this.fb.group({
      name: ['', Validators.required],
      model: ['', Validators.required],
      category: ['', Validators.required],
      purchasePrice: [null, [Validators.required, Validators.min(0)]],
      serialNumber: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.equipmentId = params['id'];
      if (this.equipmentId) {
        this.isEditMode = true;
        this.loadEquipmentData(this.equipmentId);
      }
    });

    this.equipmentForm.valueChanges.subscribe(() => {
      this.equipmentError = null;
    });
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagePreviewUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  loadEquipmentData(equipmentId: string): void {
    this.equipmentService.getEquipmentById(equipmentId).subscribe({
      next: (equipment) => {
        this.equipmentForm.patchValue(equipment);
        this.imagePreviewUrl = equipment.photoUrl ? `http://localhost:8080/images/${equipment.photoUrl}` : null;
      },
      error: (error) => {
        this.equipmentError = 'Failed to load equipment data';
        console.error('Load equipment error:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.equipmentForm.valid) {
      const formData = new FormData();

      formData.append('name', this.equipmentForm.get('name')?.value);
      formData.append('model', this.equipmentForm.get('model')?.value);
      formData.append('category', this.equipmentForm.get('category')?.value);
      formData.append('purchasePrice', this.equipmentForm.get('purchasePrice')?.value);
      formData.append('serialNumber', this.equipmentForm.get('serialNumber')?.value);

      if (this.fileInput.nativeElement.files && this.fileInput.nativeElement.files.length > 0) {
        formData.append('picture', this.fileInput.nativeElement.files[0]);
      }

      if (this.isEditMode) {
        this.equipmentService.updateEquipment(this.equipmentId, formData).subscribe({
          next: () => {
            this.router.navigateByUrl('/dashboard/equipments');
          },
          error: (error) => {
            this.equipmentError = 'Failed to update equipment';
            console.error('Update equipment error:', error);
          }
        });
      } else {
        this.equipmentService.createEquipment(formData).subscribe({
          next: () => {
            this.router.navigateByUrl('/dashboard/equipments');
          },
          error: (error) => {
            this.equipmentError = 'Failed to create equipment';
            console.error('Create equipment error:', error);
          }
        });
      }
    }
  }
}