package org.hisp.dhis.jphes.hierarchy.donor;


import java.util.List;

/**
 * Created by afya on 11/01/17.
 */
public interface DonorUnitService
{
    String ID = DonorUnitService.class.getName();

    int addDonorUnit(DonorUnit donorUnit);

    void updateDonorUnit(DonorUnit donorUnit);

    void deleteDonorUnit(DonorUnit donorUnit);

    DonorUnit getDonorUnit(String uid);

    DonorUnit getDonorUnit(int id);

    DonorUnit getDonorUnitByName(String name);

    DonorUnit getDonorUnitByCode(String code);

    DonorUnit getDonorUnitByShortName(String shortName);

    List<DonorUnit> getAllDonorUnit();

    List<DonorUnit> getDonorUnits(String name);

    List<DonorUnit> getDonorUnitsBetween(int first, int max);

    List<DonorUnit> getDonorUnitsBetweenByName(String name, int first, int max);

    int getDonorUnitCount();

    int getDonorUnitCountByName(String name);

}
