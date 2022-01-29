import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HikeReportCommentComponent } from './hike-report-comment.component';

describe('HikeReportCommentComponent', () => {
  let component: HikeReportCommentComponent;
  let fixture: ComponentFixture<HikeReportCommentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HikeReportCommentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HikeReportCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
