import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Comment, Post} from "../models/discussion.model";


const API_URL = 'http://localhost:8080/api/v1/discussion';

@Injectable({
  providedIn: 'root',
})
export class DiscussionService {

  readonly http: HttpClient = inject(HttpClient)

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(
      `${API_URL}/posts`
    );
  }

  getComment(commentId: number): Observable<Comment> {
    return this.http.get<Comment>(`${API_URL}/comments/${commentId}`);
  }

  getPost(postId: number): Observable<Post> {
    return this.http.get<Post>(`${API_URL}/posts/${postId}`);
  }

  postComment(message: string, postId: number, parentCommentId: number | null): Observable<Comment> {
    return this.http.post<Comment>(`${API_URL}/comments`, {
      message: message,
      postId: postId,
      parentCommentId: parentCommentId
    });
  }
}
