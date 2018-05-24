package westbahn.model;

import westbahn.Zahlung;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Ticket {

	@Id
	@Column
	protected Long ID;

	@Column
	protected Strecke strecke;

	@Column
	protected Zahlung zahlung;

}
