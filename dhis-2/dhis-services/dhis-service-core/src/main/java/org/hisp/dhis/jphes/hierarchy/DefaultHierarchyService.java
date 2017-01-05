package org.hisp.dhis.jphes.hierarchy;

import java.util.List;

/**
 * Created by afya on 30/12/16.
 */
public class DefaultHierarchyService implements HierarchyService
{

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private HierarchyStore hierarchyStore;

    public void setHierarchyStore(HierarchyStore hierarchyStore){ this.hierarchyStore=hierarchyStore; }

    // -------------------------------------------------------------------------
    // Service implementation
    // -------------------------------------------------------------------------

    @Override public int savePartner( PartnerHierarchy partner )
    {
        return hierarchyStore.save( partner );
    }

    @Override public PartnerHierarchy getPartner( String uid )
    {
        return hierarchyStore.getByUid( uid );
    }

    @Override public PartnerHierarchy getPartnerByName( String name )
    {
        return hierarchyStore.getByName( name );
    }

    @Override public List<PartnerHierarchy> getPartners( String name )
    {
        return hierarchyStore.getAllLikeName( name );
    }

    @Override public List<PartnerHierarchy> getAllPartners()
    {
        return hierarchyStore.getAll();
    }

    @Override public List<PartnerHierarchy> getPartnersBetween( int first, int max )
    {
        return hierarchyStore.getAllOrderedName( first, max );
    }

    @Override public List<PartnerHierarchy> getPartnersBetweenByName( String name, int first, int max )
    {
        return hierarchyStore.getAllLikeName( name, first, max );
    }

    @Override public int getPartnerCount()
    {
        return hierarchyStore.getCount();
    }

    @Override public int getPartnerCountByName( String name )
    {
        return hierarchyStore.getCountLikeName( name );
    }

    @Override public void updatePartner( PartnerHierarchy partner )
    {
        hierarchyStore.update( partner );
    }

    @Override public void deletePartner( PartnerHierarchy partner )
    {
        hierarchyStore.delete( partner );
    }
}
