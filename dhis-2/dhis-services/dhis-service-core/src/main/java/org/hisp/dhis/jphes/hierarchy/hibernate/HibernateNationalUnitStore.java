package org.hisp.dhis.jphes.hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.hisp.dhis.common.AuditLogUtil;
import org.hisp.dhis.common.GenericIdentifiableObjectStore;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.hibernate.exception.ReadAccessDeniedException;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitStore;

import java.util.List;

/**
 * Created by afya on 05/01/17.
 */
public class HibernateNationalUnitStore extends HibernateIdentifiableObjectStore<NationalUnit>
    implements NationalUnitStore
{
    private static final Log log = LogFactory.getLog( HibernateNationalUnitStore.class );

    @Override public NationalUnit getNationalUnitByShortName( String shortName )
    {
        List<NationalUnit> list = getList( Restrictions.eq( "shortName", shortName ) );

        NationalUnit object = list != null && !list.isEmpty() ? list.get( 0 ) : null;

        if ( !isReadAllowed( object ) )
        {
            AuditLogUtil.infoWrapper( log, currentUserService.getCurrentUsername(), object, AuditLogUtil.ACTION_READ_DENIED );
            throw new ReadAccessDeniedException( object.toString() );
        }

        return object;
    }
}
