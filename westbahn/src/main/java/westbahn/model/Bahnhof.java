package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bahnhof {

	@Id
	@Column
	private long ID;

	@Column
	private String name;

	@Column
	private int absPreisEntfernung;

	@Column
	private int absKmEntfernung;

	@Column
	private int absZeitEntfernung;

	@Column
	private boolean kopfBahnhof;

}
