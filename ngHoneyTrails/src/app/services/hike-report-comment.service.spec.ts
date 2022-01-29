import { TestBed } from '@angular/core/testing';

import { HikeReportCommentService } from './hike-report-comment.service';

describe('HikeReportCommentService', () => {
  let service: HikeReportCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HikeReportCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
