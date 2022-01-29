import { TestBed } from '@angular/core/testing';

import { GroupHikeCommentService } from './group-hike-comment.service';

describe('GroupHikeCommentService', () => {
  let service: GroupHikeCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupHikeCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
