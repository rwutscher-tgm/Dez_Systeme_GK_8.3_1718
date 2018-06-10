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

	public Sonderangebot(int kontingent, Date startZeit, int dauer, float preisNachlass) {
		this.kontingent = kontingent;
		this.startZeit = startZeit;
		this.dauer = dauer;
		this.preisNachlass = preisNachlass;
	}

	public Sonderangebot() {
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public int getKontingent() {
		return kontingent;
	}

	public void setKontingent(int kontingent) {
		this.kontingent = kontingent;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public float getPreisNachlass() {
		return preisNachlass;
	}

	public void setPreisNachlass(float preisNachlass) {
		this.preisNachlass = preisNachlass;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
