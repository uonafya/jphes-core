package org.hisp.dhis.jphes_hierarchy;

import java.util.List;

/**
 * Created by afya on 30/12/16.
 */
public interface HierarchyService
{
    String ID = HierarchyService.class.getName();

    int savePartner(PartnerHierarchy partner);

    PartnerHierarchy getPartner(String uid);

    PartnerHierarchy getPartnerByName(String name);

    List<PartnerHierarchy> getPartners(String name);

    List<PartnerHierarchy> getAllPartners();

    List<PartnerHierarchy> getPartnersBetween( int first, int max );

    List<PartnerHierarchy> getPartnersBetweenByName( String name, int first, int max );

    int getPartnerCount();

    int getPartnerCountByName(String name);

    void updatePartner(PartnerHierarchy partner);

    void deletePartner(PartnerHierarchy partner);
}
