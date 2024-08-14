import { User } from './user.model';
import { Equipment } from './equipment.model';
import { Ticket } from './ticket.model';
import { Role } from '../enums/role.enum';

export class Client extends User {
  private _equipments?: Equipment[];
  private _tickets?: Ticket[];

  constructor(
    id: number,
    fullName: string,
    mail: string,
    username: string,
    password: string,
    equipments: Equipment[] = [],
    tickets: Ticket[] = [],
    phone?: string,
    address?: string,
    joinedDate?: Date,
    avatarUrl?: string
  ) {
    super(id, fullName, mail, username, password, Role.CLIENT, phone, address, joinedDate, avatarUrl);
    this._equipments = equipments;
    this._tickets = tickets;
    this.role = Role.CLIENT;
  }

  get equipments(): Equipment[] | undefined {
    return this._equipments;
  }

  set equipments(value: Equipment[] | undefined) {
    this._equipments = value;
  }

  get tickets(): Ticket[] | undefined {
    return this._tickets;
  }

  set tickets(value: Ticket[] | undefined) {
    this._tickets = value;
  }
}
