package westbahn.model;

import javax.persistence.*;

@Entity
public class Strecke {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@ManyToOne
	@JoinColumn(name = "start")
	private Bahnhof start;

	@ManyToOne
	@JoinColumn(name = "bahnhof")
	private Bahnhof bahnhof;

	@ManyToOne
	@JoinColumn(name = "ende")
	private Bahnhof ende;

	public Strecke(Bahnhof start, /*Bahnhof bahnhof,*/ Bahnhof ende) {
		this.start = start;
		//this.bahnhof = bahnhof;
		this.ende = ende;
	}

	public Strecke() {
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}
}
