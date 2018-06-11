package westbahn;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import westbahn.model.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;


public class Main {

	private static final Logger log = Logger.getLogger(Main.class);
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {
		log.setLevel(Level.ALL);
		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			//task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			task02a();
			task02b();
			task02c();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void fillDB(EntityManager em) throws ParseException {
        Maestro maestro = new Maestro();
        Kreditkarte kreditkarte = new Kreditkarte();
        Praemienmeilen praemienmeilen = new Praemienmeilen();

        Benutzer benutzerChristoph = new Benutzer(
                "Christoph",
                "Pader-Barosch",
                "christoph.pader-barosch@hotmail.com",
                "hallo123",
                "+436801182120"
        );
        Benutzer benutzerMarc = new Benutzer(
                "Marc",
                "Rousavy",
                "mrousavy@hotmail.com",
                "hallo123",
                "+43680123456789"
        );
        Benutzer benutzerBen = new Benutzer(
                "David",
                "Hofer",
                "david.hofer@gmail.com",
                "hallo123",
                "+43680987654321"
        );

        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );
        Bahnhof bahnhofWienHuetteldorf = new Bahnhof(
                "Wien Hütteldorf",
                25,
                50,
                false
        );
        Bahnhof bahnhofSanktPoelten = new Bahnhof(
                "Sankt Pölten",
                40,
                500,
                false
        );
        Bahnhof bahnhofAmstetten = new Bahnhof(
                "Amstetten",
                60,
                200,
                false
        );
        Bahnhof bahnhofLinz = new Bahnhof(
                "Linz",
                50,
                150,
                false
        );
        Bahnhof bahnhofWels = new Bahnhof(
                "Wels",
                20,
                400,
                false
        );
        Bahnhof bahnhofAttnangPuchheim = new Bahnhof(
                "Attnang-Puchheim",
                20,
                500,
                false
        );
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        Strecke streckeWienWestbahnhofLinz = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofLinz
        );
        Strecke streckeWienHuetteldorfWels = new Strecke(
                bahnhofWienHuetteldorf,
                bahnhofWels
        );
        Strecke streckeSanktPoeltenSalzburg = new Strecke(
                bahnhofSanktPoelten,
                bahnhofSalzburg
        );
        Strecke streckeAmstettenWels = new Strecke(
                bahnhofAmstetten,
                bahnhofWels
        );
        Strecke streckeLinzAttnangPuchheim = new Strecke(
                bahnhofLinz,
                bahnhofAttnangPuchheim
        );

        Zug zug1 = new Zug(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofWienHuetteldorf,
                bahnhofSalzburg
        );
        Zug zug2 = new Zug(
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                500, 50, 10,
                bahnhofWienHuetteldorf,
                bahnhofSalzburg
        );
        Zug zug3 = new Zug(
                new java.sql.Date(dateForm.parse("11.03.2018").getTime()),
                500, 50, 10,
                bahnhofWienHuetteldorf,
                bahnhofSalzburg
        );
        Zug zug4 = new Zug(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofSalzburg,
                bahnhofWienWestbahnhof
        );
        Zug zug5 = new Zug(
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                500, 50, 10,
                bahnhofSalzburg,
                bahnhofWienWestbahnhof
        );


        Reservierung reservierung1 = new Reservierung(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                15,
                150,
                StatusInfo.ONTIME,
                zug1,
                streckeWienWestbahnhofLinz,
                benutzerChristoph,
                maestro
        );
        Reservierung reservierung2 = new Reservierung(
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                15,
                150,
                StatusInfo.DELAYED,
                zug2,
                streckeAmstettenWels,
                benutzerChristoph,
                maestro
        );
        Reservierung reservierung3 = new Reservierung(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                15,
                150,
                StatusInfo.ONTIME,
                zug2,
                streckeWienWestbahnhofLinz,
                benutzerBen,
                kreditkarte
        );
        Reservierung reservierung4 = new Reservierung(
                new java.sql.Date(dateForm.parse("11.03.2018").getTime()),
                15,
                150,
                StatusInfo.DELAYED,
                zug3,
                streckeSanktPoeltenSalzburg,
                benutzerMarc,
                maestro
        );
        Reservierung reservierung5 = new Reservierung(
                new java.sql.Date(dateForm.parse("12.03.2018").getTime()),
                15,
                150,
                StatusInfo.ONTIME,
                zug3,
                streckeWienWestbahnhofLinz,
                benutzerChristoph,
                maestro
        );

