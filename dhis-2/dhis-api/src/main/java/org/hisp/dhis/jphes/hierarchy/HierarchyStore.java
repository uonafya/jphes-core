package org.hisp.dhis.jphes.hierarchy;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

import java.util.List;

/**
 * Created by afya on 30/12/16.
 */
public interface HierarchyStore extends GenericIdentifiableObjectStore<PartnerHierarchy>
{
    List<PartnerHierarchy> getAllPartners();

    PartnerHierarchy getPartnerByName(String name);
}
