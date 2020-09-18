import { userInfo } from './user-info.model';

export interface CustomUser {
    id : number;
    username : string;
    info : userInfo;
}