import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Trail } from '../models/trail';
import { Trailcomment } from '../models/trailcomment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TrailCommentService {
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

  createNewTrailComment(newTrailComment : Trailcomment, trailId : number): Observable <Trailcomment> {
    return this.http.post<Trailcomment>(this.url + '/' + trailId + '/comments', newTrailComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating trail comment');
      })
    )
  }
}
