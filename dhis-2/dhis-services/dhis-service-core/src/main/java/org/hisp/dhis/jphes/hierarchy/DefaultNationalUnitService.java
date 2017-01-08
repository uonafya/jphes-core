package org.hisp.dhis.jphes.hierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author bangadennis on 05/01/17.
 */
public class DefaultNationalUnitService implements NationalUnitService
{
    public static final Log log = LogFactory.getLog( DefaultNationalUnitService.class );

    @Autowired
    private NationalUnitStore nationalUnitStore;


    @Override public int addNationalUnit( NationalUnit nationalUnit )
    {
        return nationalUnitStore.save( nationalUnit );
    }

    @Override public void updateNationalUnit( NationalUnit nationalUnit )
    {
        nationalUnitStore.update( nationalUnit );
    }

    @Override public void deleteNationalUnit( NationalUnit nationalUnit )
    {
        nationalUnitStore.delete(  nationalUnit );
    }

    @Override public NationalUnit getNationalUnit( String uid )
    {
        return nationalUnitStore.getByUid( uid );
    }

    @Override public NationalUnit getNationalUnit( int id )
    {
        return nationalUnitStore.get( id );
    }

    @Override public NationalUnit getNationalUnitByName( String name )
    {
        return nationalUnitStore.getByName( name );
    }

    @Override public List<NationalUnit> getAllNationalUnit()
    {
        return nationalUnitStore.getAll();
    }

    @Override public List<NationalUnit> getNationalUnits( String name )
    {
        return nationalUnitStore.getAllLikeName( name );
    }

    @Override public List<NationalUnit> getNationalUnitsBetween( int first, int max )
    {
        return nationalUnitStore.getAll( first, max );
    }

    @Override public List<NationalUnit> getNationalUnitsBetweenByName( String name, int first, int max )
    {
        return nationalUnitStore.getAllLikeName( name, first, max );
    }

    @Override public int getNationalUnitCount()
    {
        return nationalUnitStore.getCount();
    }

    @Override public int getNationalUnitCountByName( String name )
    {
        return nationalUnitStore.getCountLikeName( name );
    }
}
