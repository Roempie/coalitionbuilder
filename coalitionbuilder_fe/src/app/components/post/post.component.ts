import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { catchError, delay, map, Observable, of, startWith, switchMap } from 'rxjs';
import { LoadingState } from 'src/app/models/loading-state.model';
import { DiscussionService } from 'src/app/services/discussion.service';
import { AsyncPipe, JsonPipe, NgFor,  } from '@angular/common';
import { Post } from 'src/app/models/discussion.model';
import { DateAgoPipe } from "../../pipes/date-ago.pipe";
import { CommentComponent } from "../comment/comment.component";

@Component({
    selector: 'app-post',
    standalone: true,
    templateUrl: './post.component.html',
    styleUrl: './post.component.css',
    imports: [AsyncPipe, JsonPipe, NgFor, DateAgoPipe, CommentComponent]
})
export class PostComponent implements OnInit {

  readonly route: ActivatedRoute = inject(ActivatedRoute)
  readonly discussionService: DiscussionService = inject(DiscussionService);
  public isReplying = false;
  public newCommentMessage = '';

  postRequest$: Observable<LoadingState<Post>> = new Observable<LoadingState<Post>>();

  ngOnInit(): void {
    this.postRequest$ = this.route.paramMap.pipe(
      switchMap(params => {
        const postId = Number(params.get('id')); 
        return this.discussionService.getPost(postId).pipe(
          map(data => ({ state: "loaded" as "loaded", data })),
          catchError(error => of({ state: "error" as "error", error })),
          startWith({ state: "loading" as "loading" })
        );
      })
    );
  }

  toggleReply() {
    this.isReplying = !this.isReplying;
  }

  submitComment(message: string, postId: number) {
    this.discussionService.postComment(message, postId, null).pipe(
       delay(50) // Delay for 1000 milliseconds (1 second)
    ).subscribe((data) => {
       this.isReplying = false;
       // After the delay, refresh the post data
       this.postRequest$ = this.discussionService.getPost(postId).pipe(
         map(data => ({ state: "loaded" as "loaded", data })),
         catchError(error => of({ state: "error" as "error", error })),
         startWith({ state: "loading" as "loading" })
       );
    });
   }
}
