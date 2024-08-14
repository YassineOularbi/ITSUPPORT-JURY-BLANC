import { Role } from "../enums/role.enum";

export class UserDto {
    private _id?: number;
    private _fullName?: string;
    private _mail?: string;
    private _username?: string;
    private _phone?: string;
    private _address?: string;
    private _avatarUrl?: string;
    private _role?: Role;

    constructor(
        id?: number,
        fullName?: string,
        mail?: string,
        username?: string,
        phone?: string,
        address?: string,
        avatarUrl?: string,
        role?: Role
    ) {
        this._id = id;
        this._fullName = fullName;
        this._mail = mail;
        this._username = username;
        this._phone = phone;
        this._address = address;
        this._avatarUrl = avatarUrl;
        this._role = role;
    }

    get id(): number | undefined {
        return this._id;
    }

    set id(id: number | undefined) {
        this._id = id;
    }

    get fullName(): string | undefined {
        return this._fullName;
    }

    set fullName(fullName: string | undefined) {
        this._fullName = fullName;
    }

    get mail(): string | undefined {
        return this._mail;
    }

    set mail(mail: string | undefined) {
        this._mail = mail;
    }

    get username(): string | undefined {
        return this._username;
    }

    set username(username: string | undefined) {
        this._username = username;
    }

    get phone(): string | undefined {
        return this._phone;
    }

    set phone(phone: string | undefined) {
        this._phone = phone;
    }

    get address(): string | undefined {
        return this._address;
    }

    set address(address: string | undefined) {
        this._address = address;
    }

    get avatarUrl(): string | undefined {
        return this._avatarUrl;
    }

    set avatarUrl(avatarUrl: string | undefined) {
        this._avatarUrl = avatarUrl;
    }

    get role(): Role | undefined {
        return this._role;
    }

    set role(role: Role | undefined) {
        this._role = role;
    }
}
