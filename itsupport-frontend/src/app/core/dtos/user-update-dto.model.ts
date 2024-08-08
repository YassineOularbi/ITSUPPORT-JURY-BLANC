export class UserUpdateDto {
    private _fullName: string;
    private _mail: string;
    private _username: string;

    constructor(fullName: string, mail: string, username: string) {
        this._fullName = fullName;
        this._mail = mail;
        this._username = username;
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
}
