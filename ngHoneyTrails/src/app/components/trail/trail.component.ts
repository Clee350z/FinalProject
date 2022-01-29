import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-trail',
  templateUrl: './trail.component.html',
  styleUrls: ['./trail.component.css']
})
export class TrailComponent implements OnInit {

  trails: Trail[] = [];
  selected : Trail | null = null;

  constructor(
    private trailSvc : TrailService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.reload();
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
        this.router.navigateByUrl('/trails/' + trail.id);
        this.selected = trail;
      },

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trail');
        console.error(fail);
      }
    );
  }

}
