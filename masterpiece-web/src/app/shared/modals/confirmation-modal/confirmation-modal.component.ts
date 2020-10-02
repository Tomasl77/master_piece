import { Component, Inject, OnInit } from '@angular/core';
import { DialogPosition, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog'; 

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.css'],
})
export class ConfirmationModalComponent {

  constructor(
    public dialogRef: MatDialogRef<ConfirmationModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  
    public getPosition() {
      this.dialogRef.updatePosition({
        top: "20px"
      })
    }
}
