import { Role } from "../enums/role.enum";

export class User {
    private _id: number;
    private _fullName: string;
    private _mail: string;
    private _username: string;
    private _password: string;
    private _role: Role;
    private _phone?: string;
    private _address?: string;
    private _joinedDate?: Date;
    private _avatarUrl?: string;

    constructor(id: number, fullName: string, mail: string, username: string, password: string, role: Role,
                phone?: string, address?: string, joinedDate?: Date, avatarUrl?: string) {
        this._id = id;
        this._fullName = fullName;
        this._mail = mail;
        this._username = username;
        this._password = password;
        this._role = role;
        this._phone = phone;
        this._address = address;
        this._joinedDate = joinedDate;
        this._avatarUrl = avatarUrl;
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

    get phone(): string | undefined {
        return this._phone;
    }

    set phone(value: string | undefined) {
        this._phone = value;
    }

    get address(): string | undefined {
        return this._address;
    }

    set address(value: string | undefined) {
        this._address = value;
    }

    get joinedDate(): Date | undefined {
        return this._joinedDate;
    }

    set joinedDate(value: Date | undefined) {
        this._joinedDate = value;
    }

    get avatarUrl(): string | undefined {
        return this._avatarUrl;
    }

    set avatarUrl(value: string | undefined) {
        this._avatarUrl = value;
    }
}
