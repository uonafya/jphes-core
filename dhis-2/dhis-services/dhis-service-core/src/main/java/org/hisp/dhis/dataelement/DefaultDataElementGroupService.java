package org.hisp.dhis.dataelement;

import org.hisp.dhis.dataelement.hibernate.HibernateDataElementGroupStore;

/**
 * Created by xenial on 1/15/17.
 */
public class DefaultDataElementGroupService implements DataElementGroupService {

    private HibernateDataElementGroupStore dataElementGroupStore;

    public void setDataElementGroupStore(HibernateDataElementGroupStore dataElementGroupStore) {
        this.dataElementGroupStore = dataElementGroupStore;
    }

    @Override
    public int addDataElementGroup(DataElementGroup dataElementGroup) {
        return dataElementGroupStore.save(dataElementGroup);
    }

    @Override
    public void updateDataElementGroup(DataElementGroup dataElementGroup) {
       dataElementGroupStore.update(dataElementGroup);
    }

    @Override
    public void deleteDataElementGroup(DataElementGroup dataElementGroup) {
        dataElementGroupStore.delete(dataElementGroup);
    }

    @Override
    public DataElementGroup getDataElementGroup(int id) {
        return dataElementGroupStore.get(id);
    }
}
