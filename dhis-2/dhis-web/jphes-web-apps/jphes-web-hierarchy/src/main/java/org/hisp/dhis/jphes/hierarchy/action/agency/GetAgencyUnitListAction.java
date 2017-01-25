package org.hisp.dhis.jphes.hierarchy.action.agency;

import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by @bangadennis on 05/01/17.
 */
public class GetAgencyUnitListAction extends ActionPagingSupport<AgencyUnit>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private AgencyUnitService agencyUnitService;

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private List<AgencyUnit> agencyUnits;

    public List<AgencyUnit> getAgencyUnits(){ return  agencyUnits; }

    private String key;

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }

    // -------------------------------------------------------------------------
    // Implementation
    // -------------------------------------------------------------------------
    @Override public String execute() throws Exception
    {
        // Filter on key only if set
        if ( isNotBlank( key ) )
        {
            this.paging = createPaging(agencyUnitService.getAgencyUnitCountByName(key));

            agencyUnits = agencyUnitService.getAgencyUnitsBetweenByName(key, paging.getStartPos(), paging.getPageSize() );

            Collections.sort( agencyUnits );
        }
        else
        {
            this.paging = createPaging( agencyUnitService.getAgencyUnitCount() );

            agencyUnits = agencyUnitService.getAgencyUnitsBetween( paging.getStartPos(), paging.getPageSize() );

            Collections.sort( agencyUnits );
        }

        return SUCCESS;
    }
}
