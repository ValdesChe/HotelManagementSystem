package utils;

import org.hibernate.Session;

import javax.faces.context.FacesContext;

/**
 * Created by ValdoR on 2019-12-11.
 */

// Notre gestionnaire de session utilisateur
public class FacesContextUtil {
    private static final String HIBERNATE_SESSION = "HIBERNATE_SESSION";

    public static void setRequestSession(Session session){
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(HIBERNATE_SESSION, session);
    }

    public static Session getRequestSession() {
        return (Session)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(HIBERNATE_SESSION);
    }
}
