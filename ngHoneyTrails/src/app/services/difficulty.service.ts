import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Difficulty } from '../models/difficulty';

@Injectable({
  providedIn: 'root'
})
export class DifficultyService {
  private baseUrl ='http://localhost:8086/';
  private url = this.baseUrl + 'api/trails/difficulties';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Difficulty[]> {
    return this.http.get<Difficulty[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting exrcise list');
      })
    );
  };
}
