import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';
import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { AuthenticationService } from './authentication/authentication.service';

@Component({
    selector: 'btnCellRenderer',
    template: `
      <button class="{{btnClass}}" [disabled]="isCurrentlyLog()" (click)="onClick($event)">Delete</button>
    `,
  })
  export class BtnCellRenderer implements ICellRendererAngularComp {
    
  constructor(private authenticationService: AuthenticationService){}

    private params: any;
    btnClass:string ;
  
    agInit(params: any): void {
      this.params = params;
      this.btnClass = this.params.btnClass;
    }
  
    onClick($event: Function) {
      const params = {
        event: $event,
        rowData: this.params.node.data
      }
      this.params.onClick(params);
    }
  
    refresh(params: any): boolean {
        throw new Error('Method not implemented.');
    }

    isCurrentlyLog() : boolean {
      return this.authenticationService.currentUserValue.userId === this.params.node.data.id;
    }
  }