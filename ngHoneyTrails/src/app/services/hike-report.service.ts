import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HikeReport } from '../models/hike-report';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class HikeReportService {
  private url = environment.baseUrl + 'api/hikereports';
  private url2 = environment.baseUrl + 'api/trails';

  constructor(private http: HttpClient, private auth: AuthService) {}
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<HikeReport[]> {
    return this.http.get<HikeReport[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'HikeReportService.index(): error retrieving reports' + err
            )
        );
      })
    );
  }

  create(report: HikeReport): Observable<HikeReport> {
    return this.http
      .post<HikeReport>(this.url2 + "/" + report.trails.id + "/hikereports", report, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'HikeReportService.create(): error creating hike report ' + err
              )
          );
        })
      );
  }

  update(report: HikeReport): Observable<HikeReport> {
    return this.http.put<HikeReport>(this.url + "/" + report.id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'HikeReportService.update(): error updating hike report ' + err
            )
        );
      })
    );
  }

  destroy(reportId: number): Observable<void> {
    return this.http
      .delete<void>(`${this.url}/${reportId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.error(
            'HikeReportService.destroy(): error deleting hike report'
          );
          console.error(err);
          return throwError(
            () =>
              new Error(
                'HikeReportService.delete(): error deleting hike report' + err
              )
          );
        })
      );
  }

  show(reportId: number): Observable<HikeReport> {
    return this.http.get<HikeReport>(`${this.url}/${reportId}`).pipe(
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
