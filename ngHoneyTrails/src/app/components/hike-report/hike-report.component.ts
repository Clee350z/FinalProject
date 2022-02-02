import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Condition } from 'src/app/models/condition';
import { HikePhoto } from 'src/app/models/hike-photo';
import { HikeReport } from 'src/app/models/hike-report';
import { Trail } from 'src/app/models/trail';
import { User } from 'src/app/models/user';
import { ConditionService } from 'src/app/services/condition.service';
import { HikePhotoService } from 'src/app/services/hike-photo.service';
import { HikeReportService } from 'src/app/services/hike-report.service';
import { TrailService } from 'src/app/services/trail.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-hike-report',
  templateUrl: './hike-report.component.html',
  styleUrls: ['./hike-report.component.css'],
})
export class HikeReportComponent implements OnInit {
  title = 'Hike Report';
  reports: HikeReport[] = [];
  trails: Trail[] = [];
  condition: Condition[] =[];
  selected: HikeReport | null = null;
  newReport: HikeReport = new HikeReport();
  editReport: HikeReport | null = null;
  addFormPhotoSelected: boolean =false;
  newPhoto: HikePhoto = new HikePhoto();
  users: User[] = [];

  constructor(private HRptSvc: HikeReportService,
    private route: ActivatedRoute,
    private router: Router,
    private tServ: TrailService,
    private cServ: ConditionService,
    private photoSer: HikePhotoService,
    private uServ: UserService
    ) {}

  ngOnInit(): void {
    this.reload();

    let reportIdStr = this.route.snapshot.paramMap.get('id');
    if(!this.selected && reportIdStr){
      let reportId = Number.parseInt(reportIdStr);
      if(!isNaN(reportId)){
        this.HRptSvc.show(reportId).subscribe({
          next: (report) =>{
            this.selected = report;
          },
          error: (fail) => {
            console.error('Error retrieving report' + reportId);
            this.router.navigateByUrl('reportNotFound');
          }
        });
      }
      else{
        this.router.navigateByUrl('invalidReportId');

      }
    }
    // this.populateUsers();
    this.populateTrails();
    this.populateCondition();
  }
  reload() {
    this.HRptSvc.index().subscribe(
      {
        next: (reports) => {
          this.reports = reports;
        },
        error: (err) => {
          console.error('HikeReportComp.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
  }
  populateTrails(){
    this.tServ.index().subscribe({
      next: (t)=> {
        this.trails = t;
      },
      error:(fail) => {
        console.error('Error on retrieval of trails');
      }
    });
  }
  populateCondition(){
    this.cServ.index().subscribe({
      next: (c) => {
        this.condition = c;
      },
      error: (fail) => {
        console.error('Error on retrieval of conditions');}
    });
  }

  populateUsers() {
    this.uServ.index().subscribe({
      next: (t) => {
        this.users = t;
      },
      error: (fail) => {
        console.error('GroupHikeComponent.uServ.index(): error on populate users');
        console.error(fail);
      }
    })
  }


  setEditReport(){
    this.editReport = Object.assign({},this.selected);
  }

  updateReport(report: HikeReport, goToDetail = true): void{
  this.HRptSvc.update(report).subscribe({
    next: (report) =>{
      this.editReport = null;
      if(goToDetail){
        this.selected = report;
      }
      this.reload();
    },
    error:(fail) => {
      console.error('Error on update');
    }
  })
  }

  deleteReport(reportId: number): void{
    this.HRptSvc.destroy(reportId).subscribe({
      next: () =>{
        this.reload();
      },
      error:(fail) => {
        console.error('Error on deletion');
  }
    });

  }

  displayReport(report: HikeReport){
    this.selected = report;
  }
  displayTable(){
    this.selected = null;
  }

  createPhoto(photo: HikePhoto){
    if(this.selected){
      photo.hikeReport.id =this.selected.id;
    }
    this.photoSer.create(photo).subscribe({
      next: (photo)=>{
        this.newPhoto = new HikePhoto();
        this.reload();
      },
      error: (fail) => {
        console.error('HikePhoto.createPhoto(): error on creation');
        console.error(fail);
      }
    });
  }
}
