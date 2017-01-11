package org.hisp.dhis.jphes.hierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public class DefaultAgencyUnitService implements AgencyUnitService
{
    public static final Log log = LogFactory.getLog( DefaultAgencyUnitService.class );

    @Autowired
    private AgencyUnitStore agencyUnitStore;

    @Override public int addAgencyUnit( AgencyUnit agencyUnit )
    {
        return agencyUnitStore.save( agencyUnit );
    }

    @Override public void updateAgencyUnit( AgencyUnit agencyUnit )
    {
        agencyUnitStore.update( agencyUnit );
    }

    @Override public void deleteAgencyUnit( AgencyUnit agencyUnit )
    {
        agencyUnitStore.delete( agencyUnit );
    }

    @Override public AgencyUnit getAgencyUnit( String uid )
    {
        return agencyUnitStore.getByUid( uid );
    }

    @Override public AgencyUnit getAgencyUnit( int id )
    {
        return agencyUnitStore.get( id );
    }

    @Override public AgencyUnit getAgencyUnitByName( String name )
    {
        return agencyUnitStore.getByName( name );
    }

    @Override public AgencyUnit getAgencyUnitByCode( String code )
    {
        return agencyUnitStore.getByCode( code );
    }

    @Override public AgencyUnit getAgencyUnitByShortName( String shortName )
    {
        return agencyUnitStore.getAgencyUnitByShortName( shortName );
    }

    @Override public List<AgencyUnit> getAllAgencyUnit()
    {
        return agencyUnitStore.getAll();
    }

    @Override public List<AgencyUnit> getAgencyUnits( String name )
    {
        return agencyUnitStore.getAllLikeName( name );
    }

    @Override public List<AgencyUnit> getAgencyUnitsBetween( int first, int max )
    {
        return agencyUnitStore.getAll( first, max );
    }

    @Override public List<AgencyUnit> getAgencyUnitsBetweenByName( String name, int first, int max )
    {
        return agencyUnitStore.getAllLikeName( name, first, max );
    }

    @Override public int getAgencyUnitCount()
    {
        return agencyUnitStore.getCount();
    }

    @Override public int getAgencyUnitCountByName( String name )
    {
        return agencyUnitStore.getCountLikeName( name );
    }
}
