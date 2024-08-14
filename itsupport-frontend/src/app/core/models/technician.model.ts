import { User } from './user.model';
import { Ticket } from './ticket.model';
import { Role } from '../enums/role.enum';

export class Technician extends User {
    private _availability: boolean;
    private _tickets?: Ticket[];

    constructor(
        id: number,
        fullName: string,
        mail: string,
        username: string,
        password: string,
        availability: boolean = false,
        tickets?: Ticket[],
        phone?: string,
        address?: string,
        joinedDate?: Date,
        avatarUrl?: string
    ) {
        super(id, fullName, mail, username, password, Role.TECHNICIAN, phone, address, joinedDate, avatarUrl);
        this._availability = availability;
        this._tickets = tickets;
        this.role = Role.TECHNICIAN;
    }

    get availability(): boolean {
        return this._availability;
    }

    set availability(value: boolean) {
        this._availability = value;
    }

    get tickets(): Ticket[] | undefined {
        return this._tickets;
    }

    set tickets(value: Ticket[] | undefined) {
        this._tickets = value;
    }
}
