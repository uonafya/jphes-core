package org.hisp.dhis.jphes.hierarchy.mechanism;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public interface MechanismUnitService
{
    String ID = MechanismUnitService.class.getName();

    int addMechanismUnit(MechanismUnit mechanismUnit);

    void updateMechanismUnit(MechanismUnit mechanismUnit);

    void deleteMechanismUnit(MechanismUnit mechanismUnit);

    MechanismUnit getMechanismUnit(String uid);

    MechanismUnit getMechanismUnit(int id);

    MechanismUnit getMechanismUnitByName(String name);

    MechanismUnit getMechanismUnitByCode(String code);

    MechanismUnit getMechanismUnitByShortName(String shortName);

    List<MechanismUnit> getAllMechanismUnit();

    List<MechanismUnit> getMechanismUnits(String name);

    List<MechanismUnit> getMechanismUnitsBetween(int first, int max);

    List<MechanismUnit> getMechanismUnitsBetweenByName(String name, int first, int max);

    int getMechanismUnitCount();

    int getMechanismUnitCountByName(String name);
}
