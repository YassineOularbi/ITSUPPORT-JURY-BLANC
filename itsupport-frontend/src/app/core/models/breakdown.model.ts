import { BreakdownPriority } from '../enums/breakdown-priority.enum';
import { BreakdownType } from '../enums/breakdown-type.enum';
import { EquipmentBreakdown } from './equipment-panne.model';

export class Breakdown {
  private _id: number;
  private _name: string;
  private _description: string;
  private _priority: BreakdownPriority;
  private _type: BreakdownType;
  private _equipmentBreakdowns?: EquipmentBreakdown[];

  constructor(
    id: number,
    name: string,
    description: string,
    priority: BreakdownPriority,
    type: BreakdownType,
    equipmentBreakdowns: EquipmentBreakdown[] | undefined = []
  ) {
    this._id = id;
    this._name = name;
    this._description = description;
    this._priority = priority;
    this._type = type;
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

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get priority(): BreakdownPriority {
    return this._priority;
  }

  set priority(value: BreakdownPriority) {
    this._priority = value;
  }

  get type(): BreakdownType {
    return this._type;
  }

  set type(value: BreakdownType) {
    this._type = value;
  }

  get equipmentBreakdowns(): EquipmentBreakdown[] | undefined {
    return this._equipmentBreakdowns;
  }

  set equipmentBreakdowns(value: EquipmentBreakdown[] | undefined) {
    this._equipmentBreakdowns = value;
  }
}
