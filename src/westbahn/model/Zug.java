package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Zug {

	@Id
	@Column
	private Long ID;

	@Column
	private Date startZeit;

	@Column
	private int sitzPlaetze = 500;

	@Column
	private int fahrradStellplaetze = 50;

	@Column
	private int rollStuhlPlaetze = 10;

	@Column
	private Bahnhof start;

	@Column
	private Bahnhof ende;

}
