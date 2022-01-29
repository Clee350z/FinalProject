import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupHikeCommentComponent } from './group-hike-comment.component';

describe('GroupHikeCommentComponent', () => {
  let component: GroupHikeCommentComponent;
  let fixture: ComponentFixture<GroupHikeCommentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupHikeCommentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupHikeCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
