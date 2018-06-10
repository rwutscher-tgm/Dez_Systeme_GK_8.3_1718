package westbahn.query;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*@NamedQueries(
        {
                @NamedQuery(
                        name = "findAllReservierungensByEmail",//query = "from Reservierung r where r.benutzer.= :a"
                        query="SELECT r FROM Reservierung r LEFT JOIN r.benutzer b WHERE b=:email"
                        //query = "from Reservierung r where r.benutzer = (from Benutzer b where b.eMail = :eMail)"
                ),
                @NamedQuery(
                        name = "findBenutzerWithMonatskarte",
                        query = "SELECT b FROM Benutzer b LEFT JOIN b.tickets t WHERE t.typ=1"
                        //query = "from Benutzer b where b.tickets = 'MONATSKARTE'"
                ),
                @NamedQuery(
                        name = "findTicketByStrecke",
                        query = "SELECT t FROM Ticket t LEFT JOIN t.strecke s WHERE s.start=:start AND s.ende=:ende"
                        //query = "from Ticket t where (select f from Strecke f where f.start = :start) "
                )
        }
)*/

public class Queries {

}
