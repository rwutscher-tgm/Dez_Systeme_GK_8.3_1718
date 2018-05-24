package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Sonderangebot {

	@Id
	@Column
	private Long ID;

	@Column
	private int kontingent = 999;

	@Column
	private Date startZeit;

	@Column
	private int dauer = 12;

	@Column
	private float preisNachlass = 0.5f;

	@Column
	private Ticket tickets;

}
