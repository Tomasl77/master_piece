import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomUserComponent } from './custom-user.component';

describe('AccountComponent', () => {
  let component: CustomUserComponent;
  let fixture: ComponentFixture<CustomUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
