import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TrailService {
  private url = environment.baseUrl + 'api/trails';

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
        return throwError('Error getting trail list');
      })
    );
  };

  viewTrailDetails(trailId : number) : Observable<Trail>{
    return this.http.get<Trail>(this.url + "/" + trailId)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting trail list');
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

  delete(trailId: number) {
    return this.http.delete(this.url + '/' + trailId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('failed to delete trail');
      })
    )
  }

  update(trail: Trail) {
    return this.http.put<Trail>(this.url + '/' + trail.id, trail).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('failed to update trail');
      })
    )

  }




}
