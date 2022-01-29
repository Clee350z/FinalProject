import { TestBed } from '@angular/core/testing';

import { HikeReportService } from './hike-report.service';

describe('HikeReportService', () => {
  let service: HikeReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HikeReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
