import { TestBed } from '@angular/core/testing';

import { MemberRegistrationService } from './member-registration.service';

describe('AccountRegistrationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MemberRegistrationService = TestBed.get(MemberRegistrationService);
    expect(service).toBeTruthy();
  });
});
