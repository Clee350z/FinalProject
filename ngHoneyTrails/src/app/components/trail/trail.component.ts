import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service'


@Component({
  selector: 'app-trail',
  templateUrl: './trail.component.html',
  styleUrls: ['./trail.component.css']
})
export class TrailComponent implements OnInit {

  trails: Trail[] = [];

  constructor(
    private trailSvc : TrailService
  ) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    this.trailSvc.index().subscribe(
      trails => {
        this.trails = trails;
      },

      wrong =>{
        console.error('TrailComponent.reload(): Error retreiving trails');
        console.error(wrong);
      }
    );
  }

}
