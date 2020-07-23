import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {

private baseUrl = "http://localhost:8000/subjects";
  
  constructor(private translate: TranslateService) { }

  ngOnInit() {
  }

}
