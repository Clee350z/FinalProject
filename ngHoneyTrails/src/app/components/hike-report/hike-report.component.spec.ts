import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HikeReportComponent } from './hike-report.component';

describe('HikeReportComponent', () => {
  let component: HikeReportComponent;
  let fixture: ComponentFixture<HikeReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HikeReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HikeReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
