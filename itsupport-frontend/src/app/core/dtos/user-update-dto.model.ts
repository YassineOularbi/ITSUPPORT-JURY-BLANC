export class UserUpdateDto {
    private _fullName: string;
    private _mail: string;
    private _username: string;
    private _phone?: string;
    private _address?: string;
    private _picture?: File;

    constructor(
        fullName: string,
        mail: string,
        username: string,
        phone?: string,
        address?: string,
        picture?: File
    ) {
        this._fullName = fullName;
        this._mail = mail;
        this._username = username;
        this._phone = phone;
        this._address = address;
        this._picture = picture;
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

    get picture(): File | undefined {
        return this._picture;
    }

    set picture(picture: File | undefined) {
        this._picture = picture;
    }
}
