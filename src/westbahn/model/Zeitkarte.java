package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Zeitkarte extends Ticket {

	@Column
	private Date gueltigAb;

	@Column
	private ZeitkartenTyp typ;

}
