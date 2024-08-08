export class TicketDto {
    private _description: string;

    constructor(description: string) {
        this._description = description;
    }

    get description(): string {
        return this._description;
    }

    set description(description: string) {
        this._description = description;
    }
}
