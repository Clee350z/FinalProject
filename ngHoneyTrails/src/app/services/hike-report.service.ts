import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HikeReport } from '../models/hike-report';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HikeReportService {
private url = environment.baseUrl + 'api/hikereports';

  constructor(private http: HttpClient, private auth: AuthService) { }
  getHttpOptions() {
    let options = {
      headers: {
        'Authorization': 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
    }
  }
  return options;
}

index():Observable<HikeReport[]>{
  return  this.http.get<HikeReport[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('HikeReportService.index(): error retrieving reports' + err)
      );
    })
  );
}


show(reportId: number): Observable<HikeReport> {
return this.http.get<HikeReport>(`${this.url}/${reportId}`).pipe(
  catchError((err: any) => {
    console.error("HikeReportService.show(): error retrieving report");
    console.error(err);
    return throwError(
      () => new Error('HikeReportService.show(): err retrieving report' + err)
    );
  })
);
}
}
