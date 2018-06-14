package westbahn.model;

import javax.persistence.*;

@NamedQuery(
		name = "Ticket.findTicketByStrecke",
//		query = "SELECT t FROM Ticket t LEFT JOIN t.strecke s WHERE s.id=:streckeID"
		query = "SELECT t FROM Benutzer b JOIN b.tickets t WHERE t.strecke.start=:start AND t.strecke.ende=:ende AND t.strecke NOT in (SELECT r.strecke FROM b.reservierungen r)"
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
