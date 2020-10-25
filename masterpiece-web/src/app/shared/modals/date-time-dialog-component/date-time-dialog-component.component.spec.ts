import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DateTimeDialogComponentComponent } from './date-time-dialog-component.component';

describe('DateTimeDialogComponentComponent', () => {
  let component: DateTimeDialogComponentComponent;
  let fixture: ComponentFixture<DateTimeDialogComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DateTimeDialogComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DateTimeDialogComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
