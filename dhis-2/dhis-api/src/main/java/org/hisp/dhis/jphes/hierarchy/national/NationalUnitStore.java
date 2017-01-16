package org.hisp.dhis.jphes.hierarchy.national;

import org.hisp.dhis.common.GenericIdentifiableObjectStore;

/**
 * @author bangadennis on 05/01/17.
 */
public interface NationalUnitStore extends GenericIdentifiableObjectStore<NationalUnit>
{
    //inherits all methods of the generic class.
    NationalUnit getNationalUnitByShortName( String shortName );
}
