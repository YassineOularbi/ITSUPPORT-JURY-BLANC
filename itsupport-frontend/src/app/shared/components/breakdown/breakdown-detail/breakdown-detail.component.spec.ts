import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreakdownDetailComponent } from './breakdown-detail.component';

describe('BreakdownDetailComponent', () => {
  let component: BreakdownDetailComponent;
  let fixture: ComponentFixture<BreakdownDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BreakdownDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BreakdownDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
