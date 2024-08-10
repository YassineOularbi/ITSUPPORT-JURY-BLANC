export class EquipmentBreakdownKey {
    private _equipmentId: number;
    private _breakdownId: number;
  
    constructor(equipmentId: number, breakdownId: number) {
      this._equipmentId = equipmentId;
      this._breakdownId = breakdownId;
    }
  
    get equipmentId(): number {
      return this._equipmentId;
    }
  
    set equipmentId(value: number) {
      this._equipmentId = value;
    }
  
    get breakdownId(): number {
      return this._breakdownId;
    }
  
    set breakdownId(value: number) {
      this._breakdownId = value;
    }

    equals(other: EquipmentBreakdownKey): boolean {
      if (this === other) return true;
      if (!other) return false;
      return this._equipmentId === other._equipmentId && this._breakdownId === other._breakdownId;
    }

    hashCode(): number {
      const prime = 31;
      let result = 1;
      result = prime * result + (this._equipmentId ?? 0);
      result = prime * result + (this._breakdownId ?? 0);
      return result;
    }
  }
  