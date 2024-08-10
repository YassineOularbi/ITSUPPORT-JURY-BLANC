import { EquipmentBreakdown } from "./equipment-panne.model";

export class Breakdown {
  private _id: number;
  private _name: string;
  private _equipmentBreakdowns?: EquipmentBreakdown[];

  constructor(
    id: number,
    name: string,
    equipmentBreakdowns: EquipmentBreakdown[] | undefined = []
  ) {
    this._id = id;
    this._name = name;
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

  get equipmentBreakdowns(): EquipmentBreakdown[] | undefined {
    return this._equipmentBreakdowns;
  }

  set equipmentBreakdowns(value: EquipmentBreakdown[] | undefined) {
    this._equipmentBreakdowns = value;
  }
}
