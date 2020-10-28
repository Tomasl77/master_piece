import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-date-time-dialog-component',
  templateUrl: './date-time-dialog-component.component.html',
  styleUrls: ['./date-time-dialog-component.component.css']
})
export class DateTimeDialogComponentComponent implements OnInit {

  private dateTimeForm: FormGroup;

  private startTime: FormControl;

  constructor(
    public dialogRef: MatDialogRef<DateTimeDialogComponentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder: FormBuilder
  ) {
    
  }
  ngOnInit(): void {
    this.onChanges();
  }

  private onChanges() : void{

  }
}
