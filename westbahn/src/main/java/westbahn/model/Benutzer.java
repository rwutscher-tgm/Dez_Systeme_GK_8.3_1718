package westbahn.model;

import javax.persistence.*;
import java.util.List;



@NamedQuery(
		name = "Benutzer.findBenutzerWithMonatskarte",
		query = "SELECT b FROM Benutzer b LEFT JOIN b.tickets t WHERE t.typ='MONATSKARTE'"
)
@Entity
@Table(name = "benutzer")
public class Benutzer {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String vorName;

	@Column
	private String nachName;

	@Column
	private String eMail;

	@Column
	private String passwort;

	@Column
	private String smsNummer;

	@Column
	private long verbuchtePraemienMeilen;

	@OneToMany
	@JoinTable(
			name = "benutzer_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
	private List<Ticket> tickets;

	@OneToMany
	@JoinTable(
			name = "benutzer_reservierungen",
			joinColumns = {@JoinColumn(referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
	private List<Reservierung> reservierungen;

	public long getID() {
		return id;
	}

	public void setID(long ID) {
		this.id = ID;
	}

	public String getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName = vorName;
	}

	public String getNachName() {
		return nachName;
	}

	public void setNachName(String nachName) {
		this.nachName = nachName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getSmsNummer() {
		return smsNummer;
	}

	public void setSmsNummer(String smsNummer) {
		this.smsNummer = smsNummer;
	}

	public long getVerbuchtePraemienMeilen() {
		return verbuchtePraemienMeilen;
	}

	public void setVerbuchtePraemienMeilen(long verbuchtePraemienMeilen) {
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}

	public void setReservierungen(List<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}

    public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer) {
        this.vorName = vorName;
        this.nachName = nachName;
        this.eMail = eMail;
        this.passwort = passwort;
        this.smsNummer = smsNummer;
    }

    public Benutzer() {
    }
}
