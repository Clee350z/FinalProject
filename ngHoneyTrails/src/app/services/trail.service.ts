import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TrailService {
  private baseUrl ='http://localhost:8086/';
  private url = this.baseUrl + 'api/trails/';

  constructor(
    private http: HttpClient,
    private authSvc: AuthService
  ) { }

  getHttpOptions(){
    let options = {
      headers : {
        Authorization : 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      }
    }
    return options;
  }

  index(): Observable<Trail[]> {
    return this.http.get<Trail[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting exrcise list');
      })
    );
  };

  viewTrailDetails(trailId : number) : Observable<Trail>{
    return this.http.get<Trail>(this.url + trailId)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting exrcise list');
      })
    );
  }

  createNewTrail(newTrail : Trail): Observable <Trail> {
    return this.http.post<Trail>(this.url, newTrail, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating trail');
      })
    )
  }




}
