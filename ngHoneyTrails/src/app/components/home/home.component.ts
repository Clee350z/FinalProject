import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailFiltersPipe } from 'src/app/pipes/trail-filters.pipe';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  trails : Trail[] = [];

  constructor(
    private trailSvc : TrailService,
    private trailfilter : TrailFiltersPipe
  ) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    this.trailSvc.random(3).subscribe(
      trails => {
        this.trails = trails;
      },

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trails');
        console.error(fail);
      }
    );
  }


}
