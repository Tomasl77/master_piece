import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams } from 'ag-grid-community';
import { AuthenticationService } from './services/authentication/authentication.service';

@Component({
  selector: 'btnCellRendererBis',
  template: `
      <button class="{{btnClass}}" style=style type="button" (click)="onClick($event)">{{label}}</button>
    `
})
export class BtnCellRendererBis implements ICellRendererAngularComp {

  constructor(private translateService: TranslateService) { }
  refresh(params: any): boolean {
    return true;
  }
  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    
  }

  params: any;
  btnClass: string;
  label: string;
  hasVoted: boolean;
  style: string;
  
  agInit(params: any): void {
    this.params = params;
    this.btnClass = params.btnClass;
    this.label = this.getLabel(params.data.hasVoted);
    this.style = this.getStyle();
    this.hasVoted = (params.data.hasVoted == true)
  }

  getLabel(hasVoted: boolean) {
    if(hasVoted == true) {
      this.btnClass = "btn btn-success red"
      return this.translateService.instant('btnRenderer.unvote');
    } else {
      this.btnClass = "btn btn-success"
      return this.translateService.instant('btnRenderer.vote');
    }
  }

  getStyle(){
    return "color: black;"
  }

  onClick($event: Function) {
    const params = {
      event: $event,
      rowData: this.params.node.data,
    }
    this.params.onClick(params);
  }


}