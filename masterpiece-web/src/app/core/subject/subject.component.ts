import { Component, OnInit, Input, OnChanges, SimpleChanges, OnDestroy } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { SubjectService } from './subject.service';
import { ActivatedRoute, Params } from '@angular/router';
import { AuthenticationService } from 'src/app/shared/authentication/authentication.service';
import { Subject } from './subject.model';
import { Subscription } from 'rxjs';
import { HttpRequestHandler } from 'src/app/shared/http-helper/http-request-handler';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService]
})
export class SubjectComponent implements OnInit, OnDestroy {

  @Input('id')
  id: number;

  action: string;

  subjects: Subject[];

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
    private translate: TranslateService,
    private formBuilder: FormBuilder,
    private subjectService: SubjectService,
    private activatedRoute: ActivatedRoute,
    private authenthicationService: AuthenticationService
  ) {
    this.subjectForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(30)]],
      description: ['', [Validators.required]],
      category: ''
    })
  }

  formErrors = {
    'title': '',
    'description': ''
  }

  /* ag-grid */
  columnDefs = [
    { headerName: 'id', field: 'id', hide: true},
    { headerName: 'Title', field: 'title', sortable: true, filter: true },
    { headerName: 'Description', field: 'description', sortable: true, filter: true },
    { headerName: 'Category', field: 'category', sortable: true, filter: true },
    { headerName: 'Vote', field: 'vote', sortable: true, filter: true },
    { headerName: 'Requester', field: 'member.user.username', sortable: true, filter: true },
    { headerName: 'Delete', 
      hide: !this.isAdmin() }
    
  ];

  rowData: Subject[];

  /* end ag-grid */

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.action = params["action"];
      this.customInit();
    });
  }

  ngOnDestroy() {
    this.subjectService.unsubscribe(this.deleteSubjectSubscription);
    this.subjectService.unsubscribe(this.getAllSubjectsSubscription);
    this.subjectService.unsubscribe(this.postSubjectSubscription);
  }

  customInit() {
    if (this.action === "vote") {
      this.subjectService.getAllSubject().subscribe(
        (subjects: Subject[]) => {
          this.subjects = subjects;
          this.rowData = subjects;
          console.log(this.subjects)
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
              const err = this.translate.instant('subject.validationMessages.' + key + '.' + errorKey);
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
        console.log("success"),
          this.subjectForm.reset()
      },
      (error) => console.log(error)
    );
  }

  public deleteSubject() {
    const request = this.subjectService.deleteSubject(this.id);
    this.deleteSubjectSubscription = request.subscribe(
      () => console.log("Deleted with succes : " + this.id),
      (error) => console.log(error)
    )
  }

  getSubjects() {
    const request = this.subjectService.getAllSubject()
    this.getAllSubjectsSubscription = request.subscribe(
      (subjects: Subject[]) => {
        this.rowData = subjects;
        console.log(this.rowData)
      },
      error => console.log(error)
    );
  }

  isAdmin(): boolean {
    return this.authenthicationService.currentUserValue.isAdmin();
  }
}