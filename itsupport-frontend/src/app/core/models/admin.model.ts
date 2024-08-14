import { Role } from "../enums/role.enum";
import { User } from "./user.model";

export class Admin extends User {

    constructor(
        id: number,
        fullName: string,
        mail: string,
        username: string,
        password: string,
        phone?: string,
        address?: string,
        joinedDate?: Date,
        avatarUrl?: string
    ) {
        super(id, fullName, mail, username, password, Role.ADMIN, phone, address, joinedDate, avatarUrl);
        this.role = Role.ADMIN
    }
}
