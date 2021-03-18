import { Category } from './category.model';
import { EntityUser } from './user-credentials.model';

export class Subject{
    id : number;
    title : string;
    description : string;
    category : Category;
    requester : EntityUser;
}