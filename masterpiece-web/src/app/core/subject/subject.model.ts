import { Member } from 'src/app/shared/models/member.model';

export interface Subject{
    id : number;
    title : string;
    description : string;
    category : string;
    member : Member;
}