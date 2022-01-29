import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupHikeComponent } from './group-hike.component';

describe('GroupHikeComponent', () => {
  let component: GroupHikeComponent;
  let fixture: ComponentFixture<GroupHikeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupHikeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupHikeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
