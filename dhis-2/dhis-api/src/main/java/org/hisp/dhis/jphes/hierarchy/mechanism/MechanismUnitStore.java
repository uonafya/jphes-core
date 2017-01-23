package org.hisp.dhis.jphes.hierarchy.mechanism;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

/**
 * @author bangadennis on 11/01/17.
 */
public interface MechanismUnitStore extends GenericIdentifiableObjectStore<MechanismUnit>
{
    MechanismUnit getMechanismUnitByShortName(String shortName);
}