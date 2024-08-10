import { Breakdown } from "./breakdown.model";
import { EquipmentBreakdownKey } from "./equipment-panne-key.model";
import { Equipment } from "./equipment.model";
import { Ticket } from "./ticket.model";


export class EquipmentBreakdown {
  private _id: EquipmentBreakdownKey;
  private _equipment: Equipment;
  private _breakdown: Breakdown;
  private _tickets?: Ticket[];

  constructor(
    id: EquipmentBreakdownKey,
    equipment: Equipment,
    breakdown: Breakdown,
    tickets: Ticket[] | undefined
  ) {
    this._id = id;
    this._equipment = equipment;
    this._breakdown = breakdown;
    this._tickets = tickets;
  }

  get id(): EquipmentBreakdownKey {
    return this._id;
  }

  set id(value: EquipmentBreakdownKey) {
    this._id = value;
  }

  get equipment(): Equipment {
    return this._equipment;
  }

  set equipment(value: Equipment) {
    this._equipment = value;
  }

  get breakdown(): Breakdown {
    return this._breakdown;
  }

  set breakdown(value: Breakdown) {
    this._breakdown = value;
  }

  get tickets(): Ticket[]  | undefined {
    return this._tickets;
  }

  set tickets(value: Ticket[] | undefined) {
    this._tickets = value;
  }
}
