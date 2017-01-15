package org.hisp.dhis.dataelement;

/**
 * Created by xenial on 1/15/17.
 */
public interface DataElementGroupService {

    int addDataElementGroup(DataElementGroup dataElementGroup);

    void updateDataElementGroup(DataElementGroup dataElementGroup);

    void deleteDataElementGroup(DataElementGroup dataElementGroup);

    DataElementGroup getDataElementGroup(int id);
}
