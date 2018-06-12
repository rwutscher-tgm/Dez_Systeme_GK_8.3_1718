package westbahn.model;

import javax.persistence.*;

@NamedQuery(
		name = "Ticket.findTicketByStrecke",
		query = "SELECT t FROM Ticket t LEFT JOIN t.strecke s WHERE s.id=:streckeID"
)
@Entity
public abstract class Ticket {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long ID;

	@ManyToOne
	@JoinColumn
	protected Strecke strecke;

	//@ManyToOne
	//@JoinColumn
	@Transient
	protected Zahlung zahlung;

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
}
