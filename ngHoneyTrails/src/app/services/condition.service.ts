import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Condition } from '../models/condition';

@Injectable({
  providedIn: 'root'
})
export class ConditionService {
  private baseUrl = 'http://localhost:8086/';
  private url = this.baseUrl + 'api/trails/conditions';

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Condition[]> {
    return this.http.get<Condition[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'Condition.index(): error retrieving conditions' + err
            )
        );
      })
    );
  }
}
