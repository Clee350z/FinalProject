import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
private url = environment.baseUrl + 'api/addresses';

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

index():Observable<Address[]>{
  return  this.http.get<Address[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () => new Error('AddressService.index(): error retrieving reports' + err)
      );
    })
  );
}

}
