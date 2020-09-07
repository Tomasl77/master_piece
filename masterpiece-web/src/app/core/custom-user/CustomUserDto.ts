import { User } from 'src/app/shared/models/user.model';

export interface CustomUserDto {
    email : string;
    user: User;
}