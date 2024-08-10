import { TicketStatus } from '../enums/ticket-status.enum';
import { Client } from './client.model';
import { EquipmentBreakdown } from './equipment-panne.model';
import { Technician } from './technician.model';

export class Ticket {
  private _id: number;
  private _description: string;
  private _reportingDate: Date;
  private _lastUpdated: Date;
  private _resolutionDate?: Date;
  private _status: TicketStatus;
  private _client?: Client;
  private _technician?: Technician;
  private _equipmentBreakdown?: EquipmentBreakdown;

  constructor(
    id: number,
    description: string,
    reportingDate: Date,
    lastUpdated: Date,
    resolutionDate: Date | undefined,
    status: TicketStatus,
    client: Client | undefined,
    technician: Technician | undefined,
    equipmentBreakdown: EquipmentBreakdown | undefined
  ) {
    this._id = id;
    this._description = description;
    this._reportingDate = reportingDate;
    this._lastUpdated = lastUpdated;
    this._resolutionDate = resolutionDate;
    this._status = status;
    this._client = client;
    this._technician = technician;
    this._equipmentBreakdown = equipmentBreakdown;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get reportingDate(): Date {
    return this._reportingDate;
  }

  set reportingDate(value: Date) {
    this._reportingDate = value;
  }

  get lastUpdated(): Date {
    return this._lastUpdated;
  }

  set lastUpdated(value: Date) {
    this._lastUpdated = value;
  }

  get resolutionDate(): Date | undefined {
    return this._resolutionDate;
  }

  set resolutionDate(value: Date | undefined) {
    this._resolutionDate = value;
  }

  get status(): TicketStatus {
    return this._status;
  }

  set status(value: TicketStatus) {
    this._status = value;
  }

  get client(): Client | undefined {
    return this._client;
  }

  set client(value: Client | undefined) {
    this._client = value;
  }

  get technician(): Technician | undefined {
    return this._technician;
  }

  set technician(value: Technician | undefined) {
    this._technician = value;
  }

  get equipmentBreakdown(): EquipmentBreakdown | undefined {
    return this._equipmentBreakdown;
  }

  set equipmentBreakdown(value: EquipmentBreakdown | undefined) {
    this._equipmentBreakdown = value;
  }
}
