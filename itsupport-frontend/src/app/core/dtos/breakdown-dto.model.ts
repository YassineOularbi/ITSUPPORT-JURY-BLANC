import { BreakdownPriority } from "../enums/breakdown-priority.enum";
import { BreakdownType } from "../enums/breakdown-type.enum";

export class BreakdownDto {
    private _name: string;
    private _description: string;
    private _priority: BreakdownPriority;
    private _type: BreakdownType;

    constructor(
        name: string,
        description: string,
        priority: BreakdownPriority,
        type: BreakdownType
    ) {
        this._name = name;
        this._description = description;
        this._priority = priority;
        this._type = type;
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
}
