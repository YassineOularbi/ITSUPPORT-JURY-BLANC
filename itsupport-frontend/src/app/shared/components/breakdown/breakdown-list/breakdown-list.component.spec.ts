import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreakdownListComponent } from './breakdown-list.component';

describe('BreakdownListComponent', () => {
  let component: BreakdownListComponent;
  let fixture: ComponentFixture<BreakdownListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BreakdownListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BreakdownListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
