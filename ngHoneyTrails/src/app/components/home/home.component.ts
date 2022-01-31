import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  trails : Trail[] = [];

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

      fail =>{
        console.error('TrailComponent.reload(): Error retreiving trails');
        console.error(fail);
      }
    );
  }

}
