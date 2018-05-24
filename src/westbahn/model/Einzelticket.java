package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Einzelticket extends Ticket {

	@Column
	private TicketOption ticketOption;

}
