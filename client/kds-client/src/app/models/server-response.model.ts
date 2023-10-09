export interface Response<T> {
  timestamp?: number;
  status: number;
  error: null | string;
  body: null | T;
  path?: string;
}
