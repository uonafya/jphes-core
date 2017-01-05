package org.hisp.dhis.jphes.program.hibernate;

import org.hibernate.Query;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramStore;

import java.util.List;

/**
 * Created by xenial on 1/4/17.
 */
public class HibernateProgramStore extends HibernateIdentifiableObjectStore<Program> implements ProgramStore {

    @Override
    public List<Program> searchProgramByName(String key) {
        String hql = "select program from Program where program.name = :key";
        Query query = getQuery(hql);
        return (List<Program>) query.uniqueResult();
    }

    @Override
    public List<Program> getAllPrograms(){
        String hql = "select program from Program";
        Query query = getQuery(hql);
        return query.list();
    }
}
