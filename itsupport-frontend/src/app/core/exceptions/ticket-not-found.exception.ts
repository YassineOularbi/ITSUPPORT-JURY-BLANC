export class TicketNotFoundException extends Error {
    constructor(message: string = 'Ticket not found') {
      super(message);
      this.name = 'TicketNotFoundException';
    }
}