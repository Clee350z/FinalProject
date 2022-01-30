import { TestBed } from '@angular/core/testing';

import { GroupHikeService } from './group-hike.service';

describe('GroupHikeService', () => {
  let service: GroupHikeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupHikeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
