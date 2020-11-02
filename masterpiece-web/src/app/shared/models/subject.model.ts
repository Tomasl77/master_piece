import { UserProfile } from 'src/app/shared/models/user-profile.model';

export class Subject{
    id : number;
    title : string;
    description : string;
    category : string;
    vote: number;
    user : UserProfile;
}