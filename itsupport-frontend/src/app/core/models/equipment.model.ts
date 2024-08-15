import { EquipmentStatus } from "../enums/equipment-status.enum";
import { Client } from "./client.model";
import { EquipmentBreakdown } from "./equipment-panne.model";


export class Equipment {
    private _id: number;
    private _name: string;
    private _model?: string;
    private _status: EquipmentStatus;
    private _serialNumber?: string;
    private _category?: string;
    private _purchasePrice?: number;
    private _purchaseDate?: Date;
    private _photoUrl?: string;
    private _client?: Client;
    private _equipmentBreakdowns?: EquipmentBreakdown[];

    constructor(
        id: number,
        name: string,
        status: EquipmentStatus,
        serialNumber: string,
        model?: string,
        category?: string,
        purchasePrice?: number,
        purchaseDate?: Date,
        photoUrl?: string,
        client?: Client,
        equipmentBreakdowns?: EquipmentBreakdown[]
    ) {
        this._id = id;
        this._name = name;
        this._status = status;
        this._serialNumber = serialNumber;
        this._model = model;
        this._category = category;
        this._purchasePrice = purchasePrice;
        this._purchaseDate = purchaseDate;
        this._photoUrl = photoUrl;
        this._client = client;
        this._equipmentBreakdowns = equipmentBreakdowns;
    }

    get id(): number {
        return this._id;
    }

    set id(id: number) {
        this._id = id;
    }

    get name(): string {
        return this._name;
    }

    set name(name: string) {
        this._name = name;
    }

    get model(): string | undefined {
        return this._model;
    }

    set model(model: string | undefined) {
        this._model = model;
    }

    get status(): EquipmentStatus {
        return this._status;
    }

    set status(status: EquipmentStatus) {
        this._status = status;
    }

    get serialNumber(): string | undefined {
        return this._serialNumber;
    }

    set serialNumber(serialNumber: string) {
        this._serialNumber = serialNumber;
    }

    get category(): string | undefined {
        return this._category;
    }

    set category(category: string | undefined) {
        this._category = category;
    }

    get purchasePrice(): number | undefined {
        return this._purchasePrice;
    }

    set purchasePrice(purchasePrice: number | undefined) {
        this._purchasePrice = purchasePrice;
    }

    get purchaseDate(): Date | undefined {
        return this._purchaseDate;
    }

    set purchaseDate(purchaseDate: Date | undefined) {
        this._purchaseDate = purchaseDate;
    }

    get photoUrl(): string | undefined {
        return this._photoUrl;
    }

    set photoUrl(photoUrl: string | undefined) {
        this._photoUrl = photoUrl;
    }

    get client(): Client | undefined {
        return this._client;
    }

    set client(client: Client | undefined) {
        this._client = client;
    }

    get equipmentBreakdowns(): EquipmentBreakdown[] | undefined {
        return this._equipmentBreakdowns;
    }

    set equipmentBreakdowns(equipmentBreakdowns: EquipmentBreakdown[] | undefined) {
        this._equipmentBreakdowns = equipmentBreakdowns;
    }
}
