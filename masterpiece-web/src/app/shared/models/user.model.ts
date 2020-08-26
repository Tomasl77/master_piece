import { Role } from './role.model';

export class User {
    username : string;
    userId : number;
    roles : Role[];

    constructor(username : string, userId : number, roles : Role[]) {
        this.username = username;
        this.userId = userId;
        this.roles = roles;
    }
    
}