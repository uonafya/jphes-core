package org.hisp.dhis.jphes.program.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hisp.dhis.common.hibernate.HibernateIdentifiableObjectStore;
import org.hisp.dhis.jphes.program.Program;
import org.hisp.dhis.jphes.program.ProgramStore;

/**
 * Created by xenial on 1/4/17.
 */
public class HibernateProgramStore extends HibernateIdentifiableObjectStore<Program> implements ProgramStore {

    private static final Log log = LogFactory.getLog( HibernateProgramStore.class );
}
