package example;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.event.internal.DefaultReplicateEventListener;
import org.hibernate.event.spi.EventSource;
import org.hibernate.persister.entity.EntityPersister;

public class IdPreservedReplicateEventListener extends DefaultReplicateEventListener {
	
	private static final long serialVersionUID = -8231194671262106504L;
	
	@Override
	/**{@inheritDoc} */
	protected Serializable performSaveOrReplicate(Object entity, EntityKey key,
			EntityPersister persister, boolean useIdentityColumn, Object anything, EventSource source, boolean requiresImmediateIdAccess)
					throws HibernateException {
        if (key == null) {
            Serializable id = persister.getIdentifier(entity, source);
            key = new EntityKey(id, persister);
           
            useIdentityColumn = false;
        }
       
        return super.performSaveOrReplicate(entity, key, persister, useIdentityColumn, anything, source, requiresImmediateIdAccess);
    }
}