        Ticket einzelticketFahrrad = new Einzelticket(
                streckeAmstettenWels,
                maestro,
                TicketOption.FAHRRAD
        );
        Ticket einzelticketGrossgepaeck = new Einzelticket(
                streckeWienWestbahnhofLinz,
                kreditkarte,
                TicketOption.GROSSGEPAECK
        );
        Ticket zeitkarteWoche = new Zeitkarte(
                streckeLinzAttnangPuchheim,
                kreditkarte,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.WOCHENKARTE
        );
        Ticket zeitkarteMonat = new Zeitkarte(
                streckeAmstettenWels,
                maestro,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.MONATSKARTE
        );
        Ticket zeitkarteJahr = new Zeitkarte(
                streckeWienHuetteldorfWels,
                maestro,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.JAHRESKARTE
        );

        Sonderangebot sonderangebot1 = new Sonderangebot(
                100,
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                200,
                0.5f
        );
        Sonderangebot sonderangebot2 = new Sonderangebot(
                1500,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                100,
                0.95f
        );
        Sonderangebot sonderangebot3 = new Sonderangebot(
                500,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                1000,
                0.75f
        );
        Sonderangebot sonderangebot4 = new Sonderangebot(
                1500,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                200,
                0.95f
        );
        Sonderangebot sonderangebot5 = new Sonderangebot(
                999,
                new java.sql.Date(dateForm.parse("12.03.2018").getTime()),
                100,
                0.66f
        );

        // Transactions

        // Benutzer
        save(em, benutzerChristoph);
        save(em, benutzerMarc);
        save(em, benutzerBen);

        // Bahnhöfe
        save(em, bahnhofWienWestbahnhof);
        save(em, bahnhofWienHuetteldorf);
        save(em, bahnhofSanktPoelten);
        save(em, bahnhofAmstetten);
        save(em, bahnhofLinz);
        save(em, bahnhofWels);
        save(em, bahnhofAttnangPuchheim);
        save(em, bahnhofSalzburg);


        // Strecken
        save(em, streckeWienWestbahnhofLinz);
        save(em, streckeWienHuetteldorfWels);
        save(em, streckeSanktPoeltenSalzburg);
        save(em, streckeAmstettenWels);
        save(em, streckeLinzAttnangPuchheim);

        // Züge
        save(em, zug1);
        save(em, zug2);
        save(em, zug3);
        save(em, zug4);
        save(em, zug5);

        // Reservierungen
        save(em, reservierung1);
        save(em, reservierung2);
        save(em, reservierung3);
        save(em, reservierung4);
        save(em, reservierung5);

        // Tickets
        save(em, einzelticketFahrrad);
        save(em, einzelticketGrossgepaeck);
        save(em, zeitkarteWoche);
        save(em, zeitkarteMonat);
        save(em, zeitkarteJahr);

        // Sonderangebote
        save(em, sonderangebot1);
        save(em, sonderangebot2);
        save(em, sonderangebot3);
        save(em, sonderangebot4);
        save(em, sonderangebot5);

        em.getTransaction().begin();
        benutzerChristoph.setTickets(Collections.singletonList(zeitkarteMonat));
        benutzerChristoph.setReservierungen(Arrays.asList(reservierung1, reservierung2, reservierung5));
        em.getTransaction().commit();

        em.getTransaction().begin();
        benutzerMarc.setTickets(Collections.singletonList(einzelticketFahrrad));
        benutzerMarc.setReservierungen(Collections.singletonList(reservierung4));
        em.getTransaction().commit();

        em.getTransaction().begin();
        benutzerBen.setTickets(Collections.singletonList(zeitkarteJahr));
        benutzerBen.setReservierungen(Collections.singletonList(reservierung3));
        em.getTransaction().commit();
    }

    public static void save(EntityManager em, Object o){
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

	public static void task01() throws ParseException, InterruptedException {

        /*
        DAO<Bahnhof> bahnhofDAO = new DAO<>();
        bahnhofDAO.save(new Bahnhof());
        bahnhofDAO.close();
        */


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("westbahn");
        EntityManager em;
        em = factory.createEntityManager();
        fillDB(em);
        em.close();

        /*System.out.print("Seas");
        DAO<Bahnhof> bahnhofDAO = new DAO<>();
        bahnhofDAO.save(new Bahnhof());
        bahnhofDAO.close();*/



	}

	public static void task02a() throws ParseException {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("westbahn");
        EntityManager em;
        em = factory.createEntityManager();


        String email = "david.hofer@gmail.com";
        List<Reservierung> reservierungen = em.createNamedQuery("Reservierung.findAllReservierungensByEmail")
        .setParameter("email", email)
        .getResultList();
        for (Reservierung reservierung : reservierungen) {
            System.out.println("Reservierung für " + email + ": " + reservierung);
        }
        em.close();
	}

	public static void task02b() throws ParseException {

	}

	public static void task02c() throws ParseException {

	}

}
