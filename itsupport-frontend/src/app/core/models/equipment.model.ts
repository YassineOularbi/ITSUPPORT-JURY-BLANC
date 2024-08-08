import { EquipmentStatus } from '../enums/equipment-status.enum';
import { Client } from './client.model';
import { EquipmentBreakdown } from './equipment-panne.model';

export class Equipment {
  private _id: number;
  private _name: string;
  private _status: EquipmentStatus;
  private _client?: Client | undefined;
  private _equipmentBreakdowns?: EquipmentBreakdown[] | undefined;

  constructor(
    id: number,
    name: string,
    status: EquipmentStatus,
    client: Client,
    equipmentBreakdowns: EquipmentBreakdown[] | undefined = []
  ) {
    this._id = id;
    this._name = name;
    this._status = status;
    this._client = client;
    this._equipmentBreakdowns = equipmentBreakdowns;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get status(): EquipmentStatus {
    return this._status;
  }

  set status(value: EquipmentStatus) {
    this._status = value;
  }

  get client(): Client | undefined {
    return this._client;
  }

  set client(value: Client | undefined) {
    this._client = value;
  }

  get equipmentBreakdowns(): EquipmentBreakdown[]  | undefined{
    return this._equipmentBreakdowns;
  }

  set equipmentBreakdowns(value: EquipmentBreakdown[] | undefined) {
    this._equipmentBreakdowns = value;
  }
}
