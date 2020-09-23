import { Component, OnDestroy, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ColDef, GridOptions } from 'ag-grid-community';
import { Subscription } from 'rxjs';
import { CustomUser } from 'src/app/shared/models/custom-user.model';
import { UserRegistrationService } from '../user-registration.service';
import { BtnCellRenderer } from '../../shared/btn-cell-renderer.component'

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
  providers: [UserRegistrationService]
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  private allUsersSubscription : Subscription;
  private deleteUserSubscription: Subscription;
  private rowData : CustomUser[];
  private gridOptions : GridOptions;
  private columnDefs : ColDef[];
  private frameworkComponents = {};


  constructor(private userRegistrationService : UserRegistrationService, private translateService : TranslateService) { 
    this.gridOptions = {
      defaultColDef: { sortable: true, filter: true},
      pagination: true,
      paginationPageSize: 10,
      onFirstDataRendered: this.sizeColumnsToFit
    },
    this.frameworkComponents = {
      btnCellRenderer: BtnCellRenderer
    }
  }

  ngOnInit() {
    this.getAllUsers();
    this.getTableHeaderWithLang();
    this.translateService.onLangChange.subscribe(() => {
      this.getTableHeaderWithLang();
  });
  }

  ngOnDestroy(): void {
    this.userRegistrationService.unsubscribe(this.allUsersSubscription);
    this.userRegistrationService.unsubscribe(this.deleteUserSubscription);
  }

  private getAllUsers() {
    this.allUsersSubscription = this.userRegistrationService.getAllUsers().subscribe(
      ((users : CustomUser[]) => {
        this.rowData = users;
        console.log(this.rowData)
      }),
      err => console.log(err)
    );
  }

  private getTableHeaderWithLang() : void {
    this.translateService.get('language').subscribe((translate: string)=> {
      const username = this.translateService.instant('ag-grid.admin-panel.username');
      const email = this.translateService.instant('ag-grid.admin-panel.email');
      const deleteBut = this.translateService.instant('ag-grid.delete');
      this.columnDefs = [
        { headerName: 'id', field:'id', hide: true },
        { headerName: this.translate('ag-grid.admin-panel.username'), field: 'username' },
        { headerName: this.translate('ag-grid.admin-panel.email'), field : 'info.email' },
        { headerName: this.translate('ag-grid.delete'), 
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.deleteUser.bind(this)
          }
        }
      ]
    })
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public deleteUser(params: any) {
    this.deleteUserSubscription = this.userRegistrationService.deleteUser(3).subscribe(

    )
  };

  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }
}
