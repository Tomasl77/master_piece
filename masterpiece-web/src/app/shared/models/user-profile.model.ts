import { UserCredentials } from './user-credentials.model';

export interface UserProfile {
    id : number;
    email : string;
    credentials : UserCredentials;
}