import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';
import { Router } from '@angular/router';
import { Difficulty } from 'src/app/models/difficulty';
import { DifficultyService } from 'src/app/services/difficulty.service';
import { TrailCommentService } from 'src/app/services/trail-comment.service';
import { Trailcomment } from 'src/app/models/trailcomment';


@Component({
  selector: 'app-trail',
  templateUrl: './trail.component.html',
  styleUrls: ['./trail.component.css']
})
export class TrailComponent implements OnInit {

  trails: Trail[] = [];
  selected : Trail | null = null;
  newTrail : Trail = new Trail();
  addTrailFormSelected : boolean = false;
  difficulties : Difficulty[] = [];
  trailDetailsDropDown : boolean = false;
  leaveAComment : boolean = false;
  newTrailComment : Trailcomment = new Trailcomment();


  constructor(
    private trailSvc : TrailService,
    private router : Router,
    private difSvc : DifficultyService,
    private trlCmntSvc : TrailCommentService
  ) { }

  ngOnInit(): void {
    this.reload();
    this.getDifficultyList();
  }

  reload(){
    this.trailSvc.index().subscribe(
      trails => {
        this.trails = trails;
      },

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trails');
        console.error(fail);
      }
    );
  }

  viewTrailDetails(trailId : number){
    this.trailSvc.viewTrailDetails(trailId).subscribe(
      trail => {
        this.selected = trail;

      },

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trail');
        console.error(fail);
      }
    );

  }

  addTrail(newTrail : Trail) {
    this.trailSvc.createNewTrail(newTrail).subscribe(
      trail => {
        this.newTrail = new Trail();
        this.reload();
      },

      fail => {
        this.router.navigateByUrl('/login')
        console.error('TrailComponent.addTrail(): Error creating trail');
        console.error(fail);

      }
    );
    this.newTrail = new Trail();
    this.reload();
  }

  deleteTrail(trailId : number){
    this.trailSvc.delete(trailId).subscribe(
      success => {
        this.reload();
      },

      fail => {
        console.error('Error deleting trail');
        console.error(fail);
      }
    )
  }

  updateTrail(trail : Trail){
    this.trailSvc.update(trail).subscribe(
      success => {
        this.reload();
      },

      fail => {
        console.error('Error updating trail');
        console.error(trail);
      }
    )
  }

  getDifficultyList(){
    this.difSvc.index().subscribe(
      difficulties => {
        this.difficulties = difficulties;
      },

      wrong =>{
        console.error('TrailComponent.reload(): Error retreiving difficulties');
        console.error(wrong);
      }
    );
  }

  createTrailComment(newComment : Trailcomment, trail : Trail){
    this.trlCmntSvc.createNewTrailComment(newComment, trail.id).subscribe(
      comment => {
        this.newTrailComment = new Trailcomment();
        this.reload();
        trail.comments?.push(comment);
      },

      fail => {
        console.error('TrailComponent.reload(): Error creating comment');
        console.error(fail);
      }
      )
  }
}
