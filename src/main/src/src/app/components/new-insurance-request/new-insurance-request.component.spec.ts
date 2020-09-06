import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewInsuranceRequestComponent } from './new-insurance-request.component';

describe('NewInsuranceRequestComponent', () => {
  let component: NewInsuranceRequestComponent;
  let fixture: ComponentFixture<NewInsuranceRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewInsuranceRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewInsuranceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
