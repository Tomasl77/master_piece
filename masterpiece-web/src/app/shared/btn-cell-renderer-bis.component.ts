import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams } from 'ag-grid-community';
import { AuthenticationService } from './services/authentication/authentication.service';

@Component({
  selector: 'btnCellRendererBis',
  template: `
      <button class="{{btnClass}}" [disabled]="checkVoted()" type="button" (click)="onClick($event)">{{label}}</button>
    `
})
export class BtnCellRendererBis implements ICellRendererAngularComp {

  constructor() { }
  refresh(params: any): boolean {
    return true;
  }
  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    
  }

  params: any;
  btnClass: string;
  label: string;
  hideForVote: boolean;
  
  agInit(params: any): void {
    this.params = params;
    this.btnClass = params.btnClass;
    this.label = params.label;
  }

  onClick($event: Function) {
    const params = {
      event: $event,
      rowData: this.params.node.data,
    }
    this.params.onClick(params);
  }

  checkVoted(): boolean {
    return this.params.data.hasVoted == true
  }
}