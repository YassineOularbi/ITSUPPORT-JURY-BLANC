export class BreakdownNotFoundException extends Error {
    constructor(message: string = 'Breakdown not found') {
      super(message);
      this.name = 'BreakdownNotFoundException';
    }
}