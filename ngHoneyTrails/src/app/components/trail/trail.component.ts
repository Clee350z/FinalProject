import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';
import { Router } from '@angular/router';
import { Difficulty } from 'src/app/models/difficulty';
import { DifficultyService } from 'src/app/services/difficulty.service';
import { TrailCommentService } from 'src/app/services/trail-comment.service';
import { Trailcomment } from 'src/app/models/trailcomment';
import { HikeReportService } from 'src/app/services/hike-report.service';
import { HikeReport } from 'src/app/models/hike-report';
import { ConditionService } from 'src/app/services/condition.service';
import { Condition } from 'src/app/models/condition';
import { GroupHikeService } from 'src/app/services/group-hike.service';
import { GroupHike } from 'src/app/models/group-hike';
import { Loader } from '@googlemaps/js-api-loader';
import { map } from 'rxjs';


@Component({
  selector: 'app-trail',
  templateUrl: './trail.component.html',
  styleUrls: ['./trail.component.css']
})
export class TrailComponent implements OnInit {

  trails: Trail[] = [];
  selected : Trail | null = null;
  newTrail : Trail = new Trail();
  trailMap : Trail = new Trail();
  addTrailFormSelected : boolean = false;
  difficulties : Difficulty[] = [];
  trailDetailsDropDown : boolean = false;
  leaveAComment : boolean = false;
  newTrailComment : Trailcomment = new Trailcomment();
  addFormReportSelected: boolean = false;
  newReport: HikeReport = new HikeReport();
  condition: Condition[] =[];
  selectedTrailHikeReports: HikeReport[] = [];
  selectedTrailGroupHikes: GroupHike [] = [];


  constructor(
    private trailSvc : TrailService,
    private router : Router,
    private difSvc : DifficultyService,
    private trlCmntSvc : TrailCommentService,
    private hRptServ: HikeReportService,
    private cServ: ConditionService,
    private grpHkSvc : GroupHikeService
  ) { }

  ngOnInit(): void {
    this.reload();
    this.getDifficultyList();
    this.populateCondition();
  }
/*----------------------------------------------------------------------------------------------------
    Gets a list of all Trails
-----------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------
    View Trail Details
-----------------------------------------------------------------------------------------------------*/

  viewTrailDetails(trailId : number){
    this.trailSvc.viewTrailDetails(trailId).subscribe(
      trail => {
        this.selected = trail;
        this.trailMap = trail;

        let loader = new Loader({
          apiKey: 'AIzaSyDHOH7qwK5gZYBdbYoWLS3PbPjVU3-pH4Q'
        })

        loader.load().then(() => {
          let map = new google.maps.Map(document.getElementById("map") as HTMLElement,
          {
            center: { lat: this.trailMap.latitude, lng: this.trailMap.longitude},
            zoom: 10
          })

           new google.maps.Marker({
            position: { lat: trail.latitude, lng: trail.longitude},
            map: map,
          });
        })

      },

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trail');
        console.error(fail);
      }
      );

  }

/*----------------------------------------------------------------------------------------------------
    Populates list of conditions for create Hike Report form
-----------------------------------------------------------------------------------------------------*/

  populateCondition(){
    this.cServ.index().subscribe({
      next: (c) => {
        this.condition = c;
      },
      error: (fail) => {
        console.error('Error on retrieval of conditions');}
    });
  }
/*----------------------------------------------------------------------------------------------------
    Creates a trail
-----------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------
    Creates Hike Report for Trail
-----------------------------------------------------------------------------------------------------*/

  addReport(report: HikeReport){
    if(this.selected){
      report.trail.id = this.selected.id;
    }
    this.hRptServ.create(report).subscribe({
      next: (report) => {
        this.newReport = new HikeReport();
        this.reload();
      },
      error:(fail) => {
        console.error('Error on creation');
      }
    });
  }

/*----------------------------------------------------------------------------------------------------
    Deletes Trail
-----------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------
    Updates Trail
-----------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------
    Populates a list of difficulties for create Trail form
-----------------------------------------------------------------------------------------------------*/

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

/*----------------------------------------------------------------------------------------------------
    Create a Trail Comment
-----------------------------------------------------------------------------------------------------*/

  createTrailComment(newComment : Trailcomment, trail : Trail){
    this.trlCmntSvc.createNewTrailComment(newComment, trail.id).subscribe(
      comment => {
        this.newTrailComment = new Trailcomment();
        this.reload();
        this.leaveAComment = false;
        trail.comments?.push(comment);
      },

      fail => {
        console.error('TrailComponent.reload(): Error creating comment');
        console.error(fail);
      }
      )
  }

/*----------------------------------------------------------------------------------------------------
    Set of Hike Reports for a specific Trail id
-----------------------------------------------------------------------------------------------------*/

  getHikeReportsForTrail(trailId : number){
    this.hRptServ.getHikeReportsByTrailId(trailId).subscribe(
      hikeReports => {
        this.selectedTrailHikeReports = hikeReports
      },

      fail => {
        console.error('TrailComponent.reload(): Error getting hike report for trail');
        console.error(fail);
      }
      )
  }

/*----------------------------------------------------------------------------------------------------
    Set of Group Hikes for a specific Trail id
-----------------------------------------------------------------------------------------------------*/

getGroupHikesForTrail(trailId : number){
  this.grpHkSvc.getGroupHikesByTrailId(trailId).subscribe(
    groupHikes => {
      this.selectedTrailGroupHikes = groupHikes;
    },

    fail => {
      console.error('TrailComponent.reload(): Error getting hike report for trail');
      console.error(fail);
    }
    )
}
}
