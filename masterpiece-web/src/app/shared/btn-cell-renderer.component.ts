import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams } from 'ag-grid-community';
import { AuthenticationService } from './services/authentication/authentication.service';

@Component({
  selector: 'btnCellRenderer',
  template: `
      <button class="{{btnClass}}" type="button" [hidden]="(isCurrentlyLog() && isPanelAdmin())" (click)="onClick($event)">{{label}}</button>
    `
})
export class BtnCellRenderer implements ICellRendererAngularComp {

  constructor(private authenticationService: AuthenticationService) { }
  refresh(params: any): boolean {
    throw new Error('Method not implemented.');
  }
  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    throw new Error('Method not implemented.');
  }

  private params: any;
  btnClass: string;
  hasVoted : boolean;
  label: string;
  private panelAdmin: boolean
  private userId: number;

  agInit(params: any): void {
    this.params = params;
    this.btnClass = params.btnClass;
    this.hasVoted = params.hasVoted;
    this.label = params.label;
    this.panelAdmin = params.panelAdmin || false;
  }

  onClick($event: Function) {
    const params = {
      event: $event,
      rowData: this.params.node.data,
    }
    console.log(params)
    this.params.onClick(params);
  }

  isPanelAdmin(): boolean {
    return this.params.isPanelAdmin
  }

  hasVote(){
    return true == this.params.node.data.hasVoted
  }

  isCurrentlyLog(): boolean {
    return this.authenticationService.currentUserValue.userId === this.params.node.data.id;
  }
}