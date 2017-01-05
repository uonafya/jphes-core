package org.hisp.dhis.jphes.program;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

import java.util.List;

/**
 * Created by xenial on 1/4/17.
 */
public interface ProgramStore extends GenericIdentifiableObjectStore<Program> {
    String ID = ProgramStore.class.getName();

    List<Program> searchProgramByName(String key);

    List<Program> getAllPrograms();
}
