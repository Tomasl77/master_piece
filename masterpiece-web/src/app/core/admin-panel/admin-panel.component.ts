import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { ColDef, GridOptions } from 'ag-grid-community';
import { Subscription } from 'rxjs';
import { CustomUser } from 'src/app/shared/models/custom-user.model';
import { UserRegistrationService } from '../user-registration.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
  providers: [UserRegistrationService]
})
export class AdminPanelComponent implements OnInit, OnDestroy {

  private allUsersSubscription : Subscription;
  private rowData : CustomUser[];
  private gridOptions : GridOptions;
  private columnDefs : ColDef[];
  

  constructor(private userRegistrationService : UserRegistrationService, private translateService : TranslateService) { 
    this.gridOptions = {
      defaultColDef: { sortable: true, filter: true},
      pagination: true,
      paginationPageSize: 10
    }
  }

  ngOnInit() {
    this.getAllUsers();
    this.getUsersWithLang();
    this.translateService.onLangChange.subscribe(() => {
      this.getUsersWithLang();
  });
  }

  ngOnDestroy(): void {
    this.userRegistrationService.unsubscribe(this.allUsersSubscription)
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

  private getUsersWithLang() : void {
      const username = this.translateService.instant('ag-grid.username');
      const email = this.translateService.instant('ag-grid.email');
      const deleteBut = this.translateService.instant('ag-grid.delete');
      this.columnDefs = [
        { headerName: 'id', field:'id', hide: true },
        { headerName: username, field: 'username' },
        { headerName: email, field : 'info.email' },
        { headerName: deleteBut}
      ]
  }
}
