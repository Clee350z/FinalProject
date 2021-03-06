import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GroupHike } from '../models/group-hike';
import { GroupHikeComment } from '../models/group-hike-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GroupHikeCommentService {
  private url = environment.baseUrl + 'api/grouphikes';

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

  index(): Observable<GroupHikeComment[]>{
    return  this.http.get<GroupHikeComment[]>(this.url + "/comments").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GroupHikeCommentService.index(): error retrieving reports' + err)
        );
      })
    );
  }

  showComments(groupHikeId: number): Observable<GroupHikeComment[]> {
    return  this.http.get<GroupHikeComment[]>(this.url + "/" + groupHikeId + "/comments").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GroupHikeCommentService.index(): error retrieving reports' + err)
        );
      })
    );
  }

  show(groupHikeCommentId: number): Observable<GroupHikeComment> {
    return this.http.get<GroupHikeComment>(this.url + "/" + groupHikeCommentId,).pipe(
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

  create(groupHikeComment: GroupHikeComment):Observable<GroupHikeComment>{
    return this.http.post<GroupHikeComment>(this.url + "/" + groupHikeComment.groupHike.id + "/comments", groupHikeComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error creating groupHikeComment');
      })
    )
  }

  update(groupHikeComment: GroupHikeComment):Observable<GroupHikeComment>{
    return this.http.put<GroupHikeComment>(this.url + "/" +groupHikeComment.groupHike.id + "/comments/" + groupHikeComment.id, groupHikeComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error updating groupHikeComment');
      })
    )
  }

  delete(groupHikeCommentId: number): Observable<void> {
    return this.http.delete<void>(this.url + "/comments/" + groupHikeCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting groupHikeComment');
      })
    )
  }
}
