import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HikeReport } from 'src/app/models/hike-report';
import { HikeReportComment } from 'src/app/models/hike-report-comment';
import { User } from 'src/app/models/user';
import { HikeReportCommentService } from 'src/app/services/hike-report-comment.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-hike-report-comment',
  templateUrl: './hike-report-comment.component.html',
  styleUrls: ['./hike-report-comment.component.css']
})
export class HikeReportCommentComponent implements OnInit {

  selected: HikeReportComment | null = null;
  newReportComment: HikeReportComment = new HikeReportComment();
  editReportComment: HikeReportComment | null = null;
  reportComments: HikeReportComment [] = [];
  addReportComment: boolean = false;
  updateReportCommentSelected: boolean = false;
  @Input() hikeReport: HikeReport = new HikeReport() ;
  users: User[] = [];

  constructor(
    private hRComServ: HikeReportCommentService,
    private router: Router,
    private route: ActivatedRoute,
    private uServ: UserService
  ) { }

  ngOnInit(): void {
    let reportCommentIdStr = this.route.snapshot.paramMap.get('id');
    if (!this.selected && reportCommentIdStr) {
      let reportCommentId = Number.parseInt(reportCommentIdStr);
      if ( !isNaN(reportCommentId)) {
        this.hRComServ.show(reportCommentId).subscribe({
          next: (comment: HikeReportComment | null) => {
            this.selected = comment;
          },
          error: (fail: string) => {
            console.error('HikeReportComment.ngOnInit(): invalid reportId' + fail);
            this.router.navigateByUrl("hikereportnotfound")
          }
        });
      } else {
        this.router.navigateByUrl('invalidHikeReport');
      }
    }
    // this.populateUsers();
    this.reload();
  }

  reload(){
    this.hRComServ.showComments(this.hikeReport.id).subscribe(
      {
        next: (hikeReportComments) => {
          this.reportComments = hikeReportComments;
        },
        error: (err) => {
          console.error('HikeReportComment.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
  }
  populateUsers() {
    this.uServ.index().subscribe({
      next: (t) => {
        this.users = t;
      },
      error: (fail) => {
        console.error('HikeReportComment.uServ.index(): error on populate users');
        console.error(fail);
      }
    })
  }

  displayHikeReportComment(hikeReportComment: HikeReportComment) {
    this.hRComServ.showComments(this.hikeReport.id).subscribe(
      {
        next: (hikeReportComments) => {
          this.reportComments = hikeReportComments;
        },
        error: (err) => {
          console.error('GroupHikeComp.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
    this.selected = hikeReportComment;
  }

  createReportComment(reportComment: HikeReportComment) {
    reportComment.hikeReport = this.hikeReport;
    // console.log(reportComment);
    this.hRComServ.create(reportComment).subscribe({
      next: (comment) => {
        this.newReportComment = new HikeReportComment();
        this.reload();
      },
      error: (fail) => {
        console.error('HikeReportComment.createReportComment(): error on creation');
        console.error(fail);
      }
    })

  }

  setEditReportComment() {
    this.editReportComment = Object.assign({}, this.selected)
  }

  updateReportComment(reportComment: HikeReportComment, goToDetails = true): void {
    reportComment.hikeReport = this.hikeReport;
    this.hRComServ.update(reportComment).subscribe({
      next: (comment) =>  {
        this.editReportComment = null;
        if(goToDetails) {
          this.selected = comment;
        }
        this.reload();
      },
      error: (fail) => {
        console.error('HikeReportComment.updateReportComment(): error on update')
        console.error(fail);
      }
    });
  }

  deleteReportComment(reportComment: HikeReportComment):void {
    reportComment.hikeReport = this.hikeReport;
    this.hRComServ.destroy(reportComment).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error('HikeReportComment.deleteReportComment(): error deleting comment');
      }
    })
  }

}


