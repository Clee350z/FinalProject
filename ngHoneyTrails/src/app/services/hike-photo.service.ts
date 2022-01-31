import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HikePhoto } from '../models/hike-photo';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HikePhotoService {
  private url = environment.baseUrl + 'api/hikereports'

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        'Authorization' : 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }
    }
    return options;
  }

  index(): Observable<HikePhoto[]>{
    return this.http.get<HikePhoto[]>(this.url + "/photos").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikePhotoService.index(): error retrieving photos' + err)
        );
      })
    );
  }

  show(photoId: number): Observable<HikePhoto>{
    return this.http.get<HikePhoto>(this.url+ "/" + photoId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikePhotoService.create(): error retrieving photo' + err)
        );
      })
    );
  }

  create(reportPhoto: HikePhoto): Observable<HikePhoto>{
    return this.http.post<HikePhoto>(this.url + "/" + reportPhoto.hikeReport.id + "/photos", reportPhoto,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikePhotoService.create(): error retrieving photo' + err)
        );
      })
    );
  }

  update(reportPhoto: HikePhoto): Observable<HikePhoto> {
    return this.http.put<HikePhoto>(this.url + "/" + reportPhoto.hikeReport.id + "/photos/" + reportPhoto.id, reportPhoto,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikePhotoService.update(): error updating photo' + err)
        );
      })
    );
  }

  destroy(reportPhoto: HikePhoto): Observable<void>{
    return this.http.delete<void>(this.url + "/" + reportPhoto.hikeReport.id + "/photos/" + reportPhoto.id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikePhotoService.destroy(): error deleting photo' + err)
        );
      })
    );
  }
}
