package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.paging.ActionPagingSupport;

/**
 * Created by afya on 05/01/17.
 */
public class GetMechanismUnitListAction extends ActionPagingSupport<MechanismUnit>
{
    @Override public String execute() throws Exception
    {
        return SUCCESS;
    }
}
