package org.hisp.dhis.jphes.hierarchy.agency;

import java.util.List;

/**
 * @author bangadennis on 11/01/17.
 */
public interface AgencyUnitService
{
    String ID = AgencyUnitService.class.getName();

    int addAgencyUnit(AgencyUnit agencyUnit);

    void updateAgencyUnit(AgencyUnit agencyUnit);

    void deleteAgencyUnit(AgencyUnit agencyUnit);

    AgencyUnit getAgencyUnit(String uid);

    AgencyUnit getAgencyUnit(int id);

    AgencyUnit getAgencyUnitByName(String name);

    AgencyUnit getAgencyUnitByCode(String code);

    AgencyUnit getAgencyUnitByShortName(String shortName);

    List<AgencyUnit> getAllAgencyUnit();

    List<AgencyUnit> getAgencyUnits(String name);

    List<AgencyUnit> getAgencyUnitsBetween(int first, int max);

    List<AgencyUnit> getAgencyUnitsBetweenByName(String name, int first, int max);

    int getAgencyUnitCount();

    int getAgencyUnitCountByName(String name);
}
