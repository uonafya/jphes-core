package org.hisp.dhis.jphes_hierarchy.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.jphes_hierarchy.HierarchyStore;
import org.hisp.dhis.jphes_hierarchy.PartnerHierarchy;

import java.util.List;

/**
 * Created by afya on 30/12/16.
 */
public class HibernateHierarchyStore extends HibernateIdentifiableObjectStore<PartnerHierarchy> implements HierarchyStore
{

    private static final Log log = LogFactory.getLog( HibernateHierarchyStore.class );


    // -------------------------------------------------------------------------
    // implementations
    // -------------------------------------------------------------------------

    @Override public List<PartnerHierarchy> getAllPartners()
    {
        return null;
    }

    @Override public PartnerHierarchy getPartnerByName( String name )
    {
        return null;
    }

}
