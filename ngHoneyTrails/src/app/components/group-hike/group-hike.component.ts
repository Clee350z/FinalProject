import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { GroupHike } from 'src/app/models/group-hike';
import { GroupHikeService } from 'src/app/services/group-hike.service';

@Component({
  selector: 'app-group-hike',
  templateUrl: './group-hike.component.html',
  styleUrls: ['./group-hike.component.css']
})
export class GroupHikeComponent implements OnInit {

  selected: GroupHike | null = null;
  newGroupHike: GroupHike = new GroupHike();
  editGroupHike: GroupHike | null = null;
  groupHikes: GroupHike[] = [];
  addGroupHikeFormSelected: boolean = false;
  updateGroupHikeFormSelected: boolean = false;

  constructor(
    private ghServ: GroupHikeService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
     let groupHikeIdStr = this.route.snapshot.paramMap.get('id');
    if (!this.selected && groupHikeIdStr) {
      let groupHikeId = Number.parseInt(groupHikeIdStr);
      if ( !isNaN(groupHikeId)) {
        this.ghServ.show(groupHikeId).subscribe({
          next: (tournament: GroupHike | null) => {
            this.selected = tournament;
          },
          error: (fail: string) => {
            console.error('GroupHikeComponent.ngOnInit(): invalid groupHikeId' + fail);
            this.router.navigateByUrl("grouphikenotfound")
          }
        });
      } else {
        this.router.navigateByUrl('invalidGroupHike');
      }
    }
    this.reload();
  }

  reload(){
    this.ghServ.index().subscribe(
      {
        next: (groupHikes) => {
          this.groupHikes = groupHikes;
        },
        error: (err) => {
          console.error('GroupHikeComp.reload(): error getting reports');
          console.error(err);
        }
      }//end of object
    );
  }

  displayGroupHike(groupHike: GroupHike){
    this.selected = groupHike;

  }

  createGroupHike(groupHike: GroupHike) {
    this.ghServ.create(groupHike).subscribe({
      next: (gh) => {
        this.newGroupHike = new GroupHike();
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeComponent.createGroupHike(): error on create');
        console.error(fail);
      }
    })

  }

  setEditGroupHike() {
    this.editGroupHike = Object.assign({}, this.selected)
  }


  updateGroupHike(groupHike: GroupHike, goToDetails = true): void {
    this.ghServ.update(groupHike).subscribe({
      next: (gh) =>  {
        this.editGroupHike = null;
        if(goToDetails) {
          this.selected = gh;
        }
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeComponent.updateGroupHike(): error on update')
        console.error(fail);
      }
    });
  }

  deleteGroupHike(groupHikeId: number):void {
    this.ghServ.delete(groupHikeId).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error('GroupHikeComponent.deleteGroupHike(): error deleting groupHike');
      }
    })
  }
}
