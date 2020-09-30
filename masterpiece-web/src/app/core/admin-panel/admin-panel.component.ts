import { Component, OnDestroy, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ColDef, GridOptions } from 'ag-grid-community';
import { Subscription } from 'rxjs';
import { CustomUser } from 'src/app/shared/models/custom-user.model';
import { UserRegistrationService } from '../user-registration.service';
import { BtnCellRenderer } from '../../shared/btn-cell-renderer.component'
import { User } from 'src/app/shared/models/user.model';
import { ConfirmationModalComponent } from 'src/app/shared/modals/confirmation-modal/confirmation-modal.component';
import { MatDialog, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
  providers: [UserRegistrationService, ConfirmationModalComponent]
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  private allUsersSubscription: Subscription;
  private deleteUserSubscription: Subscription;
  private rowData: CustomUser[];
  private gridOptions: GridOptions;
  private columnDefs: ColDef[];
  private frameworkComponents = {};

  constructor(private userRegistrationService: UserRegistrationService,
    private translateService: TranslateService,
    public dialog: MatDialog
  ) {
    this.gridOptions = {
      defaultColDef: { sortable: true, filter: true },
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
      ((users: CustomUser[]) => {
        this.rowData = users;
        console.log(this.rowData)
      }),
      err => console.log(err)
    );
  }

  openDialog(user: CustomUser){
    console.log(user);
    const dialogRef = this.dialog.open(ConfirmationModalComponent, {
      width:'50%',
      minWidth: '300px',
      height:'60%',
      data: 
        {user : user,
        action: 'delete'},
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(user.id);
      result == 'confirm' ? this.deleteUser(user.id): dialogRef.close();
    })
  }
  
  deleteUser(id: number) {
    this.deleteUserSubscription = this.userRegistrationService.deleteUser(id).subscribe(response=> {
      console.log("response : "+ response);
      this.getAllUsers();
    });
  }

  private getTableHeaderWithLang(): void {
    this.translateService.get('language').subscribe((translate: string) => {
      this.columnDefs = [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: this.translate('ag-grid.admin-panel.username'), field: 'username' },
        { headerName: this.translate('ag-grid.admin-panel.email'), field: 'info.email' },
        {
          headerName: this.translate('ag-grid.delete'),
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openDeleteModal.bind(this),
            btnClass : "btn btn-success"
          }
        }
      ]
    })
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public openDeleteModal(params: any) {
    const user: CustomUser = params.rowData;
    this.openDialog(user);
  };

  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }
}
