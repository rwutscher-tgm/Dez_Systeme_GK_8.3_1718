package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Preisstaffelung {

	@Id
	@Column
	private static Long serialVersionUID;

	@Column
	private float grossGepaeck = 1.02f;

	@Column
	private float fahrrad = 1.05f;

	@Column
	private int zeitkarteWoche = 8;

	@Column
	private int zeitkarteMonat = 25;

	@Column
	private int zeitkarteJahr = 250;

	@Column
	private static Preisstaffelung instance;

	@Column
	public static Preisstaffelung getInstance() {
		return null;
	}

	private Preisstaffelung() {

	}

}
