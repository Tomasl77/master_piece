import { User } from './user.model';

export interface Member {
    email : string;
    id : number;
    user : User;
}