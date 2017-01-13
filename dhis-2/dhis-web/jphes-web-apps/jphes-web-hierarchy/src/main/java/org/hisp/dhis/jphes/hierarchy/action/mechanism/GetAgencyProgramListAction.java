package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.program.Program;

/**
 * Created by afya on 13/01/17.
 */
public class GetAgencyProgramListAction extends ActionPagingSupport<Program>
{
    @Override public String execute() throws Exception
    {
        return SUCCESS;
    }
}
