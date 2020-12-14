import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { DatePipe, Location } from '@angular/common';
import { MatDialog } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { ColDef, GridOptions } from 'ag-grid-community';
import { Subscription } from 'rxjs';

import { SubjectService } from './subject.service';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Subject } from '../../shared/models/subject.model';
import { ConfirmationModalComponent } from 'src/app/shared/modals/confirmation-modal/confirmation-modal.component';
import { BtnCellRenderer } from 'src/app/shared/btn-cell-renderer.component';
import { DateTimeDialogComponentComponent } from 'src/app/shared/modals/date-time-dialog-component/date-time-dialog-component.component';
import { ErrorHandler } from 'src/app/shared/services/error-handler';
import { HttpErrorResponse } from '@angular/common/http';
import { Category } from 'src/app/shared/models/category.model';
import { SubjectWithVote } from 'src/app/shared/models/subject-with-vote.model';
import { BtnCellRendererBis } from 'src/app/shared/btn-cell-renderer-bis.component';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService, CategoryService, ConfirmationModalComponent]
})
export class SubjectComponent implements OnInit, OnDestroy {

  action: string;
  infoToDisplay: string;
  tomorrow: Date;

  formErrors = {
    'title': '',
    'description': ''
  }
  subjects: SubjectWithVote[];
  rowData: SubjectWithVote[];
  gridOptions: GridOptions;
  columnDefs: ColDef[];
  categories: Category[];
  frameworkComponents = {};

  private getAllSubjectsSubscription: Subscription;
  private deleteSubjectSubscription: Subscription;
  private postSubjectSubscription: Subscription;
  private presentSubjectSubscription: Subscription;
  private getAllCategoriesSubscription: Subscription;
  private voteForSubjectSubscription: Subscription;

  subjectForm: FormGroup;

  constructor(
    private translateService: TranslateService,
    private formBuilder: FormBuilder,
    private subjectService: SubjectService,
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private location: Location,
    private authenthicationService: AuthenticationService,
    private datePipe: DatePipe,
    private dialog: MatDialog) {
    this.subjectForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(30)]],
      description: ['', [Validators.required]],
      category: ['', Validators.required]
    }),

      /* ag-grid */
      this.gridOptions = {
        defaultColDef: { sortable: true, filter: true },
        pagination: true,
        paginationPageSize: 10,
        onFirstDataRendered: this.sizeColumnsToFit
      },
      this.frameworkComponents = {
        btnCellRenderer: BtnCellRenderer,
        btnCellRendererBis: BtnCellRendererBis
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
    this.subjectService.unsubscribe(this.getAllCategoriesSubscription)
  }

  customInit() {
    this.categoryService.getAllCategories().subscribe((categories) => {
      this.categories = categories,
        console.log(this.categories)
    });
    this.getSubjectsIfVotePanel();
  }

  public getSubjectsIfVotePanel() {
    if (this.action === "vote") {
      this.subjectService.getAllSubject().subscribe(
        (subjects: SubjectWithVote[]) => {
          this.subjects = subjects;
          this.rowData = subjects;
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
    const formBuild: FormGroup = this.constructForm(this.subjectForm)
    const request = this.subjectService.postSubject(formBuild);
    this.postSubjectSubscription = request.subscribe(
      (subject: Subject) => {
        this.subjectForm.reset();
        this.action = "vote";
        this.getSubjectsIfVotePanel();
        this.infoDisplayedWithTime('subject.newTopic', 3000, subject.title);
      },
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log("message : " + message);
      }
    );
  }

  constructForm(subjectForm: FormGroup): FormGroup {
    return this.formBuilder.group({
      title: [subjectForm.value.title],
      description: [subjectForm.value.description],
      categoryId: [subjectForm.value.category]
    })
  }
  public deleteSubject(id: number) {
    const request = this.subjectService.deleteSubject(id);
    this.deleteSubjectSubscription = request.subscribe(
      () => this.getSubjects(),
      (error) => console.log(error)
    )
  }
  /*
  public deleteSubject(subject: SubjectWithVote) {
    const request = this.subjectService.deleteSubject(subject.id);
    this.deleteSubjectSubscription = request.subscribe(
      () => this.getSubjects(),
      (error) => console.log(error)
    )
  }
*/
  getSubjects() {
    const request = this.subjectService.getAllSubject()
    this.getAllSubjectsSubscription = request.subscribe(
      (subjects: SubjectWithVote[]) => {
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
        { headerName: this.translate('ag-grid.subject.category'), field: 'categoryName', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.requester'), field: 'requesterUsername', sortable: true, filter: true },
        {
          headerName: this.translate('ag-grid.subject.vote'),
          sortable: false,
          filter: false,
          cellStyle: { border: "none" },
          cellRenderer: 'btnCellRendererBis',
          cellRendererParams: {
            onClick: this.openVoteDialog.bind(this),
            btnClass: "btn btn-success",
            label: this.translate("btnRenderer.vote")
          }
        },
        { headerName: this.translate('ag-grid.subject.numberOfVote'), field: 'numberOfVote', sortable: true, filter: true },
        {
          sortable: false,
          filter: false,
          cellStyle: { border: "none" },
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openPresentModal.bind(this),
            btnClass: "btn btn-success",
            label: this.translate("btnRenderer.present")
          }
        },
        {
          sortable: false,
          filter: false,
          hide: !this.isAdmin(),
          cellStyle: { border: "none" },
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

  private openVoteDialog(params: any) {
    const subject: SubjectWithVote = params.rowData;
    console.log(subject);
  }


  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }

  private openPresentModal(params: any) {
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
  /*
  openConfirmDialog(subject: any, action: String, object: String, callBack: Function) {
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
      action == 'confirm' ? callBack(subject) : dialogRef.close();
    })
  }
*/
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

    dialogRef.afterClosed().subscribe((startDate: Date) => {
      if (startDate) {
        const sessionSharedForm = this.createSessionForm(startDate, subject);
        const request = this.subjectService.presentSubject(sessionSharedForm);
        this.presentSubjectSubscription = request.subscribe(
          () => {
            this.router.navigate(['/sharing-session'])
          },
          (error: HttpErrorResponse) => {
            if (error.status == 400) {
              this.infoDisplayedWithTime('error.session-scheduled', 3000);
            }
            const message = ErrorHandler.catch(error);
            console.log("error : " + message);
          }
        );
      }
    })
  }

  voteSubject(subject: SubjectWithVote) {
    const formBuild: FormGroup =this.formBuilder.group({
      hasVoted: [(subject.hasVoted)]
    });
    const request = this.subjectService.voteForSubject(subject.id, formBuild);
    this.voteForSubjectSubscription = request.subscribe(
      () => this.getSubjects(),
      (error) => console.log(error)
    )
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

  private convertDate(date: Date) {
    return this.datePipe.transform(date, 'yyyy-MM-dd HH:mm')
  }

  isAdmin(): boolean {
    return this.authenthicationService.currentUserValue.isAdmin();
  }

  infoDisplayedWithTime(info: string, time: number, optionalValue?: any): void {
    this.infoToDisplay = this.translateService.instant(info);
    if (optionalValue) {
      this.infoToDisplay += optionalValue
    }
    setTimeout(() => {
      this.infoToDisplay = null
    }, time)
  }
}