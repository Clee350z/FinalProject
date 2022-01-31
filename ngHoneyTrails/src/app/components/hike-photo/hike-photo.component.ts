import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HikePhoto } from 'src/app/models/hike-photo';
import { HikePhotoService } from 'src/app/services/hike-photo.service';

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

  constructor(
    private photoSer: HikePhotoService,
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

  displayPhotoDetails(photoDetails: HikePhoto){
    this.selected = photoDetails;
  }

  createPhoto(photo: HikePhoto){
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

  updatePhoto(photo: HikePhoto, goToDetails = true){
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
