package org.hisp.dhis.jphes.hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.hisp.dhis.common.AuditLogUtil;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.hibernate.exception.ReadAccessDeniedException;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitStore;

import java.util.List;

/**
 * Created by afya on 11/01/17.
 */
public class HibernateDonorUnitStore extends HibernateIdentifiableObjectStore<DonorUnit>
    implements DonorUnitStore
{
    private static final Log log = LogFactory.getLog( HibernateDonorUnitStore.class );

    @Override public DonorUnit getDonorUnitByShortName( String shortName )
    {

        List<DonorUnit> list = getList( Restrictions.eq( "shortName", shortName ) );

        DonorUnit object = list != null && !list.isEmpty() ? list.get( 0 ) : null;

        if ( !isReadAllowed( object ) )
        {
            AuditLogUtil.infoWrapper( log, currentUserService.getCurrentUsername(), object, AuditLogUtil.ACTION_READ_DENIED );
            throw new ReadAccessDeniedException( object.toString() );
        }

        return object;
    }
}
