package org.hisp.dhis.jphes.program.hibernate;

import org.hibernate.criterion.Restrictions;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramElement;
import org.hisp.dhis.jphes.program.ProgramElementStore;

/**
 * Created by xenial on 1/11/17.
 */
public class HIbernateProgramElementStore extends HibernateIdentifiableObjectStore<ProgramElement> implements ProgramElementStore {
    @Override
    public ProgramElement get(Program program, DataElement dataElement) {
        return (ProgramElement) getCriteria(
                Restrictions.eq( "program", program ),
                Restrictions.eq( "dataElement", dataElement ) ).uniqueResult();
    }
}
