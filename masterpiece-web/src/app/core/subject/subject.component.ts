import { Component, OnInit, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { SubjectService } from './subject.service';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService]
})
export class SubjectComponent implements OnInit {

  @Input('id')
  id : number

  private subjectForm: FormGroup;
  private categories = [
    {name : "Front-End", value : "FRONTEND"},
    {name :"Back-End", value : "BACKEND"},
    {name :"Database", value : "DATABASE"},
    {name :"Rift", value : "RIFT"},
    {name :"Other", value : "OTHER"}
  ];

  constructor(private translate: TranslateService, private formBuilder: FormBuilder, private subjectService: SubjectService) {
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

  ngOnInit() {
    this.categories = this.categories;
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
    this.subjectService.postSubject(this.subjectForm).subscribe(
      () => console.log("success"),
      (error) => console.log(error)
    );
  }

  public deleteSubject() {
    this.subjectService.deleteSubject(this.id).subscribe(
      ()=> console.log("Deleted with succes : " + this.id),
      (error) => console.log(error)
    )
  }

  isAdmin() : boolean {
    return false;
  }
}
