import { Component, OnInit, ErrorHandler } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-custom-error',
  templateUrl: './custom-error.component.html',
  styleUrls: ['./custom-error.component.css']
})
export class CustomErrorComponent implements ErrorHandler {

  private error: any;

  constructor(private route: ActivatedRoute) { }
  
  handleError(error: any): void {
    throw new Error("Method not implemented.");
  }

  ngOnInit() {
    
  }

}
