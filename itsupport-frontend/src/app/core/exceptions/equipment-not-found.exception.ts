export class EquipmentNotFoundException extends Error {
    constructor(message: string = 'Equipment not found') {
      super(message);
      this.name = 'EquipmentNotFoundException';
    }
}