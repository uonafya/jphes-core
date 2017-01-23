package org.hisp.dhis.jphes.hierarchy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public class DefaultMechanismUnitService implements MechanismUnitService
{
    public static final Log log = LogFactory.getLog( DefaultMechanismUnitService.class );

    @Autowired
    private MechanismUnitStore mechanismUnitStore;

    @Override public int addMechanismUnit( MechanismUnit mechanismUnit )
    {
        return mechanismUnitStore.save( mechanismUnit );
    }

    @Override public void updateMechanismUnit( MechanismUnit mechanismUnit )
    {
        mechanismUnitStore.update( mechanismUnit );
    }

    @Override public void deleteMechanismUnit( MechanismUnit mechanismUnit )
    {
        mechanismUnitStore.delete( mechanismUnit );
    }

    @Override public MechanismUnit getMechanismUnit( String uid )
    {
        return mechanismUnitStore.getByUid( uid );
    }

    @Override public MechanismUnit getMechanismUnit( int id )
    {
        return mechanismUnitStore.get( id );
    }

    @Override public MechanismUnit getMechanismUnitByName( String name )
    {
        return mechanismUnitStore.getByName( name );
    }

    @Override public MechanismUnit getMechanismUnitByCode( String code )
    {
        return mechanismUnitStore.getByCode( code );
    }

    @Override public MechanismUnit getMechanismUnitByShortName( String shortName )
    {
        return mechanismUnitStore.getMechanismUnitByShortName( shortName );
    }

    @Override public List<MechanismUnit> getAllMechanismUnit()
    {
        return mechanismUnitStore.getAll();
    }

    @Override public List<MechanismUnit> getMechanismUnits( String name )
    {
        return mechanismUnitStore.getAllLikeName( name );
    }

    @Override public List<MechanismUnit> getMechanismUnitsBetween( int first, int max )
    {
        return mechanismUnitStore.getAll( first, max );
    }

    @Override public List<MechanismUnit> getMechanismUnitsBetweenByName( String name, int first, int max )
    {
        return mechanismUnitStore.getAllLikeName( name, first, max );
    }

    @Override public int getMechanismUnitCount()
    {
        return mechanismUnitStore.getCount();
    }

    @Override public int getMechanismUnitCountByName( String name )
    {
        return mechanismUnitStore.getCountLikeName( name );
    }
}

