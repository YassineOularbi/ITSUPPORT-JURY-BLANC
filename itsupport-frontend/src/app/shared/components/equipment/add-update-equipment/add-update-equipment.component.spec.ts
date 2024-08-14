import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUpdateEquipmentComponent } from './add-update-equipment.component';

describe('AddUpdateEquipmentComponent', () => {
  let component: AddUpdateEquipmentComponent;
  let fixture: ComponentFixture<AddUpdateEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddUpdateEquipmentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddUpdateEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
