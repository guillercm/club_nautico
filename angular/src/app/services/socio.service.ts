import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Socio } from 'src/entitys/Socio';
import { HttpClient } from  '@angular/common/http';
import { URL_API, headers } from './headers';

@Injectable({
  providedIn: 'root'
})
export class SocioService {

  private baseUrl = URL_API + 'socios/';

  constructor(private http: HttpClient) { }

  getSocio(id: number): Observable<Socio> {
    return this.http.get<Socio>(`${this.baseUrl}${id}`);
  }

  getSocios(): any {
    return this.http.get<any>(this.baseUrl, { headers });
  }

  addSocio(socio: Socio): Observable<Socio> {
    return this.http.post<Socio>(this.baseUrl, socio);
  }

  updateSocio(id: number, socio: Socio): Observable<Socio> {
    const body = socio;
    console.log(body);
    return this.http.put<Socio>(`${this.baseUrl}${id}`, body, { headers });
  }

  deleteSocio(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}${id}`, { responseType: 'text' });
  }
}
