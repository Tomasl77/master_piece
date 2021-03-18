import { Component, OnDestroy, OnInit, Output, TemplateRef, ViewChild } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { ColDef, GridOptions } from 'ag-grid-community';
import { Subscription } from 'rxjs';
import { UserRegistrationService } from '../user-registration.service';
import { BtnCellRenderer } from '../../shared/btn-cell-renderer.component'
import { ConfirmationModalComponent } from 'src/app/shared/modals/confirmation-modal/confirmation-modal.component';
import { MatDialog } from '@angular/material';
import { AuthenticationService } from 'src/app/shared/services/authentication/authentication.service';
import { EntityUser } from 'src/app/shared/models/user-credentials.model';
import { CacheService } from 'src/app/shared/services/cache.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler } from 'src/app/shared/services/error-handler';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
  providers: [UserRegistrationService, ConfirmationModalComponent]
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  private allUsersSubscription: Subscription;
  private deleteUserSubscription: Subscription;
  rowData: EntityUser[];
  gridOptions: GridOptions;
  columnDefs: ColDef[];
  frameworkComponents = {};
  infoToDisplay: string;

  constructor(private userRegistrationService: UserRegistrationService,
    private translateService: TranslateService,
    public dialog: MatDialog,
    private authenthicationService: AuthenticationService,
    private cacheService: CacheService
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
      ((users: EntityUser[]) => {
        this.rowData = users;
      }),
      err => console.log(err)
    );
  }

  openDialog(user: EntityUser) {
    console.log(user);
    const dialogRef = this.dialog.open(ConfirmationModalComponent, {
      position: {
        top: "50px"
      },
      data:
      {
        dataToProcess: user.username,
        action: this.translate('dialog.delete'),
        object: this.translate('dialog.user')
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      result == 'confirm' ? this.deleteUser(user.id) : dialogRef.close();
    })
  }

  deleteUser(id: number) {
    this.deleteUserSubscription = this.userRegistrationService.deleteUser(id).subscribe(() => {
      this.getAllUsers();
    });
  }

  private getTableHeaderWithLang(): void {
    this.translateService.get('language').subscribe((translate: string) => {
      this.columnDefs = [
        { headerName: 'id', field: 'id', hide: true },
        { headerName: this.translate('ag-grid.admin-panel.username'), field: 'username' },
        { headerName: this.translate('ag-grid.admin-panel.email'), field: 'email' },
        {
          headerName: this.translate('ag-grid.delete'),
          cellStyle: { border: "none" },
          hide: !this.isAdmin(),
          cellRenderer: 'btnCellRenderer',
          cellRendererParams: {
            onClick: this.openDeleteModal.bind(this),
            btnClass: "btn btn-success",
            label: this.translate('admin-panel.label-delete'),
            isPanelAdmin: true,
          }
        }
      ]
    })
  }

  public sizeColumnsToFit(gridOptions: GridOptions) {
    gridOptions.api.sizeColumnsToFit();
  }

  public openDeleteModal(params: any) {
    const user: EntityUser = params.rowData;
    this.openDialog(user);
  };

  private translate(stringToTranslate: string): string {
    return this.translateService.instant(stringToTranslate);
  }

  isAdmin(): boolean {
    return this.authenthicationService.currentUserValue.isAdmin();
  }

  refreshCacheCategories(): void {
    this.cacheService.cleanCache("categories").subscribe(() => {
      this.infoDisplayedWithTime('cache', 1500);
    }, (error: HttpErrorResponse) => {
      const message = ErrorHandler.catch(error);
      console.log(message);
    })
  }

  infoDisplayedWithTime(info: string, time: number, optionalValue?: any): void {
    this.infoToDisplay = this.translateService.instant(info);
    if (optionalValue) {
      this.infoToDisplay += optionalValue
    }
    setTimeout(() => {
      this.infoToDisplay = null
    }, time)
  }
}
