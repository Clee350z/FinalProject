import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HikePhotoComponent } from './hike-photo.component';

describe('HikePhotoComponent', () => {
  let component: HikePhotoComponent;
  let fixture: ComponentFixture<HikePhotoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HikePhotoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HikePhotoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
