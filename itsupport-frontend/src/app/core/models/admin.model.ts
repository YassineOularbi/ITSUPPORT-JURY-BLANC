import { Role } from "../enums/role.enum";
import { User } from "./user.model";

export class Admin extends User{

    constructor(id: number, fullName: string, mail: string, username: string, password: string) {
        super(id, fullName, mail, username, password, Role.ADMIN);
        this.role = Role.ADMIN;
    }

}
