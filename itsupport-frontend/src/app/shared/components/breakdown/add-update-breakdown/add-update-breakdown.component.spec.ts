import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUpdateBreakdownComponent } from './add-update-breakdown.component';

describe('AddUpdateBreakdownComponent', () => {
  let component: AddUpdateBreakdownComponent;
  let fixture: ComponentFixture<AddUpdateBreakdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddUpdateBreakdownComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddUpdateBreakdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
