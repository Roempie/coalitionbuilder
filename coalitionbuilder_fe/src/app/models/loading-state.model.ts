export interface Loading {
  state: "loading";
}

export interface Loaded<T> {
  state: "loaded";
  data: T;
}

export interface Errored {
  state: "error";
  error: Error;
}

export type LoadingState<T = unknown> = Loading | Loaded<T> | Errored;
