export class CustomError {
    httpStatus : any;
    message : string;
    errors : string[];

   constructor(httpStatus : any, message : string, errors : string[]) {
    this.httpStatus = httpStatus,
    this.message = message,
    this.errors = errors
   }
}