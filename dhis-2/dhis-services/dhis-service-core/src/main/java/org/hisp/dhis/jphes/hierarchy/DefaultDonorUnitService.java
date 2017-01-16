package org.hisp.dhis.jphes.hierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */

public class DefaultDonorUnitService implements DonorUnitService
{
    public static final Log log = LogFactory.getLog( DefaultDonorUnitService.class );

    @Autowired
    private DonorUnitStore donorUnitStore;

    @Override public int addDonorUnit( DonorUnit donorUnit )
    {
        return donorUnitStore.save( donorUnit );
    }

    @Override public void updateDonorUnit( DonorUnit donorUnit )
    {
        donorUnitStore.update( donorUnit );
    }

    @Override public void deleteDonorUnit( DonorUnit donorUnit )
    {
        donorUnitStore.delete( donorUnit );
    }

    @Override public DonorUnit getDonorUnit( String uid )
    {
        return donorUnitStore.getByUid( uid );
    }

    @Override public DonorUnit getDonorUnit( int id )
    {
        return donorUnitStore.get( id );
    }

    @Override public DonorUnit getDonorUnitByName( String name )
    {
        return donorUnitStore.getByName( name );
    }

    @Override public DonorUnit getDonorUnitByCode( String code )
    {
        return donorUnitStore.getByCode( code );
    }

    @Override public DonorUnit getDonorUnitByShortName( String shortName )
    {
        return donorUnitStore.getDonorUnitByShortName( shortName );
    }

    @Override public List<DonorUnit> getAllDonorUnit()
    {
        return donorUnitStore.getAll();
    }

    @Override public List<DonorUnit> getDonorUnits( String name )
    {
        return donorUnitStore.getAllLikeName( name );
    }

    @Override public List<DonorUnit> getDonorUnitsBetween( int first, int max )
    {
        return donorUnitStore.getAll( first, max );
    }

    @Override public List<DonorUnit> getDonorUnitsBetweenByName( String name, int first, int max )
    {
        return donorUnitStore.getAllLikeName( name, first, max );
    }

    @Override public int getDonorUnitCount()
    {
        return donorUnitStore.getCount();
    }

    @Override public int getDonorUnitCountByName( String name )
    {
        return donorUnitStore.getCountLikeName( name );
    }
}
