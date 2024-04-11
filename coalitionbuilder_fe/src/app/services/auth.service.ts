import {inject, Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";


const AUTH_API = 'http://localhost:8080/api/v1/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http: HttpClient = inject(HttpClient);

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'authenticate',
      {
        email,
        password,
      },
      httpOptions
    );
  }

  register(email: string, password: string, firstname: string, lastname: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      {
        firstname: firstname,
        lastname: lastname,
        email: email,
        password: password,
      },
      httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', { }, httpOptions);
  }
}
