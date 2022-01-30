import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GroupHikeComment } from 'src/app/models/group-hike-comment';
import { GroupHikeCommentService } from 'src/app/services/group-hike-comment.service';

@Component({
  selector: 'app-group-hike-comment',
  templateUrl: './group-hike-comment.component.html',
  styleUrls: ['./group-hike-comment.component.css']
})
export class GroupHikeCommentComponent implements OnInit {

  selected: GroupHikeComment | null = null;
  newGroupHikeComment: GroupHikeComment = new GroupHikeComment();
  editGroupHikeComment: GroupHikeComment | null = null;
  groupHikeComments: GroupHikeComment [] = [];
  addGroupHikeComment: boolean = false;
  updateGroupHikeCommentSelected: boolean = false;

  constructor(
    private ghcServ: GroupHikeCommentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    this.ghcServ.index().subscribe(
      {
        next: (groupHikeComments) => {
          this.groupHikeComments = groupHikeComments;
        },
        error: (err) => {
          console.error('GroupHikeComp.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
  }

  displayGroupHikeComment(groupHikeComment: GroupHikeComment) {
    this.selected = groupHikeComment;
  }

  createGroupHikeComment(groupHikeComment: GroupHikeComment) {
    this.ghcServ.create(groupHikeComment).subscribe({
      next: (ghc) => {
        this.newGroupHikeComment = new GroupHikeComment();
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeCommentComponent.createGroupHikeComment(): error on update');
        console.error(fail);
      }
    })

  }

  setEditGroupHikeComment() {
    this.editGroupHikeComment = Object.assign({}, this.selected)
  }

  updateGroupHikeComment(groupHike: GroupHikeComment, goToDetails = true): void {
    this.ghcServ.update(groupHike).subscribe({
      next: (ghc) =>  {
        this.editGroupHikeComment = null;
        if(goToDetails) {
          this.selected = ghc;
        }
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeCommentComponent.updateGroupHike(): error on update')
        console.error(fail);
      }
    });
  }

  deleteGroupHikeComment(groupHikeCommentId: number):void {
    this.ghcServ.delete(groupHikeCommentId).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeCommentComponent.deleteGroupHike(): error deleting groupHike');
      }
    })
  }

}
