import { Component, OnInit } from '@angular/core';
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
  // trails: Trail [] = [];
  selected: HikeReport | null = null;

  constructor(private HRptSvc: HikeReportService) {}

  ngOnInit(): void {
    this.reload();
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

  displayReport(report: HikeReport){
    this.selected = report;
  }
  displayTable(){
    this.selected = null;
  }
}
