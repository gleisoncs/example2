package example;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * This is an example to access sqlserver via hibernate.cfg.hbm file configuration
 * */
public class App5{
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	public static void main(String[] args) {
    	
        EscalationCallOrder eco1 = getEscalationCallOrder();
        
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sqlOn = "SET IDENTITY_INSERT escalation_call_order ON;";
        String sqlOff = "SET IDENTITY_INSERT escalation_call_order OFF";
        
        try {
            transaction = session.beginTransaction();
//            session.createNativeQuery(sqlOn).executeUpdate(); //does not work
            session.createSQLQuery(sqlOn); //does not work
//            session.save(eco1); //works fine
            session.replicate(eco1, ReplicationMode.OVERWRITE);
            session.createSQLQuery(sqlOff);
//            session.createNativeQuery(sqlOff).executeUpdate(); //does not work
          
            transaction.commit();

        	List < EscalationCallOrder > escalations = session.createQuery("from EscalationCallOrder", EscalationCallOrder.class).list();
        	escalations.forEach(s -> System.out.println("escalation: " + s.getId() + " " + s.getName()));

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	private static EscalationCallOrder getEscalationCallOrder() {
		EscalationCallOrder eco = new EscalationCallOrder();
        
        eco.setId(new Long(3));
        eco.setName("Test4");
        eco.setCreatedDate(Calendar.getInstance().getTime());
        eco.setSkipUserOnAvialibility(true);
        eco.setLastUpdatedDate(Calendar.getInstance().getTime());
		return eco;
	}
	
}