package org.hisp.dhis.jphes.hierarchy.national;

import java.util.List;

/**
 * @author bangadennis on 05/01/17.
 */
public interface NationalUnitService
{
    String ID = NationalUnitService.class.getName();

    int addNationalUnit(NationalUnit nationalUnit);

    void updateNationalUnit(NationalUnit nationalUnit);

    void deleteNationalUnit(NationalUnit nationalUnit);

    NationalUnit getNationalUnit(String uid);

    NationalUnit getNationalUnit(int id);

    NationalUnit getNationalUnitByName(String name);

    NationalUnit getNationalUnitByCode(String code);

    NationalUnit getNationalUnitByShortName(String shortName);

    List<NationalUnit> getAllNationalUnit();

    List<NationalUnit> getNationalUnits(String name);

    List<NationalUnit> getNationalUnitsBetween(int first, int max);

    List<NationalUnit> getNationalUnitsBetweenByName(String name, int first, int max);

    int getNationalUnitCount();

    int getNationalUnitCountByName(String name);


}
