package org.hisp.dhis.jphes.program;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

/**
 * Created by xenial on 1/4/17.
 */
public interface ProgramStore extends GenericIdentifiableObjectStore<Program> {
    String ID = ProgramStore.class.getName();

}
