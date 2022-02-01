import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GroupHike } from '../models/group-hike';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GroupHikeService {
  private url = environment.baseUrl + 'api/grouphikes';
  private url2 = environment.baseUrl + 'api/trails'

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

  index():Observable<GroupHike[]>{
    return  this.http.get<GroupHike[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GroupHikeService.index(): error retrieving reports' + err)
        );
      })
    );
  }

  show(groupHikeId: number): Observable<GroupHike> {
    return this.http.get<GroupHike>(this.url + "/" + groupHikeId,).pipe(
      catchError( (error: any) => {
        console.error("GroupHikeService.show(): error finding GroupHike:");
        console.error(error);
        return throwError(
          () => new Error(
            "GroupHikeService.show(): error finding GroupHike: " + error
          )
        )
      })
    );
  }

  addUser(groupHike: GroupHike): Observable<GroupHike> {
    return this.http.post<GroupHike>(this.url2 + "/" + groupHike.trail.id + "/grouphikes/" + groupHike.id + "/users", groupHike, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error adding user to groupHike');
      })
    )
  }

  create(groupHike: GroupHike):Observable<GroupHike>{
    return this.http.post<GroupHike>(this.url2 + "/" + groupHike.trail.id + "/grouphikes", groupHike, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating groupHike');
      })
    )
  }

  update(groupHike: GroupHike):Observable<GroupHike>{
    return this.http.put<GroupHike>(this.url + "/" + groupHike.id, groupHike, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error updating groupHike');
      })
    )
  }

  hide(groupHike: GroupHike):Observable<GroupHike>{
    return this.http.put<GroupHike>(this.url + "/hide/" + groupHike.id, groupHike, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error updating groupHike');
      })
    )
  }

  delete(groupHikeId: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + groupHikeId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting groupHike');
      })
    )
  }

  getGroupHikesByTrailId(trailId : number): Observable <GroupHike[]> {
    return this.http.get<GroupHike[]>(this.url2 + '/' + trailId + '/hikereports').pipe(
      catchError((err: any) => {
        console.error('HikeReportService.show(): error retrieving report');
        console.error(err);
        return throwError(
          () =>
            new Error('HikeReportService.show(): err retrieving report' + err)
        );
      })
    );
  }
}
