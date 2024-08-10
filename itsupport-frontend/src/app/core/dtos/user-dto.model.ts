import { Role } from "../enums/role.enum";

export class UserDto {
    private _id: number;
    private _fullName: string;
    private _mail: string;
    private _username: string;
    private _role: Role;

    constructor(id: number, fullName: string, mail: string, username: string, role: Role) {
        this._id = id;
        this._fullName = fullName;
        this._mail = mail;
        this._username = username;
        this._role = role;
    }

    get id(): number {
        return this._id;
    }

    set id(id: number) {
        this._id = id;
    }

    get fullName(): string {
        return this._fullName;
    }

    set fullName(fullName: string) {
        this._fullName = fullName;
    }

    get mail(): string {
        return this._mail;
    }

    set mail(mail: string) {
        this._mail = mail;
    }

    get username(): string {
        return this._username;
    }

    set username(username: string) {
        this._username = username;
    }

    get role(): Role {
        return this._role;
    }

    set role(role: Role) {
        this._role = role;
    }
}
