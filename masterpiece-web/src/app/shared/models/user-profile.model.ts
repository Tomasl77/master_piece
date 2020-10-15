import { UserCredentials } from './user-credentials.model';

export class UserProfile {
    id : number;
    email : string;
    credentials : UserCredentials;
}