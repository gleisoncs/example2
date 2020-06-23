package example;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class CustomIntegrator implements Integrator {

    private static Metadata metadata;

    private static SessionFactoryImplementor sessionFactory;

    private static SessionFactoryServiceRegistry serviceRegistry;

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        CustomIntegrator.metadata = metadata;
        CustomIntegrator.sessionFactory = sessionFactory;
        CustomIntegrator.serviceRegistry = serviceRegistry;
        
        final EventListenerRegistry eventRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventRegistry.prependListeners(EventType.REPLICATE, new IdPreservedReplicateEventListener());
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }

    public static Metadata getMetadata() {
        return metadata;
    }

    public static SessionFactoryImplementor getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactoryServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
}
