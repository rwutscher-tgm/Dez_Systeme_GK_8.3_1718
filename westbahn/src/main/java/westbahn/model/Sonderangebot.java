package westbahn.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

	@OneToMany
	@JoinTable(
			name = "benutzer_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
	private List<Ticket> tickets;

}
