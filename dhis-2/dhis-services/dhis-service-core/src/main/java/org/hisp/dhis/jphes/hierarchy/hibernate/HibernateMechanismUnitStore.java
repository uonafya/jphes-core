package org.hisp.dhis.jphes.hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.hisp.dhis.common.AuditLogUtil;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.hibernate.exception.ReadAccessDeniedException;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitStore;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public class HibernateMechanismUnitStore extends HibernateIdentifiableObjectStore<MechanismUnit>
    implements MechanismUnitStore
{
    private static final Log log = LogFactory.getLog( HibernateMechanismUnitStore.class );

    @Override public MechanismUnit getMechanismUnitByShortName( String shortName )
    {
        List<MechanismUnit> list = getList( Restrictions.eq( "shortName", shortName ) );

        MechanismUnit object = list != null && !list.isEmpty() ? list.get( 0 ) : null;

        if ( !isReadAllowed( object ) )
        {
            AuditLogUtil.infoWrapper( log, currentUserService.getCurrentUsername(), object, AuditLogUtil.ACTION_READ_DENIED );
            throw new ReadAccessDeniedException( object.toString() );
        }

        return object;
    }
}
