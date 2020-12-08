import { Category } from './category.model';
import { UserCredentials } from './user-credentials.model';

export class Subject{
    id : number;
    title : string;
    description : string;
    category : Category;
    requester : UserCredentials;
}