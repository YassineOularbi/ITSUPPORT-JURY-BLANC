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
    tickets: Ticket[] | undefined = []
  ) {
    super(id, fullName, mail, username, password, Role.TECHNICIAN);
    this._availability = availability;
    this._tickets = tickets;
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

  set tickets(value: Ticket[]  | undefined) {
    this._tickets = value;
  }
}
