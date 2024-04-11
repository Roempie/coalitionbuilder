import { AsyncPipe, NgFor } from '@angular/common';
import { Component, inject, Input, OnInit } from '@angular/core';
import { catchError, delay, map, Observable, of, startWith } from 'rxjs';
import { Comment } from 'src/app/models/discussion.model';
import { LoadingState } from 'src/app/models/loading-state.model';
import { DiscussionService } from 'src/app/services/discussion.service';
import { Post } from 'src/app/models/discussion.model';
import { DateAgoPipe } from '../../pipes/date-ago.pipe';

@Component({
  selector: 'app-comment',
  standalone: true,
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css',
  imports: [NgFor, AsyncPipe, DateAgoPipe],
})
export class CommentComponent implements OnInit {
  readonly discussionService: DiscussionService = inject(DiscussionService);
  public isOpen = false;
  public isReplying = false;
  public newCommentMessage = '';

  @Input() comment!: Comment;
  @Input() post!: Post;

  commentRequest$: Observable<LoadingState<Comment>> = new Observable<
    LoadingState<Comment>
  >();

  ngOnInit() {
    // console.log("comment: ", this.comment)
    // console.log("post`:", this.post)
  }

  toggleComments(commentId: number) {
    this.isOpen = !this.isOpen;

    if (this.isOpen) {
      this.commentRequest$ = this.discussionService.getComment(commentId).pipe(
        map((data) => ({ state: 'loaded' as 'loaded', data })),
        catchError((error) => of({ state: 'error' as 'error', error })),
        startWith({ state: 'loading' as 'loading' }),
      );
    } else {
      this.commentRequest$ = new Observable<LoadingState<Comment>>();
    }
  }

  toggleReply() {
    this.isReplying = !this.isReplying;
  }

  submitComment(commentId: number, message: string) {
    this.isReplying = false;
    console.log(this.post);
    this.discussionService
      .postComment(message, this.post.id, commentId)
      .pipe(delay(50))
      .subscribe((data) => {
        this.commentRequest$ = this.discussionService
          .getComment(commentId)
          .pipe(
            map((data) => ({ state: 'loaded' as 'loaded', data })),
            catchError((error) => of({ state: 'error' as 'error', error })),
            startWith({ state: 'loading' as 'loading' }),
          );
      });
  }
}
