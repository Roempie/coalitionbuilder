export interface Post {
  id: number;
  title: string;
  description: string;
  creationDate: Date;
  author: string;
  rootComments: Comment[];
}

export interface Comment {
  id: number;
  author: string;
  message: string;
  creationDate: Date;
  numChildComments: number;
  childComments: Comment[];
}
