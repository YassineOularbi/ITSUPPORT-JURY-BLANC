import { Role } from "../enums/role.enum";

export class User {
    private _id: number;
    private _fullName: string;
    private _mail: string;
    private _username: string;
    private _password: string;
    private _role: Role;
  
    constructor(id: number, fullName: string, mail: string, username: string, password: string, role: Role) {
      this._id = id;
      this._fullName = fullName;
      this._mail = mail;
      this._username = username;
      this._password = password;
      this._role = role;
    }

  
    get id(): number {
      return this._id;
    }
  
    set id(value: number) {
      this._id = value;
    }
  
    get fullName(): string {
      return this._fullName;
    }
  
    set fullName(value: string) {
      this._fullName = value;
    }
  
    get mail(): string {
      return this._mail;
    }
  
    set mail(value: string) {
      this._mail = value;
    }
  
    get username(): string {
      return this._username;
    }
  
    set username(value: string) {
      this._username = value;
    }
  
    get password(): string {
      return this._password;
    }
  
    set password(value: string) {
      this._password = value;
    }
  
    get role(): Role {
      return this._role;
    }
  
    set role(value: Role) {
      this._role = value;
    }
  }
  