import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInboxComponent } from './user-inbox.component';

describe('UserInboxComponent', () => {
  let component: UserInboxComponent;
  let fixture: ComponentFixture<UserInboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInboxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
