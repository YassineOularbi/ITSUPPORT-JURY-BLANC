export class TechnicianNotFoundException extends Error {
    constructor(message: string = 'Technician not found') {
      super(message);
      this.name = 'TechnicianNotFoundException';
    }
}