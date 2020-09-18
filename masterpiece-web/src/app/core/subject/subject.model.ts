import { CustomUser } from 'src/app/shared/models/custom-user.model';

export interface Subject{
    id : number;
    title : string;
    description : string;
    category : string;
    vote: number;
    user : CustomUser;
}