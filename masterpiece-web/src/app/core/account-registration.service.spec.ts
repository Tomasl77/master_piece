import { TestBed } from '@angular/core/testing';

import { AccountRegistrationService } from './account-registration.service';

describe('AccountRegistrationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccountRegistrationService = TestBed.get(AccountRegistrationService);
    expect(service).toBeTruthy();
  });
});
