import { Component, OnInit, OnDestroy } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { SubjectService } from './subject.service';
import { ActivatedRoute, Params } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { Subject } from '../../shared/models/subject.model';
import { Subscription } from 'rxjs';
import { ColDef, GridOptions } from 'ag-grid-community';
import { MatDialog } from '@angular/material';
import { ConfirmationModalComponent } from 'src/app/shared/modals/confirmation-modal/confirmation-modal.component';
import { BtnCellRenderer } from 'src/app/shared/btn-cell-renderer.component';
import { DateTimeDialogComponentComponent } from 'src/app/shared/modals/date-time-dialog-component/date-time-dialog-component.component';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService, ConfirmationModalComponent]
})
export class SubjectComponent implements OnInit, OnDestroy {

  private action: string;

  private formErrors = {
    'title': '',
    'description': ''
  }
  private subjects: Subject[];
  private rowData: Subject[];
  private gridOptions: GridOptions;
  private columnDefs: ColDef[];
  private frameworkComponents = {};

  private getAllSubjectsSubscription: Subscription;
  private deleteSubjectSubscription: Subscription;
  private postSubjectSubscription: Subscription;

  private subjectForm: FormGroup;
  private categories = [
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
    private authenthicationService: AuthenticationService,
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

  }

  ngOnDestroy() {
    this.subjectService.unsubscribe(this.deleteSubjectSubscription);
    this.subjectService.unsubscribe(this.getAllSubjectsSubscription);
    this.subjectService.unsubscribe(this.postSubjectSubscription);
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

  private logValidationErrors(group: FormGroup = this.subjectForm): void {
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
      (error) => console.log(error)
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
        console.log("error")
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
          headerName: this.translate('ag-grid.present'),
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
          headerName: this.translate('ag-grid.delete'),
          sortable: false,
          filter:false,
          hide: !this.isAdmin(),
          cellStyle:  { border: "none" },
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openDeleteModal.bind(this),
            btnClass: "btn btn-success",
            label: "Delete"
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
    dialogRef.afterClosed().subscribe(result => {
      result == 'confirm' ? this.deleteSubject(subject.id) : dialogRef.close();
    })
  }

  openDateTimeDialog(subject: Subject) {
    const dialogRef = this.dialog.open(DateTimeDialogComponentComponent, {
      position: {
        top: "50px"
      },
      data:
      {
        dataToProcess: subject.title
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      result == 'confirm' ? console.log("confirmation" + subject.title) : dialogRef.close();
    })
  }

  isAdmin(): boolean {
    return this.authenthicationService.currentUserValue.isAdmin();
  }
}