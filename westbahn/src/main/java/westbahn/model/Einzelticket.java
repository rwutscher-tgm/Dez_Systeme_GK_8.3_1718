package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Einzelticket extends Ticket {

	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_option")
	private TicketOption ticketOption;

	public Einzelticket(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}

	public Einzelticket() {
	}

	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}

    public Einzelticket(Strecke strecke, Zahlung zahlung, TicketOption ticketOption) {
        this.strecke = strecke;
        this.zahlung = zahlung;
        this.ticketOption = ticketOption;
	}
}
