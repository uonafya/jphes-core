package org.hisp.dhis.jphes.hierarchy.donor;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

/**
 * @author bangadennis on 11/01/17.
 */
public interface DonorUnitStore extends GenericIdentifiableObjectStore<DonorUnit>
{
    DonorUnit getDonorUnitByShortName(String shortName);
}
