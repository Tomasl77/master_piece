import { TestBed } from '@angular/core/testing';

import { CustomUserRegistrationService } from './custom-user-registration.service';

describe('AccountRegistrationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomUserRegistrationService = TestBed.get(CustomUserRegistrationService);
    expect(service).toBeTruthy();
  });
});
