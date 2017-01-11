package org.hisp.dhis.jphes.hierarchy.agency;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

/**
 * @author bangadennis on 11/01/17.
 */

public interface AgencyUnitStore extends GenericIdentifiableObjectStore<AgencyUnit>
{
    AgencyUnit getAgencyUnitByShortName(String shortName);
}
