import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SubjectService } from './subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css'],
  providers: [SubjectService]
})
export class SubjectComponent implements OnInit {

  public subjectForm: FormGroup;

  constructor(private translate: TranslateService, private formBuilder: FormBuilder, private subjectService: SubjectService) {
    this.subjectForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(20)]],
      description: ['', [Validators.required]],
      category: ''
    })
  }

  formErrors = {
    'title': '',
    'description': ''
  }

  ngOnInit() {
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
      (error) => console.log(error));
  }
}