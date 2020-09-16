import { User } from 'src/app/shared/models/user.model';

export interface AccountDto {
    email : string;
    user: User;
}