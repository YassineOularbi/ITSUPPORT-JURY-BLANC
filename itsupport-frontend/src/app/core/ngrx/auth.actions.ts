import { createAction, props } from '@ngrx/store';
import { UserDto } from '../dtos/user-dto.model';
import { Role } from '../enums/role.enum';

export const logout = createAction('[Auth] Logout');
export const setRole = createAction('[Auth] Set Role', props<{ role: Role }>());
export const setUser = createAction('[Auth] Set User', props<{ user: UserDto }>());