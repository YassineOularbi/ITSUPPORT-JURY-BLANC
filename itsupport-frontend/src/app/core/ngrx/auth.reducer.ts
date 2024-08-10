// src/app/ngrx/auth.reducer.ts
import { createReducer, on } from '@ngrx/store';
import { logout, setRole, setUser } from './auth.actions';
import { UserDto } from '../dtos/user-dto.model';
import { Role } from '../enums/role.enum';

export interface AuthState {
  role: Role | null;
  user: UserDto | null;
}

export const initialState: AuthState = {
  role: null,
  user: null
};

export const authReducer = createReducer(
  initialState,
  on(setRole, (state, { role }) => ({
    ...state,
    role
  })),
  on(setUser, (state, { user }) => ({
    ...state,
    user
  })),
  on(logout, () => initialState),
);
