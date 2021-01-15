import { Subject } from './subject.model';
import { UserCredentials } from './user-credentials.model';

export class SharingSession {
    id: number;
    day: Date;
    startTime: Date;
    endTime: Date;
    subjectTitle: String;
    lecturerName: String;
}