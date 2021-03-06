package westbahn;

import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static Configuration getConfig(Class[] classes){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        for(int i = 0; i < classes.length; i++){
            configuration.addClass(classes[i]);
        }
        return configuration;
    }

}
