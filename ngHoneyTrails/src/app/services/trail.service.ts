import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';

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

  index(): Observable<Trail[]> {
    return this.http.get<Trail[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting exrcise list');
      })
    );
  };


}
