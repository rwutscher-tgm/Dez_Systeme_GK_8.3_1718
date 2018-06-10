package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class Zeitkarte extends Ticket {

	@Column
	private Date gueltigAb;

	@Enumerated(
			EnumType.STRING
	)
	@Column
	private ZeitkartenTyp typ;

	public Zeitkarte(Strecke strecke, Zahlung zahlung, Date gueltigAb, ZeitkartenTyp typ) {
		this.strecke = strecke;
		this.zahlung = zahlung;
		this.gueltigAb = gueltigAb;
		this.typ = typ;
	}

}
