import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { GridOptions, ColDef } from 'ag-grid-community';
import { SharingSession } from 'src/app/shared/models/sharing-session.model';
import { ErrorHandler } from 'src/app/shared/services/error-handler';
import { SharingSessionService } from './sharing-session.service';

@Component({
  selector: 'app-sharing-session',
  templateUrl: './sharing-session.component.html',
  styleUrls: ['./sharing-session.component.css']
})
export class SharingSessionComponent implements OnInit {
  
  infoToDisplay: string;

  constructor(
    private sharingSessionService: SharingSessionService, 
    private translateService: TranslateService, 
    private errorHandler : ErrorHandler) {
    this.gridOptions = {
      defaultColDef: { sortable: true, filter: true, resizable: true },
      pagination: true,
      paginationPageSize: 10,
      onFirstDataRendered: this.sizeColumnsToFit
    }
  }
  
  sessions: SharingSession[];
  rowData: SharingSession[];
  gridOptions: GridOptions;
  columnDefs: ColDef[];

  ngOnInit() {
    this.getTableHeaderWithLang()
    this.translateService.onLangChange.subscribe(() => {
      this.getTableHeaderWithLang();
    })

    this.sharingSessionService.getAll().subscribe(
      (sessions : SharingSession[]) => {
        this.sessions = sessions;
        this.rowData = this.sessions;
        this.getTableHeaderWithLang();
      },
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log("error : " + message);
      }
    )
  }

  private getTableHeaderWithLang(): void {
    this.translateService.get('language').subscribe(() => {
     this.columnDefs = [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: this.translate('ag-grid.session.day'), field: 'day', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.session.startTime'), field: 'startTime', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.session.endTime'), field: 'endTime', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.session.subject'), field: 'subject.title', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.session.lecturer'), field: 'lecturer.username', sortable: true, filter: true }
      ]
    })
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }
}
