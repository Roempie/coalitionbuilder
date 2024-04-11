import {Component, inject, OnInit} from '@angular/core';
import {StorageService} from "../../services/storage.service";
import {FormsModule} from "@angular/forms";
import {DiscussionService} from "../../services/discussion.service";
import {catchError, map, Observable, of, startWith} from "rxjs";
import {AsyncPipe, JsonPipe, NgForOf, NgTemplateOutlet} from '@angular/common';
import { LoadingState } from 'src/app/models/loading-state.model';
import {NgIf} from "@angular/common";
import { DateAgoPipe } from 'src/app/pipes/date-ago.pipe';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/discussion.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgForOf,
    AsyncPipe,
    JsonPipe,
    DateAgoPipe
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  readonly storageService: StorageService = inject(StorageService);
  readonly discussionService: DiscussionService = inject(DiscussionService);
  readonly router: Router = inject(Router);

  postsRequest$: Observable<LoadingState<Post[]>> = this.discussionService.getPosts().pipe(
    map(data => ({ state: "loaded" as "loaded", data })), 
    catchError(error => of({ state: "error" as "error", error })), 
    startWith({ state: "loading" as "loading"})
  );
  
  selectPost(postId: number): void {
    this.router.navigate(['/post', postId]);
  }
}
