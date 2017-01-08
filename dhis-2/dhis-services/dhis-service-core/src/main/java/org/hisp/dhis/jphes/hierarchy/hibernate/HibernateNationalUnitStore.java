package org.hisp.dhis.jphes.hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.common.GenericIdentifiableObjectStore;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitStore;

/**
 * Created by afya on 05/01/17.
 */
public class HibernateNationalUnitStore extends HibernateIdentifiableObjectStore<NationalUnit>
    implements NationalUnitStore
{
    private static final Log log = LogFactory.getLog( HibernateNationalUnitStore.class );
}
