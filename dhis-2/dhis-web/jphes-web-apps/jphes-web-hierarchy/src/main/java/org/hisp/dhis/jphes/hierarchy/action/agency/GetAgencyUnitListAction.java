package org.hisp.dhis.jphes.hierarchy.action.agency;

import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.paging.ActionPagingSupport;

/**
 * Created by @bangadennis on 05/01/17.
 */
public class GetAgencyUnitListAction extends ActionPagingSupport<AgencyUnit>
{
    @Override public String execute() throws Exception
    {
        return SUCCESS;
    }
}
