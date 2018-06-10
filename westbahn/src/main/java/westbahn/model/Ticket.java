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
	protected Long ID;

	@ManyToOne
	@JoinColumn
	protected Strecke strecke;

	//@ManyToOne
	//@JoinColumn
	@Transient
	protected Zahlung zahlung;

}
