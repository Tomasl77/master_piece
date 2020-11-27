import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { AuthenticationService } from './services/authentication/authentication.service';

@Component({
  selector: 'btnCellRenderer',
  template: `
      <button class="{{btnClass}}" type="button" [hidden]="isCurrentlyLog() && isPanelAdmin()" (click)="onClick($event)">{{label}}</button>
    `
})
export class BtnCellRenderer implements ICellRendererAngularComp {

  constructor(private authenticationService: AuthenticationService) { }

  private params: any;
  private btnClass: string;
  private label: string;
  private panelAdmin: boolean
  private userId: number;

  agInit(params: any): void {
    this.params = params;
    this.btnClass = params.btnClass;
    this.label = params.label;
    this.panelAdmin = params.panelAdmin || false;
  }

  onClick($event: Function) {
    const params = {
      event: $event,
      rowData: this.params.node.data
    }
    this.params.onClick(params);
  }

  refresh(params: any): boolean {
    return true;
  }

  isPanelAdmin(): boolean {
    return this.params.isPanelAdmin
  }

  isCurrentlyLog(): boolean {
    return this.authenticationService.currentUserValue.userId === this.params.node.data.id;
  }
}