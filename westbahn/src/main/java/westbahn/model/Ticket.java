package westbahn.model;

import javax.persistence.*;

@NamedQuery(
		name = "findTicketByStrecke",
		query = "SELECT t FROM Ticket t LEFT JOIN t.strecke s WHERE s.start=:start AND s.ende=:ende"
		//query = "from Ticket t where (select f from Strecke f where f.start = :start) "
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
