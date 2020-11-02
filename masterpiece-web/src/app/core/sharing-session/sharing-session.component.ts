import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SharingSessionService } from './sharing-session.service';

@Component({
  selector: 'app-sharing-session',
  templateUrl: './sharing-session.component.html',
  styleUrls: ['./sharing-session.component.css']
})
export class SharingSessionComponent implements OnInit {

  constructor(private sharingSessionService: SharingSessionService, private translateService: TranslateService) { }

  ngOnInit() {
    this.sharingSessionService.getAll().subscribe(
      result => console.log(result),
      error => console.log(error)
    )
  }

}
