import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HikeReportComment } from '../models/hike-report-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HikeReportCommentService {
  private url = environment.baseUrl + 'api/hikereports'
  private url2 = environment.baseUrl + 'api/trails'

  constructor(private http:HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        'Authorization' : 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }
    }
    return options;
  }

  index(): Observable<HikeReportComment[]>{
    return this.http.get<HikeReportComment[]>(this.url + "/comments").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('HikeReportCommentService.index(): error retrieving reports' + err)
        );
      })
    );
  }

  show(reportCommentId: number): Observable<HikeReportComment> {
    return this.http.get<HikeReportComment>(this.url + "/" + reportCommentId,).pipe(
      catchError( (error: any) => {
        console.error("HikeReportComment.show(): error finding HikeReportComment:");
        console.error(error);
        return throwError(
          () => new Error(
            "HikeReportService.show(): error finding HikeReport: " + error
          )
        );
      })
    );
  }
  create(reportComment: HikeReportComment):Observable<HikeReportComment>{
    return this.http.post<HikeReportComment>(this.url2 + "/" + reportComment.hikeReport.trail.id + "/hikereports/" +reportComment.hikeReport.id + "/comments", reportComment, this.getHttpOptions()).pipe(
      catchError( (error: any) => {
        console.error("HikeReportComment.show(): error finding HikeReportComment:");
        console.error(error);
        return throwError(
          () => new Error(
            "HikeReportService.update(): error finding HikeReport: " + error
          )
        );
      })
    );
  }

  update(reportComment: HikeReportComment):Observable<HikeReportComment>{
    return this.http.put<HikeReportComment>(this.url2 + "/" + reportComment.hikeReport.trail.id + "/hikereports/" +reportComment.hikeReport.id + "/comments/" + reportComment.id, reportComment, this.getHttpOptions()).pipe(
      catchError( (error: any) => {
        console.error("HikeReportComment.show(): error finding HikeReportComment:");
        console.error(error);
        return throwError(
          () => new Error(
            "HikeReportService.update(): error updating HikeReport comment: " + error
          )
        );
      })
    );
  }

  destroy(reportComment: HikeReportComment): Observable<void> {
    return this.http.delete<void>(this.url2 + "/" + reportComment.hikeReport.trail.id + "/hikereports/" +reportComment.hikeReport.id + "/comments/" + reportComment.id, this.getHttpOptions()).pipe(
      catchError( (error: any) => {
        console.error("HikeReportComment.show(): error finding HikeReportComment:");
        console.error(error);
        return throwError(
          () => new Error(
            "HikeReportService.destroy(): error deleting HikeReport: " + error
          )
        );
      })
    );
  }
}
