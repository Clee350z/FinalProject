import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HikePhoto } from 'src/app/models/hike-photo';
import { HikeReport } from 'src/app/models/hike-report';
import { HikePhotoService } from 'src/app/services/hike-photo.service';
import { HikeReportService } from 'src/app/services/hike-report.service';

@Component({
  selector: 'app-hike-photo',
  templateUrl: './hike-photo.component.html',
  styleUrls: ['./hike-photo.component.css']
})
export class HikePhotoComponent implements OnInit {

  newPhoto: HikePhoto = new HikePhoto();
  editPhoto: HikePhoto | null = null;
  photoCollection: HikePhoto [] =[];
  selected: HikePhoto | null = null;
  hikeReports: HikeReport [] =[];
  addPhoto: boolean = false;
  @Input() hikeReport: HikeReport = new HikeReport() ;

  constructor(
    private photoSer: HikePhotoService,
    private repServ: HikeReportService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {let photoIdStr = this.route.snapshot.paramMap.get('id');
  if (!this.selected && photoIdStr) {
    let photoId = Number.parseInt(photoIdStr);
    if ( !isNaN(photoId)) {
      this.photoSer.show(photoId).subscribe({
        next: (photo: HikePhoto | null) => {
          this.selected = photo;
        },
        error: (fail: string) => {
          console.error('HikePhoto.ngOnInit(): invalid photoId' + fail);
          this.router.navigateByUrl("hikereportphotosnotfound")
        }
      });
    } else {
      this.router.navigateByUrl('invalidHikePhoto');
    }
  }
  this.populateHikeReport();
  this.reload();
  }

  reload(){
    this.photoSer.index().subscribe({
    next: (photoCollection) =>{
      this.photoCollection = photoCollection;
    },
    error: (err) => {
      console.error('HikePhoto.reload(): error getting photos');
      console.error(err);
    }
    });
  }

  populateHikeReport(){
    this.repServ.index().subscribe({
      next: (r) =>{
        this.hikeReports = r;
      },
      error:(fail) => {
        console.error('Error on retrieval of hike reports');
      }
    })
  }

  displayPhotoDetails(photoDetails: HikePhoto){
    this.selected = photoDetails;
  }

  createPhoto(photo: HikePhoto){
    photo.hikeReport =this.hikeReport;
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

  setEditPhoto() {
    this.editPhoto = Object.assign({}, this.selected)
  }

  updatePhoto(photo: HikePhoto, goToDetails = true){
    photo.hikeReport =this.hikeReport;
    this.photoSer.update(photo).subscribe({
      next: (photo)=> {
        this.editPhoto = null;
        if(goToDetails){
          this.selected = photo;
        }
        this.reload();
      },
      error: (fail) => {
        console.error('HikePhoto.updatePhoto(): error on update')
        console.error(fail);
      }
    });
  }

  deletePhoto(photo: HikePhoto): void{
    photo.hikeReport =this.hikeReport;
    if(this.selected){
      photo.hikeReport.id =this.selected.id;
    }
  this.photoSer.destroy(photo).subscribe({
    next: () => {
      this.reload();
    },
    error: (fail) => {
      console.error('HikePhoto.deletePhoto(): error deleting photo');
    }
  });
  }
}
