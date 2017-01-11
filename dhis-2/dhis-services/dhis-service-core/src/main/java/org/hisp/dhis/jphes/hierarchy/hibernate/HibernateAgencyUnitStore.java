package org.hisp.dhis.jphes.hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.hisp.dhis.common.AuditLogUtil;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.hibernate.exception.ReadAccessDeniedException;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitStore;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public class HibernateAgencyUnitStore extends HibernateIdentifiableObjectStore<AgencyUnit>
    implements AgencyUnitStore
{
    private static final Log log = LogFactory.getLog( HibernateDonorUnitStore.class );

    @Override public AgencyUnit getAgencyUnitByShortName( String shortName )
    {
        List<AgencyUnit> list = getList( Restrictions.eq( "shortName", shortName ) );

        AgencyUnit object = list != null && !list.isEmpty() ? list.get( 0 ) : null;

        if ( !isReadAllowed( object ) )
        {
            AuditLogUtil.infoWrapper( log, currentUserService.getCurrentUsername(), object, AuditLogUtil.ACTION_READ_DENIED );
            throw new ReadAccessDeniedException( object.toString() );
        }

        return object;
    }
}
