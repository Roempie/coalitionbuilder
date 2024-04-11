import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {StorageService} from "./services/storage.service";
import {AuthService} from "./services/auth.service";
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {JwtInterceptor} from "./interceptor/jwt.interceptor";
import {NavbarComponent} from "./components/navbar/navbar.component";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NavbarComponent
  ],
  providers: [AuthService, StorageService,     {
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
