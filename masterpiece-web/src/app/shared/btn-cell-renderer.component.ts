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
  
    onClick($event : Function) {
      this.params.clicked(this.params.value);
    }
  
    refresh(params: any): boolean {
        throw new Error('Method not implemented.');
    }
  }