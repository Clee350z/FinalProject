import { group } from '@angular/animations';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupHike } from 'src/app/models/group-hike';
import { GroupHikeComment } from 'src/app/models/group-hike-comment';
import { GroupHikeCommentService } from 'src/app/services/group-hike-comment.service';
import { GroupHikeComponent } from '../group-hike/group-hike.component';

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
  @Input() groupHike: GroupHike = new GroupHike();

  constructor(
    private ghcServ: GroupHikeCommentService,
    private router: Router,
    private route: ActivatedRoute,
    private tempGroupHike: GroupHikeComponent
  ) { }

  ngOnInit(): void {
    let groupHikeCommentIdStr = this.route.snapshot.paramMap.get('id');
    if (!this.selected && groupHikeCommentIdStr) {
      let groupHikeCommentId = Number.parseInt(groupHikeCommentIdStr);
      if ( !isNaN(groupHikeCommentId)) {
        this.ghcServ.show(groupHikeCommentId).subscribe({
          next: (ghc: GroupHikeComment | null) => {
            this.selected = ghc;
          },
          error: (fail: string) => {
            console.error('GroupHikeComponent.ngOnInit(): invalid groupHikeId' + fail);
            this.router.navigateByUrl("grouphikenotfound")
          }
        });
      } else {
        this.router.navigateByUrl('invalidGroupHike');
      }
    }
    this.reload();
  }

  reload(){
    this.ghcServ.showComments(this.groupHike.id).subscribe(
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
    this.ghcServ.showComments(this.groupHike.id).subscribe(
      {
        next: (groupHikeComments) => {
          this.groupHikeComments = groupHikeComments;
          this.reload();
        },
        error: (err) => {
          console.error('GroupHikeComp.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
    this.selected = groupHikeComment;
  }

  createGroupHikeComment(groupHikeComment: GroupHikeComment) {
    groupHikeComment.groupHike = this.groupHike;
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

  updateGroupHikeComment(groupHikeComment: GroupHikeComment, goToDetails = true): void {
    groupHikeComment.groupHike = this.groupHike;
    this.ghcServ.update(groupHikeComment).subscribe({
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
