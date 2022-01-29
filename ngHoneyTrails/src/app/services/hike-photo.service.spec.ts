import { TestBed } from '@angular/core/testing';

import { HikePhotoService } from './hike-photo.service';

describe('HikePhotoService', () => {
  let service: HikePhotoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HikePhotoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
