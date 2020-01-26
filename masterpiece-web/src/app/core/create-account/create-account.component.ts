import { Component, OnInit } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { UsernameValidator } from './username-validator';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  private readonly passwordPatten = "^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$";

  public signForm: FormGroup;
  constructor(private fb: FormBuilder, usernameValidator: UsernameValidator,
    private readonly http: HttpClient) { 
      this.signForm = this.fb.group({
       username: ['', Validators.required, usernameValidator.validate.bind(usernameValidator) ],
       password: ['', [Validators.required, Validators.pattern((this.passwordPatten))]]
    });
  }

  ngOnInit() {
  }
  register() {
    console.log(this.signForm.value);
    this.http.post<object>(`http://localhost:8000/create-account`, this.signForm.value)
      .subscribe(async (data) => {
        for (const fieldName of Object.keys(data)) {
          const serverErrors = data[fieldName];

          const errors = {};
          for (const serverError of serverErrors) {
            errors[serverError] = true;
          }

          const control = this.signForm.get(fieldName);
          control.setErrors(errors);
          control.markAsDirty();
        }

      });
  }
}

