import junit.framework.TestCase;
import org.junit.Test;
import westbahn.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;

public class TestPersistObjects extends TestCase {

    private EntityManagerFactory factory;
    private EntityManager em;
    private SimpleDateFormat dateForm;
    private SimpleDateFormat timeForm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.factory = Persistence.createEntityManagerFactory("westbahn");
        this.em = factory.createEntityManager();
        this.dateForm = new SimpleDateFormat("dd.MM.yyyy");
        this.timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");
        clearDatabase();
    }

    @Test
    public void testCreateBenutzer(){
        //Create User
        Benutzer benutzerNemo = new Benutzer(
                "Nemo",
                "Tesanovic",
                "ntesanovic@student.tgm.ac.at",
                "hallo123",
                "+436801182120"
        );

        //Persist User
        em.getTransaction().begin();
        em.persist(benutzerNemo);
        em.getTransaction().commit();

        //Load User
        em.getTransaction().begin();
        List<Benutzer> benutzer= em.createQuery("FROM Benutzer b WHERE b.eMail='ntesanovic@student.tgm.ac.at'").getResultList();
        em.getTransaction().commit();

        //Verify User
        assertEquals("Nemo", benutzer.get(0).getVorName());
        assertEquals("Tesanovic", benutzer.get(0).getNachName());
        assertEquals("ntesanovic@student.tgm.ac.at", benutzer.get(0).geteMail());
        assertEquals("hallo123", benutzer.get(0).getPasswort());
        assertEquals("+436801182120", benutzer.get(0).getSmsNummer());
    }

    @Test
    public void testCreateBahnhof(){
        //Create Bahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );

        //Persist Bahnhof
        em.getTransaction().begin();
        em.persist(bahnhofWienWestbahnhof);
        em.getTransaction().commit();

        //Load Bahnhof
        em.getTransaction().begin();
        List<Bahnhof> bahnhof= em.createQuery("FROM Bahnhof b WHERE b.name='Wien Westbahnhof'").getResultList();
        em.getTransaction().commit();

        //Verify Bahnhof
        assertEquals("Wien Westbahnhof", bahnhof.get(0).getName());
        assertEquals(10, bahnhof.get(0).getAbsKmEntfernung());
        assertEquals(100, bahnhof.get(0).getAbsZeitEntfernung());
        assertEquals(true, bahnhof.get(0).isKopfBahnhof());
    }

    @Test
    public void testCreateStrecke(){
        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        //Create Strecke Wien-Salzburg
        Strecke streckeWienWestbahnhofSalzburg = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        //Persist Objects
        em.getTransaction().begin();
        em.persist(bahnhofWienWestbahnhof);
        em.persist(bahnhofSalzburg);
        em.persist(streckeWienWestbahnhofSalzburg);
        em.getTransaction().commit();

        //Load Strecke
        em.getTransaction().begin();
        List<Strecke> strecke= em.createQuery("FROM Strecke s WHERE s.start=:start AND s.ende=:ende")
                .setParameter("start", bahnhofWienWestbahnhof)
                .setParameter("ende", bahnhofSalzburg)
                .getResultList();
        em.getTransaction().commit();

        //Verify Strecke
        assertEquals("Wien Westbahnhof", strecke.get(0).getStart().getName());
        assertEquals("Salzburg", strecke.get(0).getEnde().getName());
    }

    @Test
    public void testCreateZug() throws ParseException {
        //Create Linz Bahnhof
        Bahnhof bahnhofLinz = new Bahnhof(
                "Linz",
                50,
                150,
                false
        );

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );
        //Create Zug
        Zug zug1 = new Zug(
                new java.sql.Date(this.dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofLinz,
                bahnhofSalzburg
        );

        //Persist Objects
        em.getTransaction().begin();
        em.persist(bahnhofLinz);
        em.persist(bahnhofSalzburg);
        em.persist(zug1);
        em.getTransaction().commit();

        //Load Strecke
        em.getTransaction().begin();
        List<Zug> zug= em.createQuery("FROM Zug z WHERE z.start=:start AND z.ende=:ende")
                .setParameter("start", bahnhofLinz)
                .setParameter("ende", bahnhofSalzburg)
                .getResultList();
        em.getTransaction().commit();



        //Verify Zug
        assertEquals("Linz", zug.get(0).getStart().getName());
        assertEquals("Salzburg", zug.get(0).getEnde().getName());
        assertEquals(500, zug.get(0).getSitzPlaetze());
        assertEquals(50, zug.get(0).getFahrradStellplaetze());
        assertEquals(10, zug.get(0).getRollStuhlPlaetze());
        assertEquals("2018-03-09", zug.get(0).getStartZeit().toString());

    }

    @Test
    public void testCreateReservierung() throws ParseException {

        //Create User
        Benutzer benutzerNemo = new Benutzer(
                "Nemo",
                "Tesanovic",
                "ntesanovic@student.tgm.ac.at",
                "hallo123",
                "+436801182120"
        );

        //Create Linz Bahnhof
        Bahnhof bahnhofLinz = new Bahnhof(
                "Linz",
                50,
                150,
                false
        );

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );

        //Create Strecke Wien-Salzburg
        Strecke streckeWienWestbahnhofSalzburg = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        //Create Zug
        Zug zug1 = new Zug(
                new java.sql.Date(this.dateForm.parse("09.03.2018").getTime()),
                500, 50, 10,
                bahnhofLinz,
                bahnhofSalzburg
        );

        //Create Zahlung
        Maestro maestro = new Maestro();

        //Create Reservierung
        Reservierung reservierung1 = new Reservierung(
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                15,
                150,
                StatusInfo.ONTIME,
                zug1,
                streckeWienWestbahnhofSalzburg,
                benutzerNemo,
                maestro
        );

        //Persist Objects
        em.getTransaction().begin();

        em.persist(benutzerNemo);
        em.persist(bahnhofLinz);
        em.persist(bahnhofSalzburg);
        em.persist(bahnhofWienWestbahnhof);
        em.persist(zug1);
        em.persist(streckeWienWestbahnhofSalzburg);
        em.persist(reservierung1);

        em.getTransaction().commit();

        //Load Reservierung
        em.getTransaction().begin();
        List<Reservierung> reservierung= em.createQuery("FROM Reservierung r WHERE r.strecke=:strecke AND r.benutzer=:benutzer")
                .setParameter("strecke", streckeWienWestbahnhofSalzburg)
                .setParameter("benutzer", benutzerNemo)
                .getResultList();
        em.getTransaction().commit();

        //Verify Reservierung
        assertEquals("2018-03-09", reservierung.get(0).getDatum().toString());
        assertEquals(15, reservierung.get(0).getPraemienMeilenBonus());
        assertEquals(150, reservierung.get(0).getPreis());
        assertEquals(StatusInfo.ONTIME, reservierung.get(0).getStatus());

        assertEquals("Linz", reservierung.get(0).getZug().getStart().getName());
        assertEquals("Salzburg", reservierung.get(0).getZug().getEnde().getName());

        assertEquals("Wien Westbahnhof", reservierung.get(0).getStrecke().getStart().getName());
        assertEquals("Salzburg", reservierung.get(0).getStrecke().getEnde().getName());

        assertEquals("Nemo", reservierung.get(0).getBenutzer().getVorName());
        assertEquals("Tesanovic", reservierung.get(0).getBenutzer().getNachName());
        assertEquals("ntesanovic@student.tgm.ac.at", reservierung.get(0).getBenutzer().geteMail());
        assertEquals("hallo123", reservierung.get(0).getBenutzer().getPasswort());
        assertEquals("+436801182120", reservierung.get(0).getBenutzer().getSmsNummer());
    }

    @Test
    public void testCreateEinzelTicket(){

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );
        //Create Strecke
        Strecke streckeWienWestbahnhofSalzburg = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        //Create Zahlung
        Maestro maestro = new Maestro();

        //Create Ticket
        Ticket einzelticketFahrrad = new Einzelticket(
                streckeWienWestbahnhofSalzburg,
                maestro,
                TicketOption.FAHRRAD
        );

        //Persist Objects
        em.getTransaction().begin();

        em.persist(bahnhofSalzburg);
        em.persist(bahnhofWienWestbahnhof);
        em.persist(streckeWienWestbahnhofSalzburg);
        em.persist(einzelticketFahrrad);

        em.getTransaction().commit();

        //Load Ticket
        em.getTransaction().begin();
        List<Einzelticket> einzelTicket= em.createQuery("FROM Einzelticket t WHERE t.strecke=:strecke")
                .setParameter("strecke", streckeWienWestbahnhofSalzburg)
                .getResultList();
        em.getTransaction().commit();

        //Verify Ticket
        assertEquals("Wien Westbahnhof", einzelTicket.get(0).getStrecke().getStart().getName());
        assertEquals("Salzburg", einzelTicket.get(0).getStrecke().getEnde().getName());

        assertEquals(TicketOption.FAHRRAD, einzelTicket.get(0).getTicketOption());
    }

    @Test
    public void testCreateZeitKarte() throws ParseException {

        //Create Salzburg Bahnhof
        Bahnhof bahnhofSalzburg = new Bahnhof(
                "Salzburg",
                50,
                200,
                true
        );

        //Create Westbahnhof
        Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
                "Wien Westbahnhof",
                10,
                100,
                true
        );
        //Create Strecke
        Strecke streckeWienWestbahnhofSalzburg = new Strecke(
                bahnhofWienWestbahnhof,
                bahnhofSalzburg
        );

        //Create Zahlung
        Maestro maestro = new Maestro();

        //Create Ticket
        Ticket zeitkarteWoche = new Zeitkarte(
                streckeWienWestbahnhofSalzburg,
                maestro,
                new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
                ZeitkartenTyp.WOCHENKARTE
        );

        //Persist Objects
        em.getTransaction().begin();

        em.persist(bahnhofSalzburg);
        em.persist(bahnhofWienWestbahnhof);
        em.persist(streckeWienWestbahnhofSalzburg);
        em.persist(zeitkarteWoche);

        em.getTransaction().commit();

        //Load Ticket
        em.getTransaction().begin();
        List<Zeitkarte> zeitkarte= em.createQuery("FROM Zeitkarte t WHERE t.strecke=:strecke")
                .setParameter("strecke", streckeWienWestbahnhofSalzburg)
                .getResultList();
        em.getTransaction().commit();

        //Verify Ticket
        assertEquals("Wien Westbahnhof", zeitkarte.get(0).getStrecke().getStart().getName());
        assertEquals("Salzburg", zeitkarte.get(0).getStrecke().getEnde().getName());

        assertEquals(ZeitkartenTyp.WOCHENKARTE, zeitkarte.get(0).getTyp());
        assertEquals("2018-03-09", zeitkarte.get(0).getGueltigAb().toString());
    }

    @Test
    public void testCreateSonderangebot() throws ParseException {

        //Create Sonderangebot
        Sonderangebot sonderangebot1 = new Sonderangebot(
                100,
                new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
                200,
                0.5f
        );

        //Persist Sonderangebot
        em.getTransaction().begin();
        em.persist(sonderangebot1);
        em.getTransaction().commit();

        //Load Reservierung
        em.getTransaction().begin();
        List<Sonderangebot> sonderangebot= em.createQuery("FROM Sonderangebot s WHERE s.kontingent=100 AND s.dauer=200")
                .getResultList();
        em.getTransaction().commit();

        //Verify Sonderangebot
        assertEquals(100, sonderangebot.get(0).getKontingent());
        assertEquals(200, sonderangebot.get(0).getDauer());
        assertEquals(0.5f, sonderangebot.get(0).getPreisNachlass());
        assertEquals("2018-03-10", sonderangebot.get(0).getStartZeit().toString());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.factory.close();
//        this.em.close();
        clearDatabase();
    }

    public void clearDatabase(){
        try{
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Bahnhof").executeUpdate();
            em.createQuery("DELETE FROM Benutzer").executeUpdate();
            em.createQuery("DELETE FROM Reservierung").executeUpdate();
            em.createQuery("DELETE FROM Sonderangebot").executeUpdate();
            em.createQuery("DELETE FROM Strecke").executeUpdate();
            em.createQuery("DELETE FROM Ticket").executeUpdate();
            em.createQuery("DELETE FROM Zug").executeUpdate();
            em.getTransaction().commit();
        }catch(Exception e){

        }
    }
}
