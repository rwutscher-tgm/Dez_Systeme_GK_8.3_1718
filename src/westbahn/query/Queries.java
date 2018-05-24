package westbahn.query;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries(
        {
                @NamedQuery(
                        name = "findAllReservationsByEmail",//query = "from Reservierung r where r.benutzer.= :a"
                        query = "from Reservierung r where r.benutzer = (from Benutzer b where b.eMail = :eMail)"
                ),
                @NamedQuery(
                        name = "findBenutzerWithMonatskarte",
                        query = "from Benutzer b where b.tickets = 'MONATSKARTE'"
                ),
                @NamedQuery(
                        name = "asdasd",
                        query = "from Ticket t where (select f from Strecke f where f.start = :start) "
                )
        }
)

public class Queries {

}
