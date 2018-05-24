package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Strecke {

	@Id
	@Column
	private Long ID;

	@Column
	private Bahnhof start;

	@Column
	private Bahnhof bahnhof;

	@Column
	private Bahnhof ende;

}
