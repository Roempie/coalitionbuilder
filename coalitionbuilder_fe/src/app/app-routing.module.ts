import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomeComponent} from "./components/home/home.component";
import {authGuard, unAuthGuard} from "./guards/auth.guard";
import {PostComponent} from "./components/post/post.component";

const routes: Routes = [
  { path: 'register', component: RegisterComponent, canActivate: [unAuthGuard]},
  { path: 'login', component: LoginComponent, canActivate: [unAuthGuard]},
  { path: 'home', component: HomeComponent, canActivate: [authGuard]},
  { path: 'post/:id', component: PostComponent, canActivate: [authGuard]},
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
