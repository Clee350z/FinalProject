import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HikeReport } from 'src/app/models/hike-report';
import { Trail } from 'src/app/models/trail';
import { HikeReportService } from 'src/app/services/hike-report.service';

@Component({
  selector: 'app-hike-report',
  templateUrl: './hike-report.component.html',
  styleUrls: ['./hike-report.component.css'],
})
export class HikeReportComponent implements OnInit {
  title = 'Hike Report';
  reports: HikeReport[] = [];
  selected: HikeReport | null = null;
  newReport: HikeReport | null = null;
  editReport: HikeReport | null = null;

  constructor(private HRptSvc: HikeReportService,
    private route: ActivatedRoute,
    private router: Router) {}

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

  addReport(report: HikeReport){
    // this.HRptSvc.create(this.newReport);
    // this.
  }

  displayReport(report: HikeReport){
    this.selected = report;
  }
  displayTable(){
    this.selected = null;
  }
}
