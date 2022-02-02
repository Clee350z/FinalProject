import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { GroupHike } from 'src/app/models/group-hike';
import { Trail } from 'src/app/models/trail';
import { User } from 'src/app/models/user';
import { GroupHikeService } from 'src/app/services/group-hike.service';
import { TrailService } from 'src/app/services/trail.service';
import { UserService } from 'src/app/services/user.service';

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
  updateGroupHikeFormSelected: boolean  = false;
  trails: Trail [] = [];
  users: User [] = [];

  constructor(
    private ghServ: GroupHikeService,
    private router: Router,
    private route: ActivatedRoute,
    private tServ: TrailService,
    private uServ: UserService
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
    // this.populateUsers();
    this.populateTrails();
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

  addUserToGroupHike(groupHike: GroupHike) {
    console.log(groupHike);
    this.ghServ.addUser(groupHike).subscribe({
      next: (gh) => {
        this.selected = gh;

      },
      error: (fail) => {
        console.error('GroupHikeComponent.tServ.addUser(): error on adding user');
        console.error(fail);
      }
    })
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

  populateTrails() {
    this.tServ.index().subscribe({
      next: (t) => {
        this.trails = t;
      },
      error: (fail) => {
        console.error('GroupHikeComponent.tServ.index(): error on populate trails');
        console.error(fail);
      }
    })
  }

  createGroupHike(groupHike: GroupHike) {
    this.ghServ.create(groupHike).subscribe({
      next: (gh) => {
        this.addGroupHikeFormSelected = false;
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

  hideGroupHike(groupHike: GroupHike, goToDetails = true): void {
    this.ghServ.hide(groupHike).subscribe({
      next: (gh) =>  {

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
