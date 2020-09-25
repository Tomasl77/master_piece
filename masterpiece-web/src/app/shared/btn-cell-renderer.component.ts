import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';

@Component({
    selector: 'btnCellRenderer',
    template: `
      <button (click)="onClick($event)">Click me!</button>
    `,
  })
  export class BtnCellRenderer implements ICellRendererAngularComp {
    
    private params: any;
  
    agInit(params: any): void {
      this.params = params;
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
  }