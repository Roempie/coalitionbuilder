import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import {StorageService} from "../services/storage.service";
import {inject} from '@angular/core';

@Injectable()
export class JwtInterceptor implements HttpInterceptor 
{

  readonly storageService: StorageService = inject(StorageService);

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> 
  {  
    if (this.storageService.isLoggedIn()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.storageService.getToken()}`
        }
      });
    }
    return next.handle(request);
  }
 }
