package westbahn.model;

import westbahn.model.Bahnhof;

import javax.persistence.*;
import java.util.Date;

@Entity
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

	@OneToOne
	@JoinColumn(name = "start")
	private Bahnhof start;

	@OneToOne
	@JoinColumn(name = "ende")
	private Bahnhof ende;

	public Zug() {
	}

	public Zug(Date startZeit, int sitzPlaetze, int fahrradStellplaetze, int rollStuhlPlaetze, Bahnhof start, Bahnhof ende) {
		this.startZeit = startZeit;
		this.sitzPlaetze = sitzPlaetze;
		this.fahrradStellplaetze = fahrradStellplaetze;
		this.rollStuhlPlaetze = rollStuhlPlaetze;
		this.start = start;
		this.ende = ende;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getSitzPlaetze() {
		return sitzPlaetze;
	}

	public void setSitzPlaetze(int sitzPlaetze) {
		this.sitzPlaetze = sitzPlaetze;
	}

	public int getFahrradStellplaetze() {
		return fahrradStellplaetze;
	}

	public void setFahrradStellplaetze(int fahrradStellplaetze) {
		this.fahrradStellplaetze = fahrradStellplaetze;
	}

	public int getRollStuhlPlaetze() {
		return rollStuhlPlaetze;
	}

	public void setRollStuhlPlaetze(int rollStuhlPlaetze) {
		this.rollStuhlPlaetze = rollStuhlPlaetze;
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
}
