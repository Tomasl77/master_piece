import { Component, OnInit, OnDestroy, Input } from '@angular/core';
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
import { SubjectViewDtoWithVote } from 'src/app/shared/models/subject-with-vote.model';
import { BtnCellRendererVote } from 'src/app/shared/btn-cell-renderer-vote.component';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService, CategoryService, ConfirmationModalComponent]
})
export class SubjectComponent implements OnInit, OnDestroy {

  @Input()
  id: number;

  pathParam: string;
  infoToDisplay: string;
  tomorrow: Date;

  formErrors = {
    'title': '',
    'description': ''
  }
  rowData: SubjectViewDtoWithVote[];
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
        btnCellRendererVote: BtnCellRendererVote
      }
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.pathParam = params["action"];
      if((this.pathParam != "post") && (this.pathParam != "vote")) {
        this.router.navigateByUrl("/subject;action=vote")
      } 
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
    this.subjectService.unsubscribe(this.voteForSubjectSubscription);
    this.subjectService.unsubscribe(this.getAllCategoriesSubscription)
  }

  customInit() {
    this.categoryService.getAllCategories().subscribe((categories) => {
      this.categories = categories
    },
    (error) => {
      const errorToDisplay = ErrorHandler.catch(error);
      console.log(errorToDisplay);
    });
    this.getSubjectsIfVotePanel();
  }

  public getSubjectsIfVotePanel() {
    if (this.pathParam === "vote") {
      this.subjectService.getAllSubjects().subscribe(
        (subjects: SubjectViewDtoWithVote[]) => {
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
        this.router.navigateByUrl("/subject;action=vote")
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
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log("message : " + message);
      }
    )
  }
  
  getSubjects() {
    const request = this.subjectService.getAllSubjects()
    this.getAllSubjectsSubscription = request.subscribe(
      (subjects: SubjectViewDtoWithVote[]) => {
        this.rowData = subjects;
      },
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log(message);
      }
    );
  }

  private getTableHeaderWithLang(): void {
    this.translateService.get('language').subscribe(() => {
      this.columnDefs = [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: this.translate('ag-grid.subject.title'), field: 'title', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.description'), field: 'description', sortable: true, filter: true, tooltipField: 'description' },
        { headerName: this.translate('ag-grid.subject.category'), field: 'categoryName', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.requester'), field: 'requesterUsername', sortable: true, filter: true },
        { headerName: this.translate('ag-grid.subject.numberOfVote'), field: 'numberOfVote', sortable: true, filter: true },
        {
          headerName: this.translate('ag-grid.subject.vote'),
          sortable: false,
          filter: false,
          cellStyle: { border: "none" },
          cellRenderer: 'btnCellRendererVote',
          cellRendererParams: {
            onClick: this.voteSubject.bind(this),
            label: this.translate("btnRenderer.vote")
          }
        },
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

  private voteSubject(params: any) {
    const subject : SubjectViewDtoWithVote = params.rowData;
    const formBuild: FormGroup =this.formBuilder.group({
      hasVoted: [(subject.hasVoted)]
    });
    const request = this.subjectService.voteForSubject(subject.id, formBuild);
    this.voteForSubjectSubscription = request.subscribe(
      () => this.getSubjects(),
      (error) => {
        const message = ErrorHandler.catch(error);
        console.log("error : " + message)
      }
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