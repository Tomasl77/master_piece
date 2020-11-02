import { Subject } from './subject.model';
import { UserProfile } from './user-profile.model';

export class SharingSession {
    id: number;
    startTime: Date;
    endTime: Date;
    subject: Subject;
    userProfile: UserProfile;
}