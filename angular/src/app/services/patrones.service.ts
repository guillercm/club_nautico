import { Injectable } from '@angular/core';
import { URL_API, headers } from './headers';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PatronesService {

  private baseUrl = URL_API + 'patrones/';

  constructor(private http: HttpClient) { }

  getPatrones(): any {
    return this.http.get<any>(this.baseUrl, { headers });
  }

}
