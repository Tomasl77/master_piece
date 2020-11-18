import { Subject } from './subject.model';
import { UserProfile } from './user-profile.model';

export class SharingSession {
    id: number;
    day: Date;
    startTime: Date;
    endTime: Date;
    subject: Subject;
    lecturer: UserProfile;
}