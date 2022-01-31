import { TestBed } from '@angular/core/testing';

import { TrailCommentService } from './trail-comment.service';

describe('TrailCommentService', () => {
  let service: TrailCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrailCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
