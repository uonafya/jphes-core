package org.hisp.dhis.jphes.program;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;
import org.hisp.dhis.dataelement.DataElement;

/**
 * Created by xenial on 1/11/17.
 */
public interface ProgramElementStore extends GenericIdentifiableObjectStore<ProgramElement> {
    ProgramElement get( Program program, DataElement dataElement );
}
