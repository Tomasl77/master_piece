import { Component, OnInit, OnDestroy } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { Subscription } from 'rxjs';
import { ColDef, GridOptions } from 'ag-grid-community';
import { DatePipe } from '@angular/common';

import { SubjectService } from './subject.service';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { Subject } from '../../shared/models/subject.model';
import { ConfirmationModalComponent } from 'src/app/shared/modals/confirmation-modal/confirmation-modal.component';
import { BtnCellRenderer } from 'src/app/shared/btn-cell-renderer.component';
import { DateTimeDialogComponentComponent } from 'src/app/shared/modals/date-time-dialog-component/date-time-dialog-component.component';
import { ErrorHandler } from 'src/app/shared/services/error-handler';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService, ConfirmationModalComponent]
})
export class SubjectComponent implements OnInit, OnDestroy {

  action: string;
  errorReturned : string;
  tomorrow: Date;

  formErrors = {
    'title': '',
    'description': ''
  }
  subjects: Subject[];
  rowData: Subject[];
  gridOptions: GridOptions;
  columnDefs: ColDef[];
  frameworkComponents = {};

  private getAllSubjectsSubscription: Subscription;
  private deleteSubjectSubscription: Subscription;
  private postSubjectSubscription: Subscription;
  private presentSubjectSubscription: Subscription;

  subjectForm: FormGroup;
  categories = [
    { name: "Front-End", value: "FRONTEND" },
    { name: "Back-End", value: "BACKEND" },
    { name: "Database", value: "DATABASE" },
    { name: "Rift", value: "RIFT" },
    { name: "Other", value: "OTHER" }
  ];

  constructor(
    private translateService: TranslateService,
    private formBuilder: FormBuilder,
    private subjectService: SubjectService,
    private activatedRoute: ActivatedRoute,
    private router : Router,
    private authenthicationService: AuthenticationService,
    private datePipe : DatePipe,
    private dialog: MatDialog  ) {
    this.subjectForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(30)]],
      description: ['', [Validators.required]],
      category: ['', [Validators.required]]
    }),

      /* ag-grid */
      this.gridOptions = {
        defaultColDef: { sortable: true, filter: true },
        pagination: true,
        paginationPageSize: 10,
        onFirstDataRendered: this.sizeColumnsToFit
      },
      this.frameworkComponents = {
        btnCellRenderer: BtnCellRenderer
      }
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }


  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.action = params["action"];
      this.customInit();
      this.getTableHeaderWithLang();
      this.translateService.onLangChange.subscribe(() => {
        this.getTableHeaderWithLang();
      })
    });
    this.tomorrow = new Date();
  }

  ngOnDestroy() {
    this.subjectService.unsubscribe(this.deleteSubjectSubscription);
    this.subjectService.unsubscribe(this.getAllSubjectsSubscription);
    this.subjectService.unsubscribe(this.postSubjectSubscription);
    this.subjectService.unsubscribe(this.presentSubjectSubscription);
  }

  customInit() {
    this.getSubjectsIfVotePanel();

  }

  public getSubjectsIfVotePanel() {
    if (this.action === "vote") {
      this.subjectService.getAllSubject().subscribe(
        (subjects: Subject[]) => {
          this.subjects = subjects;
          this.rowData = subjects;
          console.log(this.rowData);
        });
    }
  }

  logValidationErrors(group: FormGroup = this.subjectForm): void {
    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.get(key);
      if (abstractControl instanceof FormGroup) {
        this.logValidationErrors(abstractControl)
      } else {
        this.formErrors[key] = '';
        if (abstractControl && !abstractControl.valid && ((abstractControl.touched) || (abstractControl.dirty))) {
          for (const errorKey in abstractControl.errors) {
            if (errorKey) {
              const err = this.translateService.instant('subject.validationMessages.' + key + '.' + errorKey);
              this.formErrors[key] += err;
            }
          };
        }
      }
    })
  }

  public postSubject() {
    const request = this.subjectService.postSubject(this.subjectForm);
    this.postSubjectSubscription = request.subscribe(
      () => {
        this.subjectForm.reset(),
          this.action = "vote",
          this.getSubjectsIfVotePanel()
      },
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log("message : " + message);
      }
    );
  }

  public deleteSubject(id: number) {
    const request = this.subjectService.deleteSubject(id);
    this.deleteSubjectSubscription = request.subscribe(
      () => this.getSubjects(),
      (error) => console.log(error)
    )
  }

  getSubjects() {
    const request = this.subjectService.getAllSubject()
    this.getAllSubjectsSubscription = request.subscribe(
      (subjects: Subject[]) => {
        this.rowData = subjects;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  private getTableHeaderWithLang(): void {
    this.translateService.get('language').subscribe(() => {
      this.columnDefs = [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: this.translate('ag-grid.subject.title'), field: 'title', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.description'), field: 'description', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.category'), field: 'category', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.vote'), field: 'vote', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.requester'), field: 'user', sortable: true, filter: true },
        {
          sortable: false,
          filter:false,
          cellStyle:  { border: "none" },
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openPresentModal.bind(this),
            btnClass: "btn btn-success",
            label: this.translate("btnRenderer.present")
          }
        },
        {
          sortable: false,
          filter:false,
          hide: !this.isAdmin(),
          cellStyle:  { border: "none" },
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openDeleteModal.bind(this),
            btnClass: "btn btn-success",
            label: this.translate("btnRenderer.delete")
          }
        }
      ]
    })
  }

  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }

  private openPresentModal(params :any) {
    const subject: Subject = params.rowData;
    this.openDateTimeDialog(subject);
  }

  private openDeleteModal(params: any) {
    const subject: Subject = params.rowData;
    this.openConfirmDialog(subject, 'delete', 'subject');
  };

  openConfirmDialog(subject: Subject, action: String, object: String) {
    const dialogRef = this.dialog.open(ConfirmationModalComponent, {
      position: {
        top: "50px"
      },
      data:
      {
        dataToProcess: subject.title,
        action: action,
        object: object
      },
    });
    dialogRef.afterClosed().subscribe(action => {
      action == 'confirm' ? this.deleteSubject(subject.id) : dialogRef.close();
    })
  }

  openDateTimeDialog(subject: Subject) {
    const dialogRef = this.dialog.open(DateTimeDialogComponentComponent, {
      position: {
        top: "50px"
      },
      data:
      {
        subject: subject
      },
    });

    dialogRef.afterClosed().subscribe((startDate : Date)=> {
      if(startDate) {
        const sessionSharedForm = this.createSessionForm(startDate, subject);
        const request = this.subjectService.presentSubject(sessionSharedForm);
        this.presentSubjectSubscription = request.subscribe(
          () => {
            this.router.navigate(['/sharing-session'])
          },
          (error : HttpErrorResponse) => {
            if(error.status == 409) {
              this.errorReturned = this.translateService.instant('error.session-scheduled');
          setTimeout(()=> {
            this.errorReturned = null
          }, 2000)
            }
            const message = ErrorHandler.catch(error);
            console.log("error : " + message);
          }
        );
      }
    })
  }

  private createSessionForm(startDate: Date, subject: Subject) {
    const endTime = new Date(startDate);
    endTime.setHours(startDate.getHours() + 1);
    const sessionSharedForm = this.formBuilder.group({
      subjectId: [subject.id, [Validators.required]],
      startTime: [this.convertDate(startDate), [Validators.required]],
      endTime: [this.convertDate(endTime), [Validators.required]]
    });
    return sessionSharedForm;
  }

  private convertDate(date : Date)  {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm')
  }

  isAdmin(): boolean {
    return this.authenthicationService.currentUserValue.isAdmin();
  }
}