import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SharingSessionComponent } from './sharing-session.component';

describe('SharingSessionComponent', () => {
  let component: SharingSessionComponent;
  let fixture: ComponentFixture<SharingSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SharingSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SharingSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
